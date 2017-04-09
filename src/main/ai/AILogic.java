package main.ai;

import main.board.ShipLocationManager;

//extremely basic, non-efficient, but functional AI class.
public class AILogic {
	private ShipLocationManager location_manager;
	
	public AILogic(){;
		this.location_manager = new ShipLocationManager();
	}
	
	//generates random coordinates, and returns in a valid attack request string
	public String getAttackRequest(){
		String letter = this.location_manager.getRandomLetter();
		int number = this.location_manager.getRandomNumber(10);
		return letter + "," + number;
	}
}
