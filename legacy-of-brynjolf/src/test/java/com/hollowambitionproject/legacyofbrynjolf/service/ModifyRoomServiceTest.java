package com.hollowambitionproject.legacyofbrynjolf.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.hollowambitionproject.legacyofbrynjolf.constant.Direction;
import com.hollowambitionproject.legacyofbrynjolf.constant.MoveResult;
import com.hollowambitionproject.legacyofbrynjolf.domain.Coordinate;
import com.hollowambitionproject.legacyofbrynjolf.domain.Room;

/**
 * The Class ModifyRoomServiceTest.
 */
public class ModifyRoomServiceTest {

	/** The modify room service. */
	private static ModifyRoomService modifyRoomService;

	/** The room. */
	private static Room room;

	/**
	 * Setup.
	 */
	@BeforeAll
	public static void setup() {
		modifyRoomService = new ModifyRoomServiceImpl();
		room = new Room();
	}

	/**
	 * Move matrix change test.
	 */
	@Test
	public void moveMatrixChangeTest() {
		char[][] matrix = { { '.', '.', '.' }, { '.', 'b', 'o' }, { '.', 'g', '.' } };
		room.setMatrix(matrix);
		room.setBrynjolfsPosition(new Coordinate(1, 1));
		List<Coordinate> guards = new ArrayList<>();
		guards.add(new Coordinate(2, 1));
		room.setGuardPositions(guards);
		Assertions.assertEquals(MoveResult.WIN, modifyRoomService.move(room, Direction.RIGHT));
		char[][] finalMatrix = room.getMatrix();
		char[][] mockedMatrix = { { '.', '.', '.' }, { '.', '.', 'o' }, { '.', '.', 'g' } };
		for (int i = 0; i < matrix.length; i++) {
			Assertions.assertArrayEquals(mockedMatrix[i], finalMatrix[i]);
		}
	}

	/**
	 * Move win test.
	 */
	@Test
	public void moveWinTest() {
		char[][] matrix = { { '.', '.', '.' }, { '.', 'b', 'o' }, { '.', 'g', '.' } };
		room.setMatrix(matrix);
		room.setBrynjolfsPosition(new Coordinate(1, 1));
		List<Coordinate> guards = new ArrayList<>();
		guards.add(new Coordinate(2, 1));
		room.setGuardPositions(guards);
		Assertions.assertEquals(MoveResult.WIN, modifyRoomService.move(room, Direction.RIGHT));
		Assertions.assertEquals(new Coordinate(1, 2), room.getBrynjolfsPosition());
		Assertions.assertEquals(new Coordinate(2, 2), room.getGuardPositions().get(0));
	}

	/**
	 * Move lose test.
	 */
	@Test
	public void moveLoseTest() {
		char[][] matrix = { { '.', '.', '.' }, { '.', 'b', 'o' }, { '.', 'g', '.' } };
		room.setMatrix(matrix);
		room.setBrynjolfsPosition(new Coordinate(1, 1));
		List<Coordinate> guards = new ArrayList<>();
		guards.add(new Coordinate(2, 1));
		room.setGuardPositions(guards);
		Assertions.assertEquals(MoveResult.LOSE, modifyRoomService.move(room, Direction.UP));
		Assertions.assertEquals(new Coordinate(0, 1), room.getBrynjolfsPosition());
		Assertions.assertEquals(new Coordinate(0, 1), room.getGuardPositions().get(0));
	}

	/**
	 * Move moved test.
	 */
	@Test
	public void moveMovedTest() {
		char[][] matrix = { { '.', '.', 'o' }, { '.', 'b', '.' }, { 'g', '.', 'g' } };
		room.setMatrix(matrix);
		room.setBrynjolfsPosition(new Coordinate(1, 1));
		List<Coordinate> guards = new ArrayList<>();
		guards.add(new Coordinate(2, 0));
		guards.add(new Coordinate(2, 2));
		room.setGuardPositions(guards);
		Assertions.assertEquals(MoveResult.MOVED, modifyRoomService.move(room, Direction.LEFT));
		Assertions.assertEquals(new Coordinate(1, 0), room.getBrynjolfsPosition());
		List<Coordinate> mockedList = new ArrayList<>();
		mockedList.add(new Coordinate(2, 0));
		mockedList.add(new Coordinate(2, 1));
		Assertions.assertEquals(mockedList, room.getGuardPositions());
	}

	/**
	 * Move unmoved test.
	 */
	@Test
	public void moveUnmovedTest() {
		char[][] matrix = { { '.', '.', 'o' }, { '.', '.', 'b' }, { '.', '.', 'g' } };
		room.setMatrix(matrix);
		room.setBrynjolfsPosition(new Coordinate(1, 2));
		List<Coordinate> guards = new ArrayList<>();
		guards.add(new Coordinate(2, 2));
		room.setGuardPositions(guards);
		Assertions.assertEquals(MoveResult.UNMOVED, modifyRoomService.move(room, Direction.RIGHT));
		Assertions.assertEquals(new Coordinate(1, 2), room.getBrynjolfsPosition());
		List<Coordinate> mockedList = new ArrayList<>();
		mockedList.add(new Coordinate(2, 2));
		Assertions.assertEquals(mockedList, room.getGuardPositions());
	}

}
