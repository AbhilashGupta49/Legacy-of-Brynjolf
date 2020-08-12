package com.hollowambitionproject.legacyofbrynjolf.service;

import com.hollowambitionproject.legacyofbrynjolf.domain.Room;

/**
 * The Interface InitializeRoomService.
 */
public interface InitializeRoomService {

	/**
	 * Setup room.
	 *
	 * @return true, if successful
	 */
	Room setupRoom();

	/**
	 * Validate.
	 *
	 * @param matrix the matrix
	 * @return true, if successful
	 */
	boolean validate(char[][] matrix);

}
