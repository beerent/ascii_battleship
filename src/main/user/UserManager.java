package main.user;

/*
 * This class manages certain user input. 
 * This is mainly used to setup the game, by getting
 * the amount of players, and the names of the players.
 */
public class UserManager {
	private InputManager input_manager;
	
	public UserManager(){
		this.input_manager = new InputManager();
	}
	
	//returns a valid username
	//prompts the user for a username, and repeats
	//the process until user enters 1 or more characters.
	public String getUsername(int player){
		String username = this.input_manager.getResponse("Player "+ player +", please enter your name.");
		boolean valid_username = validUsername(username);
		
		while(!valid_username){	
			System.out.println("Invalid username, please enter one or more characters.");
			username = this.input_manager.getResponse("Please enter your name.");
			valid_username = validUsername(username);
		}
		
		return username;
	}
	
	//returns true if the username is valid
	public boolean validUsername(String username){
		if (username == null) return false;
		if (username.length() == 0) return false;
		
		return true;
	}

	//returns a valid player count. 
	//prompts the user for a count, and repeats
	//until a valid number (1 or 2) is passed in.
	public int getPlayerCount() {
		String count = this.input_manager.getResponse("How many players, '0' (two AI's), '1' (one AI) or '2' (no AI's)?");
		boolean valid_count = validCount(count);
		
		while(!valid_count){	
			System.out.println("Invalid player count, please enter '0', '1' or '2'.");
			count = this.input_manager.getResponse("How many players, '0' (two AI's), '1' (one AI) or '2' (no AI's)?");
			valid_count = validCount(count);
		}
		
		return Integer.parseInt(count);
	}

	//valid count is 0, 1 or 2.
	public boolean validCount(String count) {
		if (count == null)
			return false;
		
		return count.equals("0") || count.equals("1") || count.equals("2");
	}
}
