package main.board;

//ShipCells contain ships, where TargetCells contain basic cells - just hits and misses.
public class ShipCell extends Cell{
	private Ship ship;
	
	public ShipCell(){
		super();
		this.ship = null;		
	}
	
	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
		this.symbol = this.ship.getSymbol();
	}
	
	public boolean containsShip(){
		return this.ship != null;
	}

}
