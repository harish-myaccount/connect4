package com.glueck.harish.connect4.model;

public class BoardExaminer {

	private static final int REQUIRED_CONSECUTIVE = 4;
	private Board board;

	public BoardExaminer(Board board) {
		this.board = board;
	}

	public boolean checkForWin(int player) {
		if (findVerticalFour(player)) {
			return true;
		} else if (findHorizontalFour(player)) {
			return true;
		} else if (findDiagonalFour(player)) {
			return true;
		}
		return false;
	}

	private boolean findDiagonalFour(int player) {
		boolean won = false;
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				if (board.getBoardData()[i][j] == player) {
					if ((i + 3) < board.getRows() && (j + 3) < board.getColumns()) {
						won = (board.getBoardData()[i + 1][j + 1] == player)
								&& (board.getBoardData()[i + 2][j + 2] == player)
								&& (board.getBoardData()[i + 3][j + 3] == player);
					} else if ((i - 3) >= 0 && (j - 3) >= 0) {
						won = (board.getBoardData()[i - 1][j - 1] == player)
								&& (board.getBoardData()[i - 2][j - 2] == player)
								&& (board.getBoardData()[i - 3][j - 3] == player);

					} else if ((i + 3) <board.getRows() && (j - 3) >= 0) {
						won = (board.getBoardData()[i + 1][j - 1] == player)
								&& (board.getBoardData()[i + 2][j - 2] == player)
								&& (board.getBoardData()[i + 3][j - 3] == player);

					} else if ((i - 3) >= 0 && (j + 3) <board.getColumns()) {
						won = (board.getBoardData()[i - 1][j + 1] == player)
								&& (board.getBoardData()[i - 2][j + 2] == player)
								&& (board.getBoardData()[i - 3][j + 3] == player);

					}
					if (won)
						return true;
				}
			}
		}
		return false;
	}

	private boolean findHorizontalFour(int player) {
		for (int i = 0; i < board.getRows(); i++) {
			int consecutive = 0;
			for (int j = 0; j < board.getColumns(); j++) {
				if (board.getBoardData()[i][j] == player) {
					consecutive++;
					if (consecutive == REQUIRED_CONSECUTIVE) {
						return true;
					}
				} else {
					consecutive = 0;
				}
			}
		}
		return false;
	}

	private boolean findVerticalFour(int player) {
		for (int i = 0; i < board.getColumns(); i++) {
			int consecutive = 0;
			for (int j = 0; j < board.getRows(); j++) {
				if (board.getBoardData()[j][i] == player) {
					consecutive++;
					if (consecutive == REQUIRED_CONSECUTIVE) {
						return true;
					}
				} else {
					consecutive = 0;
				}
			}
		}
		return false;
	}

}
