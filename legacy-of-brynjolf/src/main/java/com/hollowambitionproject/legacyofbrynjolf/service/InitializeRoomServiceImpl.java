package com.hollowambitionproject.legacyofbrynjolf.service;

import static com.hollowambitionproject.legacyofbrynjolf.constant.ApplicationConstants.MAX_SIZE;
import static com.hollowambitionproject.legacyofbrynjolf.constant.NameMappingConstants.BRYNJOLF;
import static com.hollowambitionproject.legacyofbrynjolf.constant.NameMappingConstants.EMPTY_SPACE;
import static com.hollowambitionproject.legacyofbrynjolf.constant.NameMappingConstants.EXIT;
import static com.hollowambitionproject.legacyofbrynjolf.constant.NameMappingConstants.GUARD;
import static com.hollowambitionproject.legacyofbrynjolf.constant.NameMappingConstants.WALL;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hollowambitionproject.legacyofbrynjolf.constant.ApplicationConstants;
import com.hollowambitionproject.legacyofbrynjolf.dao.RoomDao;
import com.hollowambitionproject.legacyofbrynjolf.domain.Coordinate;
import com.hollowambitionproject.legacyofbrynjolf.domain.Room;

/**
 * The Class InitializeRoomServiceImpl. Initializes room by reading file and
 * validating input(matrix).
 */
@Component
public class InitializeRoomServiceImpl implements InitializeRoomService {

	/** The Constant LOG. */
	private static final Logger LOG = LogManager.getLogger(InitializeRoomServiceImpl.class);

	/** The room dao. */
	private RoomDao roomDao;

	/**
	 * Instantiates a new initialize room service impl.
	 *
	 * @param roomFileDao the room file dao
	 */
	@Autowired
	public InitializeRoomServiceImpl(RoomDao roomDao) {
		this.roomDao = roomDao;
	}

	/**
	 * Setup room.
	 *
	 * @return the room
	 */
	@Override
	public Room setupRoom() {
		// Parses file
		List<String> lines = roomDao.getRoomData();
		Room room = new Room();
		int size = lines.size();
		char[][] matrix = new char[size][size];
		for (int i = 0; i < size; i++) {
			String[] row = lines.get(i).split(",| ");
			int j = 0;
			for (; j < size; j++) {
				char value = row[j].charAt(0);
				// Sets Brynjolf's and guards position
				if (value == BRYNJOLF) {
					room.setBrynjolfsPosition(new Coordinate(i, j));
				} else if (value == GUARD) {
					room.getGuardPositions().add(new Coordinate(i, j));
				}
				matrix[i][j] = value;
			}
			while (j < lines.size()) {
				matrix[i][j++] = WALL;
			}
		}
		// Validates the matrix and populates it in Room object.
		if (validate(matrix)) {
			room.setMatrix(matrix);
			return room;
		} else {
			LOG.error("Matrix Validation failed. Exiting Application!");
			Runtime runtime = Runtime.getRuntime();
			runtime.exit(1);
			return null;
		}
	}

	/**
	 * Validate Matrix.
	 *
	 * @param matrix the matrix
	 * @return true, if successful
	 */
	private boolean validate(char[][] matrix) {
		boolean valid = true;
		boolean brynjolfPresent = false;
		boolean exitPresent = false;
		if (matrix.length >= MAX_SIZE) {
			valid = false;
			LOG.error("Rows greater than max size allowed ({})", MAX_SIZE);
		}

		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i].length >= ApplicationConstants.MAX_SIZE) {
				valid = false;
				LOG.error("Columns greater than max size allowed ({})", MAX_SIZE);
			}
			for (int j = 0; j < matrix[i].length; j++) {

				char current = matrix[i][j];
				// Checks for invalid character
				if (current != BRYNJOLF && current != WALL && current != EXIT & current != GUARD
						&& current != EMPTY_SPACE) {
					valid = false;
					LOG.error("Matrix not properly formed at ({},{})", i, j);
				} else if (matrix[i][j] == BRYNJOLF) {
					// Checks whether Brynjolf is present multiple times
					if (brynjolfPresent) {
						valid = false;
						LOG.error("There can't be multiple Brynjolf");
					}
					brynjolfPresent = true;
				} else if (matrix[i][j] == EXIT) {
					// Checks whether exit is present multiple times
					if (exitPresent) {
						valid = false;
						LOG.error("There can't be multiple Exits");
					}
					exitPresent = true;
				}
			}
		}
		if (!brynjolfPresent) {
			LOG.error("Brynjolf not present!");
			valid = false;
		}
		if (!exitPresent) {
			LOG.error("Exit not present!");
			valid = false;
		}
		return valid;
	}

}
