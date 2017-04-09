package test.game;

import static org.junit.Assert.*;

import org.junit.Test;

import main.board.BoardBuilder;
import main.board.Ship;
import main.board.ShipBoard;
import main.board.TargetBoard;
import main.game.GameTracker;
import main.user.Player;

public class GameTrackerTest {

	/******************************/
	/*       isNewDestroy()       */
	/******************************/
	@Test
	public void isNewDestroyTrue(){
		
		//attacker
		BoardBuilder bb = new BoardBuilder();
		ShipBoard s1 = bb.newUserShipBoard();
		TargetBoard t1 = bb.newUserTargetBoard();
		Player p1 = new Player("test user 1", s1, t1, false);
		
		//target
		ShipBoard s2 = bb.newUserShipBoard();
		TargetBoard t2 = bb.newUserTargetBoard();
		Player p2 = new Player("test user 2", s2, t2, false);
		
		GameTracker gt = new GameTracker(p1, p2);
		
		Ship ship = new Ship("TEST SHIP 1", 't', 1);
		gt.addDestroy(p1, ship);
		
		boolean is_new_destroy = gt.isNewDestroy(p1);
		
		assertEquals(is_new_destroy, true);
	}
	
	@Test
	public void isNewDestroyFalse(){
		
		//attacker
		BoardBuilder bb = new BoardBuilder();
		ShipBoard s1 = bb.newUserShipBoard();
		TargetBoard t1 = bb.newUserTargetBoard();
		Player p1 = new Player("test user 1", s1, t1, false);
		
		//target
		ShipBoard s2 = bb.newUserShipBoard();
		TargetBoard t2 = bb.newUserTargetBoard();
		Player p2 = new Player("test user 2", s2, t2, false);
		
		GameTracker gt = new GameTracker(p1, p2);
		
		boolean is_new_destroy = gt.isNewDestroy(p1);
		
		assertEquals(is_new_destroy, false);
	}
	
	
	
	
	/******************************/
	/*      getDestroyCount()     */
	/******************************/
	@Test
	public void getDestroyCountBeforeUpdate(){
		
		//attacker
		BoardBuilder bb = new BoardBuilder();
		ShipBoard s1 = bb.newUserShipBoard();
		TargetBoard t1 = bb.newUserTargetBoard();
		Player p1 = new Player("test user 1", s1, t1, false);
		
		//target
		ShipBoard s2 = bb.newUserShipBoard();
		TargetBoard t2 = bb.newUserTargetBoard();
		Player p2 = new Player("test user 2", s2, t2, false);
		
		GameTracker gt = new GameTracker(p1, p2);
		
		Ship ship = new Ship("TEST SHIP 1", 't', 1);
		gt.addDestroy(p1, ship);
		
		int destroy_count = gt.getKnownDestroyCount(p1);
		
		assertEquals(destroy_count, 0);
	}
	
	
	@Test
	public void getDestroyCountAfterUpdate(){
		
		//attacker
		BoardBuilder bb = new BoardBuilder();
		ShipBoard s1 = bb.newUserShipBoard();
		TargetBoard t1 = bb.newUserTargetBoard();
		Player p1 = new Player("test user 1", s1, t1, false);
		
		//target
		ShipBoard s2 = bb.newUserShipBoard();
		TargetBoard t2 = bb.newUserTargetBoard();
		Player p2 = new Player("test user 2", s2, t2, false);
		
		GameTracker gt = new GameTracker(p1, p2);
		
		Ship ship = new Ship("TEST SHIP 1", 't', 1);
		gt.addDestroy(p1, ship);
		gt.updateKnownDestroyCount(p1);
		
		int destroy_count = gt.getKnownDestroyCount(p1);
		
		assertEquals(destroy_count, 1);
	}
	
	
	
	
	/******************************/
	/*     getLatestDestroy()     */
	/******************************/
	@Test
	public void getLatestDestroy(){
		
		//attacker
		BoardBuilder bb = new BoardBuilder();
		ShipBoard s1 = bb.newUserShipBoard();
		TargetBoard t1 = bb.newUserTargetBoard();
		Player p1 = new Player("test user 1", s1, t1, false);
		
		//target
		ShipBoard s2 = bb.newUserShipBoard();
		TargetBoard t2 = bb.newUserTargetBoard();
		Player p2 = new Player("test user 2", s2, t2, false);
		
		GameTracker gt = new GameTracker(p1, p2);
		
		Ship ship = new Ship("TEST SHIP 1", 't', 1);
		gt.addDestroy(p1, ship);
		
		Ship s = gt.getLatestDestroy(p1);
		
		assertEquals(s, ship);
	}

}
