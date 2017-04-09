package main.board;

public class ShipBoard extends Board{
	//title to be printed above the visual representation of the 2D array when printed
	private static final String TITLE = "          SHIP BOARD          ";
	
	//ships that exist on this board
	private Ship carrier;
	private Ship battleship;
	private Ship cruiser;
	private Ship submarine;
	private Ship destroyer;

	public ShipBoard() {
		super();
		this.board = new ShipCell [X_LENGTH][Y_LENGTH];
		initializeBoard();
	}

	public Ship getCarrier() {
		return carrier;
	}

	public void setCarrier(Ship carrier) {
		this.carrier = carrier;
	}

	public Ship getBattleship() {
		return battleship;
	}

	public void setBattleship(Ship battleship) {
		this.battleship = battleship;
	}

	public Ship getCruiser() {
		return cruiser;
	}

	public void setCruiser(Ship cruiser) {
		this.cruiser = cruiser;
	}

	public Ship getSubmarine() {
		return submarine;
	}

	public void setSubmarine(Ship submarine) {
		this.submarine = submarine;
	}

	public Ship getDestroyer() {
		return destroyer;
	}

	public void setDestroyer(Ship destroyer) {
		this.destroyer = destroyer;
	}

	@Override
	public ShipCell getCell(int x, int y) {
		return (ShipCell) this.board[x][y];
	}

	//initialize Ship with ShipCells
	@Override
	protected void initializeBoard() {
		for (int i = 0; i < X_LENGTH; i++){
			for (int j = 0; j < Y_LENGTH; j++){
				this.board[i][j] = new ShipCell();
			}
		}	
	}
	
	public String getVisualRepresentation(){
		return super.getVisualRepresentation(TITLE);
	}
}
