package com.glueck.harish.connect4;

import org.junit.Test;
import static org.junit.Assert.*;

import com.glueck.harish.connect4.model.Board;

public class BoardExaminerTest {

	@Test
	public void testDiagonal() {
		Board testBoard = new Board();
		int[][] testboardData={{0,0,0,0,0,0,0},
								 {0,0,0,0,2,0,0},
								 {0,0,0,1,2,2,0},
								 {0,0,0,0,1,2,0},
								 {0,0,0,0,2,1,2},
								 {0,0,2,0,0,2,1}};
		testBoard.setBoardData(testboardData);
		boolean testresult = testBoard.getExaminer().checkForWin(1);
		assertEquals(true, testresult);
		testresult = testBoard.getExaminer().checkForWin(2);
		assertEquals(false, testresult);
	}
	
	@Test
	public void testDiagonalEdge() {
		int[][] testboardData={{0,0,0,0,0,0,0},
								 {0,0,0,0,2,0,0},
								 {0,0,0,1,2,2,0},
								 {0,0,1,1,0,0,0},
								 {0,1,0,2,0,1,2},
								 {1,0,2,2,1,2,1}};
		Board testBoard = new Board();
		testBoard.setBoardData(testboardData);
		boolean testresult = testBoard.getExaminer().checkForWin(1);
		assertEquals(true, testresult);
		testresult = testBoard.getExaminer().checkForWin(2);
		assertEquals(false, testresult);
	}
	@Test
	public void testHardStrategy(){
		int[][] testHarder = {{0, 0, 0, 0, 0, 0, 0}, 
								{0, 0, 1, 0, 0, 0, 0},
								{0, 0, 2, 0, 0, 2, 0}, 
								{0, 0, 1, 0, 0, 2, 0}, 
								{1, 0, 1, 1, 2, 1, 0}, 
								{2, 1, 1, 1, 2, 2, 0}} ;

	Board testBoard = new Board();
	testBoard.setBoardData(testHarder);
	boolean testresult = testBoard.getExaminer().checkForWin(1);
	assertEquals(false, testresult);
	testresult = testBoard.getExaminer().checkForWin(2);
	assertEquals(false, testresult);
	}
}
