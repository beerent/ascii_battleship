package main.user;

import main.ai.AILogic;
import main.board.ShipBoard;
import main.board.TargetBoard;

public class Player {
	private String player_name;
	private ShipBoard ship_board;
	private TargetBoard target_board;
	
	private boolean is_ai;
	private AILogic ai_logic;
	
	public Player(String player_name, ShipBoard ship_board, TargetBoard target_board, boolean is_ai){
		this.player_name = player_name;
		this.ship_board = ship_board;
		this.target_board = target_board;
		this.is_ai = is_ai;
		
		//AILogic is only needed if this player is an AI.
		if (this.is_ai)
			this.ai_logic = new AILogic();
	}

	public String getPlayerName() {
		return player_name;
	}

	public ShipBoard getShipBoard() {
		return ship_board;
	}

	public TargetBoard getTargetBoard() {
		return target_board;
	}
	
	public boolean isAi(){
		return this.is_ai;
	}
	
	public AILogic getAILogic(){
		return this.ai_logic;
	}

}
