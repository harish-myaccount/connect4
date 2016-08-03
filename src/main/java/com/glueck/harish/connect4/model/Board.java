package com.glueck.harish.connect4.model;

import com.glueck.harish.connect4.ai.AIstrategy;

import lombok.Data;

@Data
public class Board {

	public static final int AI_USER = 2;
	public static final int HUMAN_USER = 1;
	private static final int BLANK = 0;

	private AIstrategy strategy;
	private BoardExaminer examiner;

	private final int rows = 6;
	private final int columns = 7;

	private int[][] boardData;
	private int[] heights;

	private int lastUserColumn;

	public Board() {
		boardData = new int[rows][columns];
		heights = new int[columns];
		examiner = new BoardExaminer(this);
	}

	public boolean setUserMove(Integer columnTofill) {
		if (isValidMove(columnTofill)) {
			setDataInBoard(columnTofill, HUMAN_USER);
			lastUserColumn = columnTofill;
			return true;
		}
		return false;
	}

	public int setAIMove() {
		int columnTofill = strategy.getMove();
		setDataInBoard(columnTofill, AI_USER);
		return columnTofill;
	}

	private void setDataInBoard(int columnTofill, int i) {
		boardData[heights[columnTofill]][columnTofill] = i;
		heights[columnTofill]++;
	}

	public boolean isValidMove(int columnTofill) {
		if (columnTofill < 0 || columnTofill >= columns || heights[columnTofill] == rows) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isUserWinner() {
		return examiner.checkForWin(HUMAN_USER);
	}

	public boolean isAIwinner() {
		return examiner.checkForWin(AI_USER);
	}

	public void printToConsole() {
		for (int i = rows - 1; i >= 0; i--) {
			for (int j = 0; j < columns; j++) {
				System.out.print(" " + boardData[i][j]);
			}
			System.out.println(" ");
		}
		System.out.println("--------------------");
	}

	public boolean isFull() {
		for (int i = 0; i < heights.length; i++) {
			if (heights[i] != rows)
				return false;
		}
		return true;
	}

	public void undoMove(int columnTofill) {
		heights[columnTofill]--;
		boardData[heights[columnTofill]][columnTofill] = Board.BLANK;
	}

	public boolean setAIMockMove(int columnTofill) {
		if (isValidMove(columnTofill)) {
			boardData[heights[columnTofill]][columnTofill] = Board.AI_USER;
			heights[columnTofill]++;
			return true;
		}
		return false;
	}

}
