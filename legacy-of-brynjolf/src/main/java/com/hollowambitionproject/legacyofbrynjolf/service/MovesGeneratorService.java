package com.hollowambitionproject.legacyofbrynjolf.service;

import java.util.Queue;

/**
 * The Interface MovesGeneratorService.
 */
public interface MovesGeneratorService {

	/**
	 * Generate next moves.
	 *
	 * @param additionalMoves the additional moves
	 * @return the queue
	 */
	Queue<String> generateNextMoves(String additionalMoves);

}
