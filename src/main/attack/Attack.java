package main.attack;

//this class represents an individual attack.
public class Attack {
	private String y;
	private int x;
	
	public Attack(String y, int x){
		this.y = y;
		this.x = x;
	}
	
	public String getY() {
		return y;
	}

	public int getX() {
		return x;
	}
	
	public String toString(){
		return y.toUpperCase() + x;
	}
}
