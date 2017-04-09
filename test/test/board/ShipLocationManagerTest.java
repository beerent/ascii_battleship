package test.board;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;


import main.board.BoardBuilder;
import main.board.ShipLocationManager;
import main.board.Ship;
import main.board.ShipBoard;

public class ShipLocationManagerTest {

	public static void main(String args[]) {
		  JUnitCore junit = new JUnitCore();
		  junit.addListener(new TextListener(System.out));
		  Result result = junit.run(ShipLocationManagerTest.class); // Replace "SampleTest" with the name of your class
		  if (result.getFailureCount() > 0) {
		    System.out.println("Test failed.");
		    System.exit(1);
		  } else {
		    System.out.println("Test finished successfully.");
		    System.exit(0);
		  }
	}

	
	/*
	 * 	public boolean validateLocation(int size, ShipBoard board, String location_str){
	 */
	
	/*******************************/
	/*     validateLocation()      */
	/*******************************/
	@Test
	public void validateLocationNull(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 2;
		ShipBoard s = bb.newUserShipBoard();
		String location = null;
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, false);
	}
	
	@Test
	public void validateLocationEmpty(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 2;
		ShipBoard s = bb.newUserShipBoard();
		String location = "";
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, false);
	}
	
	@Test
	public void validateLocationTooShort(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 2;
		ShipBoard s = bb.newUserShipBoard();
		String location = ",,";
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, false);
	}
	
	@Test
	public void validateLocationEmptyData(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 2;
		ShipBoard s = bb.newUserShipBoard();
		String location = ",,";
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, false);
	}
	
	@Test
	public void validateLocationTooMuchData(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 2;
		ShipBoard s = bb.newUserShipBoard();
		String location = "a,1,v,4";
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, false);
	}
	
	@Test
	public void validateLocationInvalidLetter(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 2;
		ShipBoard s = bb.newUserShipBoard();
		String location = "z,2,v";
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, false);
	}
	
	@Test
	public void validateLocationInvalidNumber(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 2;
		ShipBoard s = bb.newUserShipBoard();
		String location = "a,a,v";
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, false);
	}
	
	@Test
	public void validateLocationNegativeNumber(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 2;
		ShipBoard s = bb.newUserShipBoard();
		String location = "a,-1,v";
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, false);
	}
	
	@Test
	public void validateLocationZeroNumber(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 2;
		ShipBoard s = bb.newUserShipBoard();
		String location = "a,0,v";
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, false);
	}
	
	@Test
	public void validateLocationTooHighNumber(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 2;
		ShipBoard s = bb.newUserShipBoard();
		String location = "a,11,v";
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, false);
	}
	
	@Test
	public void validateLocationInvalidDirection(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 2;
		ShipBoard s = bb.newUserShipBoard();
		String location = "a,1,t";
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, false);
	}
	
	@Test
	public void validateLocationInvalidDirectionNumber(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 2;
		ShipBoard s = bb.newUserShipBoard();
		String location = "a,1,t";
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, false);
	}
	
	@Test
	public void validateLocationHitWallVertical(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 2;
		ShipBoard s = bb.newUserShipBoard();
		String location = "j,1,v";
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, false);
	}
	
	@Test
	public void validateLocationMissWallVertical(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 2;
		ShipBoard s = bb.newUserShipBoard();
		String location = "i,1,v";
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, true);
	}
	
	@Test
	public void validateLocationHitWallHorizontal(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 2;
		ShipBoard s = bb.newUserShipBoard();
		String location = "a,10,h";
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, false);
	}
	
	@Test
	public void validateLocationMissWallHorizontal(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 2;
		ShipBoard s = bb.newUserShipBoard();
		String location = "a,9,v";
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, true);
	}
	
	@Test
	public void validateLocationHitShipVerticalOnVertical(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 3;
		ShipBoard s = bb.newUserShipBoard();
		String location = "c,1,v";
		Ship ship = new Ship("SHIP", 'S', size);
		
		s.getCell(0, 0).setShip(ship);
		s.getCell(1, 0).setShip(ship);
		s.getCell(2, 0).setShip(ship);
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, false);
	}
	
	@Test
	public void validateLocationHitShipVerticalAfterVertical(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 3;
		ShipBoard s = bb.newUserShipBoard();
		String location = "d,1,v";
		Ship ship = new Ship("SHIP", 'S', size);
		
		s.getCell(0, 0).setShip(ship);
		s.getCell(1, 0).setShip(ship);
		s.getCell(2, 0).setShip(ship);
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, true);
	}

	@Test
	public void validateLocationHitShipHorizontalOnHorizontal(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 3;
		ShipBoard s = bb.newUserShipBoard();
		String location = "a,3,h";
		Ship ship = new Ship("SHIP", 'S', size);
		
		s.getCell(0, 0).setShip(ship);
		s.getCell(0, 1).setShip(ship);
		s.getCell(0, 2).setShip(ship);
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, false);
	}
	
	@Test
	public void validateLocationHitShipHorizontalAfterHorizontal(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 3;
		ShipBoard s = bb.newUserShipBoard();
		String location = "a,4,h";
		Ship ship = new Ship("SHIP", 'S', size);
		
		s.getCell(0, 0).setShip(ship);
		s.getCell(0, 1).setShip(ship);
		s.getCell(0, 2).setShip(ship);
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, true);
	}
	
	@Test
	public void validateLocationHitShipHorizontalCrossVertical(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 3;
		ShipBoard s = bb.newUserShipBoard();
		String location = "c,3,h";
		Ship ship = new Ship("SHIP", 'S', size);
		
		s.getCell(2, 3).setShip(ship);
		s.getCell(3, 3).setShip(ship);
		s.getCell(4, 3).setShip(ship);
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, false);
	}
	
	@Test
	public void validateLocationHitShipHorizontalUnderVertical(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 3;
		ShipBoard s = bb.newUserShipBoard();
		String location = "f,3,h";
		Ship ship = new Ship("SHIP", 'S', size);
		
		s.getCell(2, 3).setShip(ship);
		s.getCell(3, 3).setShip(ship);
		s.getCell(4, 3).setShip(ship);
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, true);
	}
	
	
	@Test
	public void validateLocationHitShipVerticalCrossHorizontal(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 3;
		ShipBoard s = bb.newUserShipBoard();
		String location = "b,3,v";
		Ship ship = new Ship("SHIP", 'S', size);
		
		s.getCell(3, 0).setShip(ship);
		s.getCell(3, 1).setShip(ship);
		s.getCell(3, 2).setShip(ship);
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, false);
	}
	
	@Test
	public void validateLocationHitShipVerticalUnderHorizontal(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 3;
		ShipBoard s = bb.newUserShipBoard();
		String location = "e,3,v";
		Ship ship = new Ship("SHIP", 'S', size);
		
		s.getCell(3, 0).setShip(ship);
		s.getCell(3, 1).setShip(ship);
		s.getCell(3, 2).setShip(ship);
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, true);
	}

	
	@Test
	public void validateLocationValid(){
		ShipLocationManager lm = new ShipLocationManager();
		BoardBuilder bb = new BoardBuilder();
		
		int size = 2;
		ShipBoard s = bb.newUserShipBoard();
		String location = "a,1,v";
		
		boolean valid = lm.validateLocation(size, s, location);		
		assertEquals(valid, true);
	}

}
