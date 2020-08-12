package com.hollowambitionproject.legacyofbrynjolf.service;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * The Class MoveGeneratorServiceTest.
 */
public class MoveGeneratorServiceTest {

	/** The moves generator service. */
	private static MovesGeneratorService movesGeneratorService;

	/**
	 * Setup.
	 */
	@BeforeAll
	public static void setup() {
		movesGeneratorService = new MovesGeneratorServiceImpl();
	}

	/**
	 * Generate next moves empty test.
	 */
	@Test
	public void generateNextMovesEmptyTest() {
		Queue<String> mockedQueue = new LinkedList<>();
		mockedQueue.add("u");
		mockedQueue.add("l");
		mockedQueue.add("r");
		mockedQueue.add("d");
		Assertions.assertEquals(mockedQueue.containsAll(movesGeneratorService.generateNextMoves("")), true);
	}

	/**
	 * Generate next moves test.
	 */
	@Test
	public void generateNextMovesTest() {
		Queue<String> mockedQueue = new LinkedList<>();
		mockedQueue.add("ul");
		mockedQueue.add("ur");
		Assertions.assertEquals(mockedQueue.containsAll(movesGeneratorService.generateNextMoves("u")), true);
	}

}
