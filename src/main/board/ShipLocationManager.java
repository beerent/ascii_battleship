package main.board;

import java.util.Arrays;
import java.util.Random;

import main.user.InputManager;

public class ShipLocationManager {
	private static final String [] VALID_STRS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}; //valid letter inputs
	private static final String [] VALID_DIRECTIONS = {"V", "H"}; //valid direction inputs

	private InputManager input_manager;
	
	public ShipLocationManager(){
		this.input_manager = new InputManager();
	}

	// Returns a random ship location.
	// Continues to loop until a valid location is generated.
	public ShipLocation getRandomLocation(String ship_name, int ship_size, ShipBoard board){
		boolean valid_location = false;
		String random_location = "";
		while(!valid_location){
			
			random_location = getRandomLocationString(); 
			valid_location = validateLocation(ship_size, board, random_location);
		}
		
		//at this point, location is valid
		return stringToLocation(random_location);
	}
	
	//generates a random letter, number and direction,
	//and returns them combined as a string in valid request form.
	private String getRandomLocationString(){
		String random_letter = getRandomLetter();
		String random_direction = VALID_DIRECTIONS[getRandomNumber(2)-1];
		int random_number = getRandomNumber(10);
		return random_letter + "," + random_number + "," + random_direction;
		
	}
	
	//returns a random letter from VALID_STRS
	public String getRandomLetter(){
		return VALID_STRS[getRandomNumber(10) - 1];
	}
	
	//returns a random number
	public int getRandomNumber(int max){
		Random rand = new Random();
		return rand.nextInt(max) + 1;
	}
	
	//Prompts user for a ship location.
	//If location is invalid, request continues to ask until a valid location is entered.
	//When the location is returned, it is guaranteed it's a valid location for the ship.
	public ShipLocation getUserLocation(String ship_name, int ship_size, ShipBoard board){
		//print current board
		System.out.println("Here is your current board: ");
		System.out.println(board.getVisualRepresentation());
		
		System.out.println("We need to place your " + ship_name + ", size " + ship_size + ".");
		
		String response = "";
		boolean valid_location = false;
		
		while(!valid_location){
			response = this.input_manager.getResponse("Please enter your desired location for your " + ship_name + ".");
			valid_location = validateLocation(ship_size, board, response);
			if(!valid_location)
				System.out.println("Invalid location.");
		}
		
		//at this point, location is valid
		return stringToLocation(response);
	}
	
	//this takes a pre-verified string location request, and returns
	//a Location object.
	private ShipLocation stringToLocation(String location_str){
		String [] entry_split = location_str.split(",");

		String y = entry_split[0].toUpperCase();
		//subtract 1 from each because it's index 0
		int y_int = letterToInt(y) - 1;
		int x = Integer.parseInt(entry_split[1]) - 1;
		String v = entry_split[2].toUpperCase();
		boolean is_vertical = v.equals("V");
		
		return new ShipLocation(x, y_int, is_vertical);
	}
	
	//breaks down the location request, and returns true if it's valid.
	public boolean validateLocation(int size, ShipBoard board, String location_str){
		//false if null
		if (location_str == null)
			return false;
		
		//false if not length of 3
		String [] entry_split = location_str.split(",");
		if (entry_split.length != 3)
			return false;
		
		//false if invalid letter
		String y = entry_split[0].toUpperCase();
		if (!isValidLetter(y))
			return false;
		
		//false if not integer
		int x;
		if (!isInteger(entry_split[1]))
			return false;
		x = Integer.parseInt(entry_split[1]);
		
		//false if invalid integer
		if (!isValidInteger(x))
			return false;
		
		//false if invalid direction
		String v = entry_split[2].toUpperCase();
		if (!v.equals("V") && !v.equals("H"))
			return false;
		
		//at this point, response is valid. now check if response will hit wall or other ship
		
		//check if response will hit wall, false if true
		boolean hit_wall = hitWall(size, board, x, y, v);
		if(hit_wall)
			return false;
		
		//check if response will hit ship, false if true
		boolean hit_ship = hitShip(size, board, x, y, v);
		return !hit_ship;
	}
	
	//returns true if location potential ship location will hit wall.
	private boolean hitWall(int size, ShipBoard board, int x, String y, String v){
		int y_int = letterToInt(y);
		
		if(v.equals("V"))
			return y_int + size - 1 > 10;			
		else
			return x + size -1 > 10;
	}

	//returns false if potential ship location will hit a ship
	private boolean hitShip(int size, ShipBoard board, int x, String y, String v){
		int y_int = letterToInt(y);
		
		//iterate through each potential cell checking if a ship is there.
		//if a ship is found, return true, because the potential ship location 
		//will have landed on a ship.
		
		ShipCell cell;
		if(v.equals("V")){
			for(int i = 0; i < size; i++){
				cell = board.getCell(y_int + i - 1, x - 1);
				if (cell.containsShip())
					return true;
			}
		}else{
			for(int i = 0; i < size; i++){
				cell = board.getCell(y_int - 1, x + i - 1);
				if (cell.containsShip())
					return true;
			}
		}
		return false;		
	}
	
	//returns true if the letter is in the VALID_STRS array.
	public boolean isValidLetter(String letter){
		return Arrays.asList(VALID_STRS).contains(letter.toUpperCase());
	}
	
	//returns true if the direction is in the VALID_DIRECTIONS array.
	public boolean isValidDirection(String letter){
		return Arrays.asList(VALID_DIRECTIONS).contains(letter.toUpperCase());
	}
	
	//returns true if integer is between 1 and 10.
	public boolean isValidInteger(int x){
		return x >= 1 && x <= 10;
	}
	
	//returns true if string is an integer.
	public boolean isInteger(String x){
		try{
			Integer.parseInt(x);
		}catch(NumberFormatException e){
			return false;
		}	
		return true;
	}
	
	//returns index of the passed in letter.
	public int letterToInt(String letter){
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		return alphabet.indexOf(letter.toLowerCase()) + 1;
	}
	
	//returns the letter in VALID_STRS by index
	public String getLetterByIndex(int i){
		if(i >= 0 && i <=9 )
			return VALID_STRS[i];
		return null;
	}

}
