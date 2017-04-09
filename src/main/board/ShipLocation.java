package main.board;

/*
 * This class is used with a ship in order to determine where a ship is located on the board. 
 * The front of the ship is located at start_x_index and start_y_index. 
 * If is_vertical is true, the ship is vertical. else, ship is horizontal.
 */
public class ShipLocation {

	private Integer start_x_index;
	private Integer start_y_index;
	
	private Boolean is_vertical;
	
	public ShipLocation(int x, int y, boolean is_vertical){
		this.start_x_index = x;
		this.start_y_index = y;
		this.is_vertical = is_vertical;
	}
	
	public Integer getStartXIndex() {
		return start_x_index;
	}

	public Integer getStartYIndex() {
		return start_y_index;
	}
	
	public Boolean isVertical() {
		return is_vertical;
	}
	
	public void setVertical(boolean is_vertical) {
		this.is_vertical = is_vertical;
	}
	
	public boolean isPositioned(){
		return this.start_x_index != null && this.start_y_index != null && this.is_vertical != null;
	}
	
	public String toString(){
		return "" + this.start_x_index + " " + this.start_y_index + " isvertical: " + this.is_vertical;
	}

}
