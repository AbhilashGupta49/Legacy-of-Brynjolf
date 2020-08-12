package com.hollowambitionproject.legacyofbrynjolf.service;

import com.hollowambitionproject.legacyofbrynjolf.constant.Direction;
import com.hollowambitionproject.legacyofbrynjolf.constant.MoveResult;
import com.hollowambitionproject.legacyofbrynjolf.domain.Room;

/**
 * The Interface ModifyRoomService.
 */
public interface ModifyRoomService {

	/**
	 * Move.
	 *
	 * @param room      the room
	 * @param direction the direction
	 * @return the result
	 */
	MoveResult move(Room room, Direction direction);

}
