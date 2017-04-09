package main.board;

public class Cell {
	protected char symbol;  //symbol is used to mark the board.
	private boolean is_hit; //true if this cell was shot at. if symbol is 'x' and this is true, it was a ship hit. 
							//  else it was a water hit.
	
	public Cell(){
		this.symbol = '/';
		this.is_hit = false;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
	public String toString(){
		return "" + symbol;
	}

	public boolean isHit() {
		return is_hit;
	}

	public void setHit() {
		this.is_hit = true;
		this.symbol = 'x';
	}
	
	public void setMiss(){
		this.is_hit = true;
		this.symbol = 'o';
	}

}
