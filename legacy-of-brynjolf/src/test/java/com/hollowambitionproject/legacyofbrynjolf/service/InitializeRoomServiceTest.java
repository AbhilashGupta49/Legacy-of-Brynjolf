package com.hollowambitionproject.legacyofbrynjolf.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.hollowambitionproject.legacyofbrynjolf.dao.RoomDao;
import com.hollowambitionproject.legacyofbrynjolf.dao.RoomTextFileDaoImpl;
import com.hollowambitionproject.legacyofbrynjolf.domain.Coordinate;
import com.hollowambitionproject.legacyofbrynjolf.domain.Room;

/**
 * The Class InitializeRoomServiceTest.
 */
public class InitializeRoomServiceTest {

	/** The initialize room service. */
	private static InitializeRoomService initializeRoomService;

	/** The room dao. */
	private static RoomDao roomDao;

	/**
	 * Setup.
	 */
	@BeforeAll
	public static void setup() {
		roomDao = Mockito.mock(RoomTextFileDaoImpl.class);
		initializeRoomService = new InitializeRoomServiceImpl(roomDao);

	}

	/**
	 * Setup room position validations test.
	 */
	@Test
	public void setupRoomPositionValidationsTest() {
		List<String> lines = new ArrayList<>();
		lines.add(". . .");
		lines.add(". b o");
		lines.add(". g .");
		Mockito.when(roomDao.getRoomData()).thenReturn(lines);
		Assertions.assertNotNull(roomDao.getRoomData());
		char[][] mockedMatrix = { { '.', '.', '.' }, { '.', 'b', 'o' }, { '.', 'g', '.' } };
		Room room = initializeRoomService.setupRoom();

		// Assert matrix equal
		char[][] matrix = room.getMatrix();
		Assertions.assertEquals(matrix.length, mockedMatrix.length);
		Assertions.assertEquals(matrix[0].length, mockedMatrix[0].length);
		for (int i = 0; i < matrix.length; i++) {
			Assertions.assertArrayEquals(mockedMatrix[i], matrix[i]);
		}

		// Assert brynjolf position equal
		Coordinate mockedCoordinate = new Coordinate(1, 1);
		Coordinate brynjolfPosition = room.getBrynjolfsPosition();
		Assertions.assertEquals(mockedCoordinate, brynjolfPosition);

		// Assert guard positions equal
		List<Coordinate> mockedCoordinates = new ArrayList<>();
		mockedCoordinates.add(new Coordinate(2, 1));
		Assertions.assertEquals(mockedCoordinates, room.getGuardPositions());
	}

	/**
	 * Validation invalid character test.
	 */
	@Test
	public void validationInvalidCharacterTest() {
		char[][] matrix = { { '.', 'a', '.' }, { '.', 'b', 'o' }, { '.', 'g', '.' } };
		Assertions.assertEquals(false, initializeRoomService.validate(matrix));
	}

	/**
	 * Validation extra row size test.
	 */
	@Test
	public void validationExtraRowSizeTest() {
		char[][] matrix = { { '.', '.', '.', '.' }, { '.', 'b', 'o' }, { '.', 'g', '.' } };
		// True, because it creates a 4X4 matrix with walls at end.
		Assertions.assertEquals(false, initializeRoomService.validate(matrix));
	}

	/**
	 * Validation max size test.
	 */
	@Test
	public void validationMaxSizeTest() {
		char[][] matrix = { { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', 'b', 'o' },
				{ '.', 'g', '.' } };
		Assertions.assertEquals(false, initializeRoomService.validate(matrix));
	}

	/**
	 * Validation multiple brynjolf test.
	 */
	@Test
	public void validationMultipleBrynjolfTest() {
		char[][] matrix = { { '.', '.', '.' }, { '.', 'b', 'o' }, { '.', 'g', 'b' } };
		Assertions.assertEquals(false, initializeRoomService.validate(matrix));
	}

	/**
	 * Validation multiple exit test.
	 */
	@Test
	public void validationMultipleExitTest() {
		char[][] matrix = { { '.', '.', '.' }, { 'o', 'b', 'o' }, { '.', 'g', '.' } };
		Assertions.assertEquals(false, initializeRoomService.validate(matrix));
	}

	/**
	 * Validation brynjolf not present test.
	 */
	@Test
	public void validationBrynjolfNotPresentTest() {
		char[][] matrix = { { '.', '.', '.' }, { 'o', '.', '.' }, { '.', 'g', '.' } };
		Assertions.assertEquals(false, initializeRoomService.validate(matrix));
	}

	/**
	 * Validation exit not present test.
	 */
	@Test
	public void validationExitNotPresentTest() {
		char[][] matrix = { { '.', '.', '.' }, { 'b', '.', '.' }, { '.', 'g', '.' } };
		Assertions.assertEquals(false, initializeRoomService.validate(matrix));
	}

}
