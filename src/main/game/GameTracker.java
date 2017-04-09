package main.game;

import java.util.ArrayList;
import java.util.HashMap;

import main.board.Ship;
import main.user.Player;

/*
 * This class keeps track of the overall gameplay. After every turn, the GameRunner feeds and pulls info from 
 * this class. 
 * 
 * GameTracker holds both a destroy count, and which ships each player has destroyed.
 * 
 * A key concept of this class is the difference between known_player_ship_destroy_count and player_ship_destroy_map.size():
 *    A new destroy is determined when there is a difference of 1 between the two values.
 *    When a new destroy is found, it is announced by the GameRunner, 
 *    and then updated, so there is no difference between the two values.
 */
public class GameTracker {
	private HashMap<Player, Integer> known_player_ship_destroy_count; //attacker : attacker destroy count
	private HashMap<Player, ArrayList<Ship>> player_ship_destroy_map; //attacker : ships attacker destroyed

	
	public GameTracker(Player player1, Player player2){
		this.known_player_ship_destroy_count = new HashMap<Player, Integer>();
		this.known_player_ship_destroy_count.put(player1, 0);
		this.known_player_ship_destroy_count.put(player2, 0);

		this.player_ship_destroy_map = new HashMap<Player, ArrayList<Ship>>();
		this.player_ship_destroy_map.put(player1, new ArrayList<Ship>());
		this.player_ship_destroy_map.put(player2, new ArrayList<Ship>());
	}
	
	//adds a destroyed ship to a players map
	public void addDestroy(Player player, Ship ship){
		this.player_ship_destroy_map.get(player).add(ship);
	}
	
	//returns true if the ArrayList size is greater than the Integer.
	public boolean isNewDestroy(Player player){
		int current_destroy_count = this.known_player_ship_destroy_count.get(player);
		int updated_destroy_count = this.player_ship_destroy_map.get(player).size();
		return updated_destroy_count > current_destroy_count;
	}
	
	//returns the known destroy count
	public int getKnownDestroyCount(Player player){
		return this.known_player_ship_destroy_count.get(player);
	}
	
	//returns the most recent ship destroyed by the passed in player
	public Ship getLatestDestroy(Player player){
		return this.player_ship_destroy_map.get(player).get(this.player_ship_destroy_map.get(player).size() - 1);
	}
	
	//updates the known destroy count for the passed in player
	public void updateKnownDestroyCount(Player player){
		int updated_destroy_count = this.player_ship_destroy_map.get(player).size();
		this.known_player_ship_destroy_count.put(player, updated_destroy_count);
	}	
}
