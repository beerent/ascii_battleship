package main.game;

import main.attack.Attack;
import main.attack.AttackManager;
import main.board.Ship;
import main.user.Player;

/*
 * This class runs the actual game. It switches between the players, gets their attacks, handles their attacks,
 * handles destroyed ships, announces each action, and ends the game.
 */
public class GameRunner {
	private GameTracker game_tracker; //object that tracks destroys
	private Player player1;
	private Player player2;
	
	private Player attacker; //current person attacking
	private Player target;   //current person being attacked
	
	private AttackManager attack_manager; //used to validate and handle each attack
	boolean game_on; //will continue to ask players for their attack until this is false
	
	public GameRunner(Player player1, Player player2){
		this.player1 = player1;
		this.player2 = player2;
		this.game_tracker = new GameTracker(this.player1, this.player2);
		this.attack_manager = new AttackManager();
	}
	
	// This method assigns the first attacker (default is player1).
	// Also sets the game on variable to true, meaning game on. 
	public void startGame(){
		this.attacker = player1;
		this.target = player2;
		this.game_on = true;
		runGame();
	}
	
	// This method contains the while loop that continues game play. 
	// This method loops through each players turn, announcing and updating
	// along the way.
	private void runGame(){
		Attack attack;
		while(game_on){
			announcePlayerTurn();
			attack = getPlayerMove();
			handleAttack(attack);
			checkAnnounceAndUpdateSunkShip();
			checkAndUpdateGameOver();
			switchPlayer();
		}
		//target beat attacker because players switched at the end before exiting while loop
		printBoards();       //reveal each players boards
		System.out.println(this.target.getPlayerName() + " has defeated " + this.attacker.getPlayerName() + "!");
	}
	
	//prints each players boards. This is called when the game is over. 
	private void printBoards(){
		System.out.println(attacker.getPlayerName() + "'s Boards:");
		System.out.println(this.attacker.getTargetBoard().getVisualRepresentation());
		System.out.println(this.attacker.getShipBoard().getVisualRepresentation());	
		System.out.println(target.getPlayerName() + "'s Boards:");
		System.out.println(this.target.getTargetBoard().getVisualRepresentation());
		System.out.println(this.target.getShipBoard().getVisualRepresentation());	
	}
	
	//announce to the player it's their turn, as long as the player is not an AI.
	private void announcePlayerTurn(){
		System.out.println(this.attacker.getPlayerName() + ", it is your turn!");
		if(!this.attacker.isAi()){
			System.out.println("here are your boards:");
			System.out.println(this.attacker.getTargetBoard().getVisualRepresentation());
			System.out.println(this.attacker.getShipBoard().getVisualRepresentation());	
		}
	}
	
	//gets and returns the attack from the User or AI.
	private Attack getPlayerMove(){
		Attack attack;
		if(!this.attacker.isAi())
			 attack = this.attack_manager.getUserAttack(attacker);
		else
			attack = this.attack_manager.getAIAttack(attacker);
		return attack;
	}
	
	//This method handles the attack that was made. 
	//logic:
	//  Check if the attacker hit a ship. 
	//  if so, and if the ship was destroyed, tell the GameTracker
	//
	//  announce the hit or miss.      
	private void handleAttack(Attack attack){
		boolean is_hit = this.attack_manager.isHit(attack, this.target.getShipBoard());
		this.attack_manager.registerAttacks(attack, attacker, target, is_hit);
		
		if(is_hit){
			Ship ship = this.attack_manager.getShipFromAttack(attack, target);
			if(ship.isDestroyed())
				this.game_tracker.addDestroy(attacker, ship);
		}
		
		announceResult(attack, is_hit);
	}
	
	// this method simply announces if the attack was a hit or a miss.
	private void announceResult(Attack attack, boolean is_hit){
		String result = "MISS";
		if (is_hit)
			result = "HIT";
		System.out.println(this.attacker.getPlayerName() + "'s attack to cell " + attack + " is a " + result + "!");
	}
	
	// This method checks for a new destroyed ship.
	// if so, update the GameTracker and announce to players.
	private void checkAnnounceAndUpdateSunkShip(){
		if(this.game_tracker.isNewDestroy(attacker)){
			Ship ship = this.game_tracker.getLatestDestroy(attacker);
			this.game_tracker.updateKnownDestroyCount(attacker);
			System.out.println(this.attacker.getPlayerName() + " just SUNK " + this.target.getPlayerName() + "'s " + ship.getName() + "!");
		}
	}
	
	//if the attacker has sunk 5 ships, game over.
	public void checkAndUpdateGameOver(){
		if(this.game_tracker.getKnownDestroyCount(attacker) == 5)
			this.game_on = false;
	}
	
	//swap players turns
	private void switchPlayer(){
		if (this.attacker == this.player2){
			this.attacker = this.player1;
			this.target = this.player2;
		}else{
			this.attacker = player2;
			this.target = player1;
		}
	}
}
