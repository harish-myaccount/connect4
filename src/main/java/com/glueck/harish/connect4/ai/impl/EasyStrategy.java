package com.glueck.harish.connect4.ai.impl;

import java.util.Random;

import com.glueck.harish.connect4.ai.AIstrategy;
import com.glueck.harish.connect4.model.Board;

/**
 * 
 * @author harish
 * Easy strategy
 * -------------
 * It takes the last user input and places AI coin in -1 OR same as user OR +1 column
 */
public class EasyStrategy implements AIstrategy{

	Board board;
	
	public EasyStrategy(Board board) {
		this.board = board;
	}
	
	@Override
	public int getMove() {
		Random random = new Random();
		int nextMove;
		do{
		nextMove = board.getLastUserColumn() + random.nextInt(3)-1;
		}while(!board.isValidMove(nextMove));
		return nextMove;
	}

}
