package com.hollowambitionproject.legacyofbrynjolf.domain;

import com.hollowambitionproject.legacyofbrynjolf.constant.MoveResult;

/**
 * The Class GameResult. Used to keep track of state of each game.
 */
public class GameResult {

	/** The moves. */
	private final String moves;

	/** The move result. */
	private MoveResult moveResult;

	/** The current moves count. */
	private int currentMovesCount;

	/** The additional moves. */
	private String additionalMoves;

	/** The room. */
	private Room room;

	/**
	 * Instantiates a new game result.
	 *
	 * @param room  the room
	 * @param moves the moves
	 */
	public GameResult(Room room, String moves) {
		super();
		this.moves = moves;
		this.room = room;
		additionalMoves = "";
		currentMovesCount = 0;
	}

	/**
	 * Gets the additional moves.
	 *
	 * @return the additional moves
	 */
	public String getAdditionalMoves() {
		return additionalMoves;
	}

	/**
	 * Sets the additional moves.
	 *
	 * @param additionalMoves the new additional moves
	 */
	public void setAdditionalMoves(String additionalMoves) {
		this.additionalMoves = additionalMoves;
	}

	/**
	 * Gets the move result.
	 *
	 * @return the move result
	 */
	public MoveResult getMoveResult() {
		return moveResult;
	}

	/**
	 * Sets the move result.
	 *
	 * @param moveResult the new move result
	 */
	public void setMoveResult(MoveResult moveResult) {
		this.moveResult = moveResult;
	}

	/**
	 * Gets the current moves count.
	 *
	 * @return the current moves count
	 */
	public int getCurrentMovesCount() {
		return currentMovesCount;
	}

	/**
	 * Sets the current moves count.
	 *
	 * @param currentMovesCount the new current moves count
	 */
	public void setCurrentMovesCount(int currentMovesCount) {
		this.currentMovesCount = currentMovesCount;
	}

	/**
	 * Gets the moves.
	 *
	 * @return the moves
	 */
	public String getMoves() {
		return moves;
	}

	/**
	 * Gets the room.
	 *
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * Sets the room.
	 *
	 * @param room the new room
	 */
	public void setRoom(Room room) {
		this.room = room;
	}

}
