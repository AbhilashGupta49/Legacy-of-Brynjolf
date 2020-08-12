package com.hollowambitionproject.legacyofbrynjolf.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hollowambitionproject.legacyofbrynjolf.domain.GameResult;
import com.hollowambitionproject.legacyofbrynjolf.domain.Room;

/**
 * The Class DisplayUtil.
 */
public class DisplayUtil {

	/** The Constant LOG. */
	private static final Logger LOG = LogManager.getLogger(DisplayUtil.class);

	/**
	 * Display matrix.
	 */
	public static void displayMatrix(Room room) {
		char[][] matrix = room.getMatrix();
		System.out.printf("\nMatrix: \n");
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.printf("%c ", matrix[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * Display result and end game.
	 *
	 * @param result the result
	 */
	public static void displayResult(GameResult result) {
		int totalMoves = result.getMoves().length();
		int currentMoves = result.getCurrentMovesCount();
		displayMatrix(result.getRoom());
		switch (result.getMoveResult()) {
		case LOSE:
			System.out.printf("\nLose: executed %d moves out of %d\n", currentMoves, totalMoves);
			break;
		case WIN:
			System.out.printf("\nWin: executed %d moves out of %d\n", currentMoves, totalMoves);
			break;
		case WIN_ADDITIONAL_MOVES:
			System.out.printf("\nWin: %s\n", result.getAdditionalMoves());
			break;
		case UNDECIDED:
			System.out.printf("\nGame is undecided(no movement) after %d user moves\n", currentMoves);
			break;
		case STUCK:
			System.out.println("\nStuck: no way to win\n");
			break;
		default:
			break;
		}
		System.out.println();
		LOG.info("Execution complete. Exiting Application!");
		Runtime runtime = Runtime.getRuntime();
		runtime.exit(0);
	}
}
