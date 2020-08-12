package com.hollowambitionproject.legacyofbrynjolf.constant;

/**
 * The Enum Move Result. Condition at end of each move.
 */
public enum MoveResult {

	/** The win without additional (computer generated) moves. */
	WIN,
	/** The win with additional (computer generated) moves. */
	WIN_ADDITIONAL_MOVES,
	/** The lose. */
	LOSE,
	/** The moved. */
	MOVED,
	/**
	 * The unmoved. Used for keeping track if actors have moved after execution in a
	 * direction.
	 */
	UNMOVED,
	/**
	 * The undecided. Used to signify that actors have not moved after (n) moves.
	 */
	UNDECIDED,
	/** The stuck. If computer can't generate a solution. */
	STUCK,
	/** The guard collision. */
	GUARD_COLLISION,
	/**
	 * The guard collision moved. Also checks if there is a movement in the matrix.
	 */
	GUARD_COLLISION_MOVED,
	/** The inconclusive. If game has no result. */
	INCONCLUSIVE
}
