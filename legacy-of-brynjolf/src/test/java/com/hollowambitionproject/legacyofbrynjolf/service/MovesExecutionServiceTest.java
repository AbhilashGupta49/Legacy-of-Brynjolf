package com.hollowambitionproject.legacyofbrynjolf.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.hollowambitionproject.legacyofbrynjolf.constant.Direction;
import com.hollowambitionproject.legacyofbrynjolf.constant.MoveResult;
import com.hollowambitionproject.legacyofbrynjolf.domain.Coordinate;
import com.hollowambitionproject.legacyofbrynjolf.domain.GameResult;
import com.hollowambitionproject.legacyofbrynjolf.domain.Room;

/**
 * The Class MovesExecutionServiceTest.
 */
public class MovesExecutionServiceTest {

	/** The modify room service. */
	private static ModifyRoomService modifyRoomService;

	/** The moves generator service. */
	private static MovesGeneratorService movesGeneratorService;

	/** The moves execution service. */
	private static MovesExecutionService movesExecutionService;

	/** The room. */
	private static Room room;

	/**
	 * Setup.
	 */
	@BeforeAll
	public static void setup() {
		movesGeneratorService = Mockito.mock(MovesGeneratorServiceImpl.class);
		modifyRoomService = Mockito.mock(ModifyRoomServiceImpl.class);
		movesExecutionService = new MovesExecutionServiceImpl(modifyRoomService, movesGeneratorService);
	}

	@BeforeEach
	public void roomSetup() {
		room = new Room();
		char[][] matrix = { { '.', '.', '.' }, { '.', 'b', 'o' }, { '.', 'g', '.' } };
		room.setMatrix(matrix);
		room.setBrynjolfsPosition(new Coordinate(1, 1));
		List<Coordinate> guards = new ArrayList<>();
		guards.add(new Coordinate(2, 1));
		room.setGuardPositions(guards);
	}

	/**
	 * Execute moves undecided test.
	 */
	@Test
	public void executeMovesUndecidedTest() {
		String moves = "uuuu";
		Mockito.when(modifyRoomService.move(room, Direction.UP)).thenReturn(MoveResult.UNMOVED);
		GameResult gameResult = movesExecutionService.executeMoves(room, moves);
		Assertions.assertEquals(MoveResult.UNDECIDED, gameResult.getMoveResult());
	}

	/**
	 * Execute moves win test.
	 */
	@Test
	public void executeMovesWinTest() {
		String moves = "r";
		Mockito.when(modifyRoomService.move(room, Direction.RIGHT)).thenReturn(MoveResult.WIN);
		GameResult gameResult = movesExecutionService.executeMoves(room, moves);
		Assertions.assertEquals(MoveResult.WIN, gameResult.getMoveResult());
	}

	/**
	 * Execute moves lose test.
	 */
	@Test
	public void executeMovesLoseTest() {
		String moves = "u";
		Mockito.when(modifyRoomService.move(room, Direction.UP)).thenReturn(MoveResult.LOSE);
		GameResult gameResult = movesExecutionService.executeMoves(room, moves);
		Assertions.assertEquals(MoveResult.LOSE, gameResult.getMoveResult());
	}

	/**
	 * Execute additional moves stuck test.
	 */
	@Test
	public void executeAdditionalMovesStuckTest() {
		Mockito.when(movesGeneratorService.generateNextMoves("")).thenReturn(null);
		GameResult gameResult = new GameResult(room, "udlr");
		movesExecutionService.executeAdditionalMoves(gameResult);
		Assertions.assertEquals(MoveResult.STUCK, gameResult.getMoveResult());
	}

}
