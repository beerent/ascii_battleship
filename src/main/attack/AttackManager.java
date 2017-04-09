package main.attack;

import main.board.ShipLocationManager;
import main.board.Ship;
import main.board.ShipBoard;
import main.board.ShipCell;
import main.user.InputManager;
import main.user.Player;

public class AttackManager {
	private InputManager input_manager;
	private ShipLocationManager location_manager;
	
	public AttackManager(){
		this.input_manager = new InputManager();
		this.location_manager = new ShipLocationManager(); 
	}
	
	
	//requests an attack from the user. 
	//this method will continue to ask the user for a request until a valid attack is entered.
	//method returns a valid, unique attack object for the user.
	public Attack getUserAttack(Player attacker){
		String response;
		boolean valid_attack = false; 
		Attack attack = null;
		
		//continue to loop until a valid attack is entered.
		while (!valid_attack){
			response = this.input_manager.getResponse(attacker.getPlayerName() + ", enter your next attack!");
			
			//if the attack entered is invalid, continue
			valid_attack = validateAttack(response);
			if(!valid_attack){
				System.out.println("invalid attack.");
				continue;
			}
			
			//if the attack has already been entered, continue
			attack = getAttackObject(response);
			valid_attack = !attackExists(attack, attacker);
			if(!valid_attack){
				System.out.println("you already attacked this cell.");
				continue;
			}
		}
		
		return attack;
	}
	
	
	//method utilizes the AILogic class to get an attack from the AI.
	public Attack getAIAttack(Player attacker){

		String request;
		boolean valid_attack = false; 
		Attack attack = null;
		
		//continue to loop until a valid attack is entered.
		while (!valid_attack){
			request = attacker.getAILogic().getAttackRequest();
			
			//if the attack entered is invalid, continue
			valid_attack = validateAttack(request);
			if(!valid_attack)
				continue;
			
			
			//if the attack has already been entered, continue
			attack = getAttackObject(request);
			valid_attack = !attackExists(attack, attacker);
			
			if(!valid_attack)
				continue;
		}
		
		return attack;
	}
	
	
	//method returns true if the attacker has made this attack before.
	public boolean attackExists(Attack attack, Player attacker){
		int y_int = this.location_manager.letterToInt(attack.getY());
		boolean is_hit = attacker.getTargetBoard().getCell(y_int - 1, attack.getX() - 1).isHit();
		return is_hit;
	}
	
	
	//turns a response string into an attack object.
	//this method is to only be called once the response has been validated.
	private Attack getAttackObject(String response){
		String [] response_split = response.split(",");
		String y = response_split[0];
		int x = Integer.parseInt(response_split[1]);
		return new Attack(y, x);
	}
	
	
	//this method returns true if the attack request is valid.
	//an attack is valid if:
	//
	//  1) it is not null
	//  2) if there are exactly two elements in the request
	//  3) if the letter portion of the request is between A % J
	//  4) if the integer portion of the request is between 1 & 10
	public boolean validateAttack(String response){
		if (response == null)
			return false;
		
		String [] response_split = response.split(",");
		if(response_split.length != 2)
			return false;
		
		if(!this.location_manager.isValidLetter(response_split[0]))
			return false;

		if(!this.location_manager.isInteger(response_split[1]))
			return false;
		
		//safe to convert to integer at this point.
		int i = Integer.parseInt(response_split[1]);
		if(!this.location_manager.isValidInteger(i))
			return false;
	
		return true;
	}
	

	//returns true if the attacker has entered an attack
	//on a cell that contains one of the opponent's ship
	public boolean isHit(Attack attack, ShipBoard ship_board) {
		int y_int = this.location_manager.letterToInt(attack.getY());
		return ship_board.getCell(y_int - 1, attack.getX() - 1).containsShip();
	}

	
	//method registers an attack.
	//registration includes:
	//   1) updating the attacker's target board of the attack.
	//   2) updating the target's ship board of the attack.
	//   3) updating the target's ship that if it was hit, if it is a hit.
	public void registerAttacks(Attack attack, Player attacker, Player target, boolean is_hit) {
		int y_int = this.location_manager.letterToInt(attack.getY()) - 1;
		if(is_hit){
			attacker.getTargetBoard().getCell(y_int, attack.getX() - 1).setHit(); //mark attackers board
			target.getShipBoard().getCell(y_int, attack.getX() - 1).setHit();     //mark targets board
			Ship ship = getShipFromAttack(attack, target);
			ship.addHit();
			
		}else{
			attacker.getTargetBoard().getCell(y_int, attack.getX() - 1).setMiss();
			target.getShipBoard().getCell(y_int, attack.getX() - 1).setMiss();
		}
	}
	
	//returns a ship associated with the coordinates of an attack
	//if the ship doesn't exist, null will be returned
	public Ship getShipFromAttack(Attack attack, Player target){
		int y_int = this.location_manager.letterToInt(attack.getY()) - 1;
		ShipCell cell = target.getShipBoard().getCell(y_int, attack.getX() - 1);
		Ship ship = cell.getShip();
		return ship;
	}
}
