package main.board;

public class TargetBoard extends Board{
	//title to be printed above the visual representation of the 2D array when printed
	private static final String TITLE = "         TARGET BOARD         ";

	public TargetBoard() {
		super();
		this.board = new TargetCell [X_LENGTH][Y_LENGTH];
		initializeBoard();
	}
	

	//overrides parent classes abstract method to return a
	//TargetCell object
	@Override
	public TargetCell getCell(int x, int y) {
		return (TargetCell) this.board[x][y];
	}
	

	//overrides the abstract method to ensure
	//this board is made up of TargetCells
	@Override
	protected void initializeBoard() {
		for (int i = 0; i < X_LENGTH; i++){
			for (int j = 0; j < Y_LENGTH; j++){
				this.board[i][j] = new TargetCell();
			}
		}
	}
	
	
	//returns a visual representation of the board, 
	//passing in the target board title
	public String getVisualRepresentation(){
		return super.getVisualRepresentation(TITLE);
	}

}
