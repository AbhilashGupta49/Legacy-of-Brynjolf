package com.hollowambitionproject.legacyofbrynjolf.service;

import com.hollowambitionproject.legacyofbrynjolf.domain.GameResult;
import com.hollowambitionproject.legacyofbrynjolf.domain.Room;

/**
 * The Interface MovesExecutionService.
 */
public interface MovesExecutionService {

	/**
	 * Execute additional moves.
	 *
	 * @param gameResult the game result
	 */
	void executeAdditionalMoves(GameResult gameResult);

	/**
	 * Execute moves.
	 *
	 * @param room  the room
	 * @param moves the moves
	 * @return the game result
	 */
	GameResult executeMoves(Room room, String moves);

}
