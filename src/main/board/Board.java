package main.board;

import java.util.Arrays;

//abstract class of a board.
//class is abstract because it contains functionality that the ShipBoard and TargetBoard handle differently.
public abstract class Board {
	protected static final int X_LENGTH = 10;
	protected static final int Y_LENGTH = 10;
	
	protected Cell [][] board;
	
	protected abstract void initializeBoard();  // sub classes need to define how they initialize the board; they are different.
	public abstract Cell getCell(int x, int y); // sub classes need to define what type of Cell they are returning. 
	
	
	//method returns a string of the 2D array. 
	//this 2D array displays the current state of the board.
	//this includes:
	//   1) where ships are
	//   2) where hits are
	//   3) where misses are
	//   4) where water is
	public String getVisualRepresentation(String title) {
		String str = title + "\n";
		String  top = "  1  2  3  4  5  6  7  8  9  10\n";
		str += top;
		
		String [] vert = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
		for (int i = 0; i < Y_LENGTH; i++){
			str += vert[i] + Arrays.toString(this.board[i]) + "\n";
		}
		return str;
	}



}
