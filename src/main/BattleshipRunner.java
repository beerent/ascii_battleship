package main;

import main.board.BoardBuilder;
import main.board.ShipBoard;
import main.board.TargetBoard;
import main.game.GameRunner;
import main.user.InputManager;
import main.user.Player;
import main.user.UserManager;

/*
 * This class is used to setup and start the game. 
 * This is the main of the application.
 */
public class BattleshipRunner {
	ShipBoard player_1_ship_board;
	ShipBoard player_2_ship_board;
	TargetBoard player_1_target_board;
	TargetBoard player_2_target_board;
	
	boolean player_1_ai;
	boolean player_2_ai;
	
	boolean player_1_random;
	boolean player_2_random;
	
	String username1;
	String username2;
	
	public BattleshipRunner(){
		this.player_1_ai = false;
		this.player_2_ai = false;
		this.player_1_random = false;
		this.player_2_random = false;
	}
	
	//sets up the game. gathers user input to determine the number of players, 
	//and if they'd like to setup their own boards or not.
	public void initializeGame(){
		UserManager user_manager = new UserManager();
		InputManager input_manager = new InputManager();

		int player_count = user_manager.getPlayerCount();
		
		if(player_count == 0){
			player_1_ai = true;
			player_1_random = true;
			username1 = "Brent's AI 1";

			player_2_ai = true;
			player_2_random = true;
			username2 = "Brent's AI 2";
			
		}else if(player_count == 1){
			player_1_ai = false;
			username1 = user_manager.getUsername(1);
			String response = input_manager.getResponse(username1 + ", would you like to have a board randomly generated for you? "
					+ "Enter 'y' or 'Y' for yes, or anything else for no.");
			if(response.toUpperCase().equals("Y"))
				player_1_random = true;

			player_2_ai = true;
			player_2_random = true;
			username2 = "Brent's AI 1";
		}else{
			player_1_ai = false;
			username1 = user_manager.getUsername(1);
			String response1 = input_manager.getResponse(username1 + ", would you like to have a board randomly generated for you? "
					+ "Enter 'y' or 'Y' for yes, or anything else for no.");
			if(response1.toUpperCase().equals("Y"))
				player_1_random = true;

			player_2_ai = false;
			username2 = user_manager.getUsername(2);
			String response2 = input_manager.getResponse(username2 + ", would you like to have a board randomly generated for you? "
					+ "Enter 'y' or 'Y' for yes, or anything else for no.");
			if(response2.toUpperCase().equals("Y"))
				player_2_random = true;
		}
	}
	
	//creates players and boards, and starts the game
	public void startGame(){
		BoardBuilder board_builder = new BoardBuilder();
		player_1_ship_board = board_builder.newUserShipBoard(player_1_random);
		player_1_target_board = board_builder.newUserTargetBoard();
		Player player1 = new Player(username1, player_1_ship_board, player_1_target_board, player_1_ai);
		
		player_2_ship_board = board_builder.newUserShipBoard(player_2_random);
		player_2_target_board = board_builder.newUserTargetBoard();
		Player player2 = new Player(username2, player_2_ship_board, player_2_target_board, player_2_ai);
		
		//start game
		GameRunner game_runner = new GameRunner(player1, player2);
		game_runner.startGame();
	}
	
	
	
	
	

	/************************************/
	/***********    MAIN     ************/
	/************************************/
	public static void main(String[] args) {
		BattleshipRunner battleship_runner = new BattleshipRunner();
		battleship_runner.initializeGame();
		battleship_runner.startGame();
		
	}
}
