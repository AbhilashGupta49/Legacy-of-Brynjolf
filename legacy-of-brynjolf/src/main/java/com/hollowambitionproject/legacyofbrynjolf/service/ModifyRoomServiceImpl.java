package com.hollowambitionproject.legacyofbrynjolf.service;

import static com.hollowambitionproject.legacyofbrynjolf.constant.NameMappingConstants.BRYNJOLF;
import static com.hollowambitionproject.legacyofbrynjolf.constant.NameMappingConstants.EMPTY_SPACE;
import static com.hollowambitionproject.legacyofbrynjolf.constant.NameMappingConstants.EXIT;
import static com.hollowambitionproject.legacyofbrynjolf.constant.NameMappingConstants.GUARD;
import static com.hollowambitionproject.legacyofbrynjolf.constant.NameMappingConstants.WALL;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Component;

import com.hollowambitionproject.legacyofbrynjolf.constant.Direction;
import com.hollowambitionproject.legacyofbrynjolf.constant.MoveResult;
import com.hollowambitionproject.legacyofbrynjolf.domain.Coordinate;
import com.hollowambitionproject.legacyofbrynjolf.domain.Room;
import com.hollowambitionproject.legacyofbrynjolf.util.DirectionUtil;

/**
 * The Class ModifyRoomServiceImpl.
 */
@Component
public class ModifyRoomServiceImpl implements ModifyRoomService {

	/**
	 * Move. Modifies the matrix by moving actors according to the direction.
	 *
	 * @param room      the room
	 * @param direction the direction
	 * @return the result
	 */
	@Override
	public MoveResult move(Room room, Direction direction) {
		// Move Brynjolf.
		MoveResult brynjolfsResult = moveActor(room, room.getBrynjolfsPosition(), direction);
		// Move all guards.
		MoveResult guardsResult = moveGuards(room, direction);

		// Checks if Brynjolf has won or lost or has moved or still at original
		// position.
		if (brynjolfsResult == MoveResult.WIN || brynjolfsResult == MoveResult.LOSE) {
			return brynjolfsResult;
		} else if (guardsResult == MoveResult.LOSE) {
			return guardsResult;
		} else if (brynjolfsResult == MoveResult.MOVED || guardsResult == MoveResult.MOVED) {
			return MoveResult.MOVED;
		}

		// Returned if there is no change in any positions
		return MoveResult.UNMOVED;
	}

	/**
	 * Move guards. Internally uses moveActor to move each guard individually.
	 *
	 * @param room      the room
	 * @param direction the direction
	 * @return the result
	 */
	private MoveResult moveGuards(Room room, Direction direction) {
		// Checks if a guard has collided with another guard. In this case, the other
		// guard is moved first and then this guard. This is done by using a queue and a
		// list to keep track.
		Queue<Coordinate> guards = new LinkedList<>(room.getGuardPositions());
		boolean moved = false;
		boolean lose = false;
		List<Coordinate> movedGuards = new ArrayList<>();
		// Loop for moving each guard individually.
		while (!guards.isEmpty()) {
			Coordinate coordinate = guards.remove();
			// Move each guard.
			MoveResult result = moveActor(room, coordinate, direction);

			// Checks for collision with another guard in the way.
			if (result == MoveResult.GUARD_COLLISION || result == MoveResult.GUARD_COLLISION_MOVED) {
				if (result == MoveResult.GUARD_COLLISION_MOVED) {
					moved = true;
				}
				// Finds the guard which first has collided with.
				Coordinate nextCoordinate = DirectionUtil.getCoordinatesInDirection(coordinate, direction);
				// If the guard has already moved that means first guard is should be 1 behind
				// the next guard.
				if (!movedGuards.contains(nextCoordinate)) {
					guards.add(coordinate);
					continue;
				}
			} else if (result == MoveResult.LOSE) {
				lose = true;
			}
			// Add this guard to the list of moved guards.
			movedGuards.add(coordinate);
		}
		// Checks if guard has caught Brynjolf or whether guard has moved or guard is at
		// original position.
		if (lose) {
			return MoveResult.LOSE;
		} else if (moved) {
			return MoveResult.MOVED;
		}
		return MoveResult.UNMOVED;
	}

	/**
	 * Move actor. Moves both brynjolf and guards in the matrix.
	 *
	 * @param room       the room
	 * @param coordinate the coordinate
	 * @param direction  the direction
	 * @return the result
	 */
	private MoveResult moveActor(Room room, Coordinate coordinate, Direction direction) {
		char[][] matrix = room.getMatrix();
		int x, y;
		// To track which actor are we moving now.
		boolean isBrynjolf = matrix[coordinate.getX()][coordinate.getY()] == BRYNJOLF;
		boolean moved = false;
		boolean stop = false;
		while (!stop) {
			// Gets the next coordinate in given direction.
			Coordinate changedCoordinates = DirectionUtil.getCoordinatesInDirection(coordinate, direction);
			x = changedCoordinates.getX();
			y = changedCoordinates.getY();
			if (x < 0 || y < 0 || x >= matrix.length || y >= matrix.length || matrix[x][y] == WALL
					|| (matrix[x][y] == EXIT && !isBrynjolf)) {
				// Checks for boundaries and walls and exit. Exit is a wall for guard. Stopping
				// the loop.
				stop = true;
			} else if (isBrynjolf && matrix[x][y] == EXIT) {
				// Checks if actor is bryjolf and current position is same as exit position.
				matrix[coordinate.getX()][coordinate.getY()] = EMPTY_SPACE;
				coordinate.set(x, y);
				return MoveResult.WIN;
			} else if (matrix[x][y] == GUARD) {
				if (isBrynjolf) {
					// Checks if Brynjolf has collided with guard.
					matrix[coordinate.getX()][coordinate.getY()] = EMPTY_SPACE;
					coordinate.set(x, y);
					return MoveResult.LOSE;
				} else if (moved) {
					// Checks if a guard has collided with other guard and there is movement.
					return MoveResult.GUARD_COLLISION_MOVED;
				}
				// If guard has collided with other guard and there is no movement. Used for
				// Undecided result.
				return MoveResult.GUARD_COLLISION;
			} else if (matrix[x][y] == BRYNJOLF && !isBrynjolf) {
				// Checks if guard has collided with Brynjolf.
				matrix[x][y] = matrix[coordinate.getX()][coordinate.getY()];
				matrix[coordinate.getX()][coordinate.getY()] = EMPTY_SPACE;
				coordinate.set(x, y);
				return MoveResult.LOSE;
			} else {
				// If there are no collisions, actor is moved in the direction.
				matrix[x][y] = matrix[coordinate.getX()][coordinate.getY()];
				matrix[coordinate.getX()][coordinate.getY()] = EMPTY_SPACE;
				coordinate.set(x, y);
				moved = true;
			}
		}
		if (moved) {
			// If there is movement, no need to stop the game.
			return MoveResult.MOVED;
		}
		// If there is no movement, we can stop the game as Undecided.
		return MoveResult.UNMOVED;
	}

}
