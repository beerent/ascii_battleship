package main.board;

public class Ship {
	private String name;
	private char symbol;
	private int size;
	private int hits;
	
	private ShipLocation location;
	
	public Ship(String name, char symbol, int size){
		this.name = name;
		this.size = size;
		this.symbol = symbol;
		this.hits = 0;
		this.setLocation(null);
	}
	
	public String getName() {
		return name;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getHits() {
		return hits;
	}
	
	public void addHit() {
		this.hits += 1;
	}

	public ShipLocation getLocation() {
		return location;
	}

	public void setLocation(ShipLocation location) {
		this.location = location;
	}
	
	//a ship is destroyed when hits == size
	public boolean isDestroyed(){
		return this.hits == this.size;
	}
}
