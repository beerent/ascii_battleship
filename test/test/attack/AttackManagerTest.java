package test.attack;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import main.attack.Attack;
import main.attack.AttackManager;
import main.board.BoardBuilder;
import main.board.Ship;
import main.board.ShipBoard;
import main.board.TargetBoard;
import main.user.Player;

public class AttackManagerTest {
	
	/******************************/
	/*      validateAttack()      */
	/******************************/
	@Test
	public void validateAttackNull(){
		AttackManager am = new AttackManager();
		String response = null;
		assertEquals(am.validateAttack(response), false);
	}
	
	@Test
	public void validateAttackEmpty(){
		AttackManager am = new AttackManager();
		String response = "";
		assertEquals(am.validateAttack(response), false);
	}
	
	@Test
	public void validateAttackTooShort(){
		
		AttackManager am = new AttackManager();
		String response = "a";
		assertEquals(am.validateAttack(response), false);
	}
	
	@Test
	public void validateAttackNoData(){
		
		AttackManager am = new AttackManager();
		String response = ",,";
		assertEquals(am.validateAttack(response), false);
	}
	
	@Test
	public void validateAttackTooLong(){
		
		AttackManager am = new AttackManager();
		String response = "a,4,a";
		assertEquals(am.validateAttack(response), false);
	}
	
	@Test
	public void validateAttackValidLowsest(){
		
		AttackManager am = new AttackManager();
		String response = "a,1";
		assertEquals(am.validateAttack(response), true);
	}
	
	@Test
	public void validateAttackValidHighest(){
		
		AttackManager am = new AttackManager();
		String response = "j,10";
		assertEquals(am.validateAttack(response), true);
	}
	
	@Test
	public void validateAttackLetterOutOfRange(){
		
		AttackManager am = new AttackManager();
		String response = "k,1";
		assertEquals(am.validateAttack(response), false);
	}
	
	@Test
	public void validateAttackNumberOutOfRangeHigh(){
		
		AttackManager am = new AttackManager();
		String response = "a,11";
		assertEquals(am.validateAttack(response), false);
	}
	
	@Test
	public void validateAttackNumberOutOfRangeLow(){
		
		AttackManager am = new AttackManager();
		String response = "a,0";
		assertEquals(am.validateAttack(response), false);
	}
	
	@Test
	public void validateAttackNumberOutOfRangeNegative(){
		
		AttackManager am = new AttackManager();
		String response = "a,-1";
		assertEquals(am.validateAttack(response), false);
	}

	@Test
	public void validateAttackInvalidLetter(){
		
		AttackManager am = new AttackManager();
		String response = "!,1";
		assertEquals(am.validateAttack(response), false);
	}

	@Test
	public void validateAttackInvalidNumber(){
		
		AttackManager am = new AttackManager();
		String response = "a,a";
		assertEquals(am.validateAttack(response), false);
	}
	
	
	
	
	/******************************/
	/*          isHit()           */
	/******************************/
	@Test
	public void isHitTestValid(){
		BoardBuilder bb = new BoardBuilder();
		ShipBoard s = bb.newUserShipBoard();
		ShipBoard ts = bb.newUserShipBoard();
		ts.getCell(0, 0).setShip(new Ship("TEST SHIP 1", 't', 1));
		TargetBoard t = bb.newUserTargetBoard();
		Player p = new Player("test user", s, t, false);
		
		String data = "a,1";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		
		
		AttackManager am = new AttackManager();
		Attack a = am.getUserAttack(p);
		
		assertEquals(am.isHit(a, ts), true);
	}
	
	@Test
	public void isHitTestInvalid(){
		BoardBuilder bb = new BoardBuilder();
		ShipBoard s = bb.newUserShipBoard();
		ShipBoard ts = bb.newUserShipBoard();
		TargetBoard t = bb.newUserTargetBoard();
		Player p = new Player("test user", s, t, false);
		
		String data = "a,1";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		
		
		AttackManager am = new AttackManager();
		Attack a = am.getUserAttack(p);
		
		assertEquals(am.isHit(a, ts), false);
	}

	
	
	
	
	/*******************************/
	/*       attackExists()        */
	/*******************************/
	@Test
	public void hitExistsFalse(){
		BoardBuilder bb = new BoardBuilder();
		ShipBoard s = bb.newUserShipBoard();
		TargetBoard t = bb.newUserTargetBoard();

		Player p = new Player("test user", s, t, false);
		
		String data = "a,1";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		
		
		AttackManager am = new AttackManager();
		Attack a = am.getUserAttack(p);
		
		assertEquals(am.attackExists(a, p), false);
	}
	
	@Test
	public void hitExistsTrue(){
		BoardBuilder bb = new BoardBuilder();
		ShipBoard s = bb.newUserShipBoard();
		TargetBoard t = bb.newUserTargetBoard();

		Player p = new Player("test user", s, t, false);
		
		String data = "a,1";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		
		
		AttackManager am = new AttackManager();
		Attack a = am.getUserAttack(p);
		
		p.getTargetBoard().getCell(0, 0).setHit();
		
		assertEquals(am.attackExists(a, p), true);
	}


	
	
	/*******************************/
	/*      registerAttacks()      */
	/*******************************/
	
	@Test
	public void registerAttackTrue(){
		
		//attacker
		BoardBuilder bb = new BoardBuilder();
		ShipBoard s1 = bb.newUserShipBoard();
		TargetBoard t1 = bb.newUserTargetBoard();

		Player p1 = new Player("test user 1", s1, t1, false);
		
		//target
		ShipBoard s2 = bb.newUserShipBoard();
		TargetBoard t2 = bb.newUserTargetBoard();
		
		s2.getCell(0, 0).setShip(new Ship("TEST SHIP 1", 't', 1));

		Player p2 = new Player("test user 2", s2, t2, false);
		
		String data = "a,1";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		
		
		AttackManager am = new AttackManager();
		Attack a = am.getUserAttack(p1);
		
		am.registerAttacks(a, p1, p2, true);
		
		//assert attackers targetboard has attack
		assertEquals(p1.getTargetBoard().getCell(0, 0).isHit(), true);
		//assert targets shipboard has attack
		assertEquals(p2.getShipBoard().getCell(0, 0).isHit(), true);
	}
	
	@Test
	public void registerAttackFalse(){
		
		//attacker
		BoardBuilder bb = new BoardBuilder();
		ShipBoard s1 = bb.newUserShipBoard();
		TargetBoard t1 = bb.newUserTargetBoard();

		Player p1 = new Player("test user 1", s1, t1, false);
		
		//target
		ShipBoard s2 = bb.newUserShipBoard();
		TargetBoard t2 = bb.newUserTargetBoard();
		
		s2.getCell(0, 0).setShip(new Ship("TEST SHIP 1", 't', 1));

		Player p2 = new Player("test user 2", s2, t2, false);
		
		//assert attackers targetboard has attack
		assertEquals(p1.getTargetBoard().getCell(0, 0).isHit(), false);
		//assert targets shipboard has attack
		assertEquals(p2.getShipBoard().getCell(0, 0).isHit(), false);
	}
}
