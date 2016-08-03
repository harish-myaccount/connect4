package com.glueck.harish.connect4.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

import com.glueck.harish.connect4.ai.impl.EasyStrategy;
import com.glueck.harish.connect4.ai.impl.HardStrategy;
import com.glueck.harish.connect4.model.Board;
import com.glueck.harish.connect4.web.response.BaseResponse;
import com.glueck.harish.connect4.web.response.FillResponse;

@RestController
@SessionScope
public class GameController {

	private Board board;
	private boolean gameFinished;

	@RequestMapping("/game/{difficulty}")
	@ResponseBody
	public BaseResponse startGame(@PathVariable("difficulty") String strategy) {
		board = new Board();
		switch (strategy) {
		case "easy":
			board.setStrategy(new EasyStrategy(board));
			break;
		case "hard":
			board.setStrategy(new HardStrategy(board));
			break;
		default:
			board.setStrategy(new HardStrategy(board));
			break;
		}
		gameFinished=false;
		BaseResponse response = new BaseResponse();
		response.setMessage("Game started");
		response.setStatus(BaseResponse.SUCCESS);
		return response;
	}

	@RequestMapping("/move/{column}")
	@ResponseBody
	public FillResponse fillColumn(@PathVariable("column") Integer columnTofill) {
		if (board == null) {
			startGame("easy");
		}
		FillResponse response = new FillResponse();

		if (!gameFinished) {
			if (!board.setUserMove(columnTofill)) {
				response.setMessage("Invalid column in request");
				response.setStatus(BaseResponse.ERROR);
				return response;
			}
			if (board.isUserWinner()) {
				response.setMessage("User won");
				gameFinished = true;
			} else {
				response.setAImoveColumn(board.setAIMove());
				if (board.isAIwinner()) {
					response.setMessage("Computer won");
					gameFinished = true;
				} else if (board.isFull()) {
					response.setMessage("Game is draw");
					gameFinished = true;
				} else {
					response.setMessage("Game in Progress");
				}
			}
			board.printToConsole();
			response.setStatus(BaseResponse.SUCCESS);
		} else {
			response.setStatus(BaseResponse.ERROR);
			response.setMessage("Game finished.No more moves allowed");
		}
		return response;
	}

}
