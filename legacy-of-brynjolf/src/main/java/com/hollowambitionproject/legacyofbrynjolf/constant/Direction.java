package com.hollowambitionproject.legacyofbrynjolf.constant;

/**
 * The Enum Direction.
 */
public enum Direction {

	/** The up. */
	UP('u'),

	/** The down. */
	DOWN('d'),

	/** The left. */
	LEFT('l'),

	/** The right. */
	RIGHT('r');

	/** The value. */
	private char value;

	/**
	 * Instantiates a new direction.
	 *
	 * @param value the value
	 */
	Direction(char value) {
		this.value = value;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public char getValue() {
		return value;
	}

	/**
	 * Gets the opposite.
	 *
	 * @return the opposite
	 */
	public Direction getOpposite() {
		switch (this) {
		case DOWN:
			return UP;
		case LEFT:
			return RIGHT;
		case RIGHT:
			return LEFT;
		case UP:
			return DOWN;
		default:
			return null;
		}
	}

}
