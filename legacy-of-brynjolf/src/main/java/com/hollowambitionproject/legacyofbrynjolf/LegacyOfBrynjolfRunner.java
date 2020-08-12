/*
 * 
 */
package com.hollowambitionproject.legacyofbrynjolf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hollowambitionproject.legacyofbrynjolf.constant.MoveResult;
import com.hollowambitionproject.legacyofbrynjolf.domain.GameResult;
import com.hollowambitionproject.legacyofbrynjolf.domain.Room;
import com.hollowambitionproject.legacyofbrynjolf.service.InitializeRoomService;
import com.hollowambitionproject.legacyofbrynjolf.service.MovesExecutionService;
import com.hollowambitionproject.legacyofbrynjolf.util.DisplayUtil;

/**
 * The Class LegacyOfBrynjolfRunner.
 */
@Component
public class LegacyOfBrynjolfRunner {

	/** The Constant LOG. */
	private static final Logger LOG = LogManager.getLogger(LegacyOfBrynjolfRunner.class);

	/** The initialize room service. */
	private InitializeRoomService initializeRoomService;

	/** The moves execution service. */
	private MovesExecutionService movesExecutionService;

	/** The initial room. */
	private Room initialRoom;

	/**
	 * Instantiates a new legacy of brynjolf runner.
	 *
	 * @param initializeRoomService the initialize room service
	 * @param movesExecutionService the moves execution service
	 */
	@Autowired
	public LegacyOfBrynjolfRunner(InitializeRoomService initializeRoomService,
			MovesExecutionService movesExecutionService) {
		this.initializeRoomService = initializeRoomService;
		this.movesExecutionService = movesExecutionService;
	}

	/**
	 * Start. Main method for starting a new game. Initializes matrix and perform
	 * moves.
	 *
	 * @param initialMoves the initial moves
	 */
	public void start(String initialMoves) {
		// Reads file, validates matrix and loads Room.
		initialRoom = initializeRoomService.setupRoom();

		System.out.printf("Input: %s \n", initialMoves);
		GameResult gameResult = new GameResult(initialRoom, initialMoves);

		// Executing initial moves.
		gameResult = movesExecutionService.executeMoves(gameResult.getRoom(), gameResult.getMoves());
		MoveResult moveResult = gameResult.getMoveResult();
		if (moveResult == MoveResult.WIN || moveResult == MoveResult.LOSE) {
			DisplayUtil.displayResult(gameResult);
		}

		LOG.debug("Input Executed. Finding solution - ");
		// Executing additional moves as user input did not conclude the game.
		movesExecutionService.executeAdditionalMoves(gameResult);
		DisplayUtil.displayResult(gameResult);

	}

}
