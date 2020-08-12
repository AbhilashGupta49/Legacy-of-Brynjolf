package com.hollowambitionproject.legacyofbrynjolf.service;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Component;

import com.hollowambitionproject.legacyofbrynjolf.constant.Direction;

/**
 * The Class MovesGeneratorServiceImpl.
 */
@Component
public class MovesGeneratorServiceImpl implements MovesGeneratorService {

	/**
	 * The max lookahead. Max Length of moves having permutations for a possible
	 * path. Value is to be set, otherwise program would not stop till it finds a
	 * solution.
	 */
	private static final int MAX_LOOKAHEAD = 30;

	/**
	 * Generate next moves. Used if game has not concluded, and we need to generate
	 * a possible solution.
	 *
	 * @param additionalMoves the additional moves
	 * @return the queue
	 */
	@Override
	public Queue<String> generateNextMoves(String additionalMoves) {
		if (additionalMoves.length() > MAX_LOOKAHEAD) {
			return null;
		}
		Queue<String> moves = new LinkedList<>();
		for (Direction direction : Direction.values()) {
			if (additionalMoves.length() != 0) {
				// Does not adds current and opposite direction in the permutation. Decreases
				// the size of permutation by removing useless directions which will never lead
				// to a solution.
				char lastDirection = additionalMoves.charAt(additionalMoves.length() - 1);
				if (direction.getOpposite().getValue() == lastDirection || direction.getValue() == lastDirection) {
					continue;
				}
			}
			moves.add(additionalMoves + direction.getValue());
		}
		return moves;
	}

}
