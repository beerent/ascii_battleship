package main.board;
/*
 * This class helps create new boards. It also stores the the details of each ship, such as name, size, and symbol.
 */
public class BoardBuilder {
	//define the length of each ship
	private static final int CARRIER_SIZE    = 5;
	private static final int BATTLESHIP_SIZE = 4;
	private static final int CRUISER_SIZE    = 3;
	private static final int SUBMARINE_SIZE  = 3;	
	private static final int DESTROYER_SIZE  = 2;
	
	//define the name of each ship
	private static final String CARRIER_NAME = "Carrier";
	private static final String BATTLESHIP_NAME = "Battleship";
	private static final String CRUISER_NAME = "Cruiser";
	private static final String SUBMARINE_NAME = "Submarine";
	private static final String DESTROYER_NAME = "Destroyer";
	
	//define the symbol of each ship
	private static final char CARRIER_SYMBOL = 'C';
	private static final char BATTLESHIP_SYMBOL = 'B';
	private static final char CRUISER_SYMBOL = 'R';
	private static final char SUBMARINE_SYMBOL = 'S';
	private static final char DESTROYER_SYMBOL = 'D';
	
	
	private ShipLocationManager location_manager;
	
	public BoardBuilder(){
		this.location_manager = new ShipLocationManager();
	}
	
	//this calls for each ship to be requested, stores them on a ShipBoard, then returns the ShipBoard..
	//if random_generate is true, the ship locations will be randomly generated.
	public ShipBoard newUserShipBoard(boolean random_generate) {
		ShipBoard board = newUserShipBoard();
		
		setShip(CARRIER_NAME,    CARRIER_SYMBOL,    CARRIER_SIZE, board, random_generate);
		setShip(BATTLESHIP_NAME, BATTLESHIP_SYMBOL, BATTLESHIP_SIZE, board, random_generate);
		setShip(CRUISER_NAME,    CRUISER_SYMBOL,    CRUISER_SIZE, board, random_generate);
		setShip(SUBMARINE_NAME,  SUBMARINE_SYMBOL,  SUBMARINE_SIZE, board, random_generate);
		setShip(DESTROYER_NAME, DESTROYER_SYMBOL,   DESTROYER_SIZE, board, random_generate);
		
		return board;
	}
	
	//returns an new, empty ShipBoard
	public ShipBoard newUserShipBoard(){
		ShipBoard board = new ShipBoard();
		return board;
	}
	
	//returns a new, empty TargetBoard
	public TargetBoard newUserTargetBoard() {
		TargetBoard target_board = new TargetBoard();
		return target_board;
	}
	
	//receives the location of the ship, and calls for it to be stored on board.
	private void setShip(String name, char symbol, int size, ShipBoard board, boolean random){
		Ship ship = new Ship(name, symbol, size);
		
		ShipLocation location;
		if (random) //randomly generate location
			location = this.location_manager.getRandomLocation(name, size, board);
		else
			location = this.location_manager.getUserLocation(name, size, board);
		
		ship.setLocation(location); //store location on ship
		placeShipOnBoard(ship, board); //store ship on board
	}
	
	//this method tells the board where exactly the passed in ship is located. 
	private void placeShipOnBoard(Ship ship, ShipBoard board){
		ShipLocation location = ship.getLocation();
		int x_start = location.getStartXIndex();
		int y_start = location.getStartYIndex();
		boolean vertical = location.isVertical();
		int size = ship.getSize();

		ShipCell cell;
		if(vertical){
			for(int i = 0; i < size; i++){
				cell = board.getCell(y_start + i, x_start);
				cell.setShip(ship);
			}
		}
			
		else{
			for(int i = 0; i < size; i++){
				cell = board.getCell(y_start, x_start + i);
				cell.setShip(ship);
			}
		}
		
		//store ship reference on the board.
		if (ship.getName().equals(CARRIER_NAME))
			board.setCarrier(ship);
		else if (ship.getName().equals(BATTLESHIP_NAME))
			board.setBattleship(ship);
		else if (ship.getName().equals(CRUISER_NAME))
			board.setCruiser(ship);
		else if (ship.getName().equals(SUBMARINE_NAME))
			board.setSubmarine(ship);
		else if (ship.getName().equals(DESTROYER_NAME))
			board.setDestroyer(ship);
	}

}
