package com.hollowambitionproject.legacyofbrynjolf.util;

import com.hollowambitionproject.legacyofbrynjolf.constant.Direction;
import com.hollowambitionproject.legacyofbrynjolf.domain.Coordinate;
import com.hollowambitionproject.legacyofbrynjolf.exception.InvalidMoveException;

/**
 * The Class DirectionUtil.
 */
public class DirectionUtil {

	/**
	 * Gets the direction. Converts char to Enum.
	 *
	 * @param direction the direction
	 * @return the direction
	 * @throws InvalidMoveException the invalid move exception
	 */
	public static Direction getDirection(char direction) throws InvalidMoveException {
		switch (direction) {
		case 'u':
		case 'U':
			return Direction.UP;
		case 'd':
		case 'D':
			return Direction.DOWN;
		case 'r':
		case 'R':
			return Direction.RIGHT;
		case 'l':
		case 'L':
			return Direction.LEFT;
		default:
			throw new InvalidMoveException("Invalid move - " + direction);
		}

	}

	/**
	 * Gets the next coordinates in a direction.
	 *
	 * @param coordinate the coordinate
	 * @param direction  the direction
	 * @return the coordinates in direction
	 */
	public static Coordinate getCoordinatesInDirection(Coordinate coordinate, Direction direction) {
		int x = coordinate.getX(), y = coordinate.getY();
		switch (direction) {
		case UP:
			x--;
			break;
		case DOWN:
			x++;
			break;
		case LEFT:
			y--;
			break;
		case RIGHT:
			y++;
			break;
		default:
			break;
		}
		return new Coordinate(x, y);
	}

}
