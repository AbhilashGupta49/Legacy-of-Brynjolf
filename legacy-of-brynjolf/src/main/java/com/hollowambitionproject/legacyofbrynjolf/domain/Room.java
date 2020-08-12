package com.hollowambitionproject.legacyofbrynjolf.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class Room. Stores matrix and positions of actors.
 */
public class Room implements Cloneable {

	/** The matrix. */
	private char[][] matrix;

	/** The brynjolfs position. */
	private Coordinate brynjolfsPosition;

	/** The guards position. */
	private List<Coordinate> guardPositions;

	/**
	 * Instantiates a new room.
	 */
	public Room() {
		guardPositions = new ArrayList<>();
	}

	/**
	 * Clone.
	 *
	 * @return the object
	 */
	public Room clone() {
		Room cloned = null;
		try {
			cloned = (Room) super.clone();
		} catch (CloneNotSupportedException e) {
			cloned = new Room();
		}
		char[][] matrix = this.matrix.clone();
		for (int i = 0; i < this.matrix.length; i++) {
			matrix[i] = this.matrix[i].clone();
		}
		cloned.matrix = matrix;
		cloned.brynjolfsPosition = this.brynjolfsPosition.clone();
		cloned.guardPositions = new ArrayList<>();
		for (Coordinate coordinate : this.guardPositions) {
			cloned.guardPositions.add(coordinate.clone());
		}
		return cloned;
	}

	/**
	 * Gets the brynjolfs position.
	 *
	 * @return the brynjolfs position
	 */
	public Coordinate getBrynjolfsPosition() {
		return brynjolfsPosition;
	}

	/**
	 * Sets the brynjolfs position.
	 *
	 * @param brynjolfsPosition the new brynjolfs position
	 */
	public void setBrynjolfsPosition(Coordinate brynjolfsPosition) {
		this.brynjolfsPosition = brynjolfsPosition;
	}

	/**
	 * Gets the guards position.
	 *
	 * @return the guards position
	 */
	public List<Coordinate> getGuardPositions() {
		return guardPositions;
	}

	/**
	 * Sets the guards position.
	 *
	 * @param guardPositions the new guard positions
	 */
	public void setGuardPositions(List<Coordinate> guardPositions) {
		this.guardPositions = guardPositions;
	}

	/**
	 * Gets the matrix.
	 *
	 * @return the matrix
	 */
	public char[][] getMatrix() {
		return matrix;
	}

	/**
	 * Sets the matrix.
	 *
	 * @param matrix the new matrix
	 */
	public void setMatrix(char[][] matrix) {
		this.matrix = matrix;
	}

}
