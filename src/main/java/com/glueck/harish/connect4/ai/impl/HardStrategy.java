package com.glueck.harish.connect4.ai.impl;

import java.util.Random;

import com.glueck.harish.connect4.ai.AIstrategy;
import com.glueck.harish.connect4.model.Board;

public class HardStrategy implements AIstrategy {

	Board board;

	public HardStrategy(Board board) {
		this.board = board;
	}

	@Override
	public int getMove() {
		int suggested = -1;
		for (int i = 0; i < board.getColumns(); i++) {
			// check if having a 1 at this place is going to defeat
			boolean mocked = board.setUserMove(i);
			if (board.getExaminer().checkForWin(Board.HUMAN_USER)) {
				suggested = i;
			}
			if(mocked)
			board.undoMove(i);

			mocked = board.setAIMockMove(i);
			if (board.getExaminer().checkForWin(Board.AI_USER)) {
				suggested = i;
			}
			if(mocked)
			board.undoMove(i);
		}

		if (suggested == -1) {
			Random random = new Random();
			int nextMove;
			do {
				nextMove = random.nextInt(board.getColumns());
			} while (!board.isValidMove(nextMove));
			return nextMove;
		} else
			return suggested;

	}

}
