import java.util.*;
import java.util.Random;

public class main {
	private final static int size = 3;
	private static String[][] board;
	private final String player1="X";
	private final String player2="O";
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		boolean gameStart=true;
		System.out.println("Hello player, the game has begin!");
		System.out.println("You can start a new game by entering 'RESET'");
		String input;
		while(gameStart) {
			gameStart=false;
			System.out.println("Please choose which mode you want to play:");
			System.out.println("Enter 1 for play with another player.");
			System.out.println("Enter 2 for play against a computer opponent.");
			initialise();
			int chooseMode=0;
			try {
				while(chooseMode==0) {
					input = in.nextLine();
					if(input.equals("1")) {
						System.out.println("You choose to play with another player.");
						chooseMode=1;
						printBoard();
					}else if(input.equals("2")) {
						System.out.println("You choose to play with computer agent.");
						chooseMode=2;
						printBoard();
					}else if(input.equals("RESET")) {
						System.out.println("NOTE : Game has been reset!");
						gameStart=true;
						break;
					}else {
						System.out.println("Invalid input, please choose which mode you want to play.");
					}
				}
				
				if(chooseMode==1) {
					int turn=0;
					while(testWinner().equals("drawn")) {
						if(turn==size*size) {
							System.out.println("It's a draw! Thanks for playing.");
							return;
						}
						if(turn%2==0) {
							System.out.println("Turn for Player 1 with symbol X. Enter a slot number to place X in:");
							input = in.nextLine();
							if(input.equals("RESET")) {
								System.out.println("NOTE : Game has been reset!");
								gameStart=true;
								break;
							}else if(Integer.parseInt(input)%size==0 && !input.equals("0")) {
								if(!board[Integer.parseInt(input)/size-1][size-1].equals("X") 
										&& !board[Integer.parseInt(input)/size-1][size-1].equals("O")) {
									board[Integer.parseInt(input)/size-1][size-1]="X";
									turn+=1;
									printBoard();
								}else {
									System.out.println("Invalid input, please try place X in a valid position");
								}	
							}else if(!board[Integer.parseInt(input)/size][Integer.parseInt(input)%size-1].equals("X") 
									&& !board[Integer.parseInt(input)/size][Integer.parseInt(input)%size-1].equals("O")
									&& !input.equals("0")) {
								board[Integer.parseInt(input)/size][Integer.parseInt(input)%size-1]="X";
								turn+=1;
								printBoard();
							}else {
								System.out.println("Invalid input, please try place X in a valid position");
							}
						}else if(turn%2==1) {
							System.out.println("Turn for Player 2 with symbol O. Enter a slot number to place O in:");
							input = in.nextLine();
							if(input.equals("RESET")) {
								System.out.println("NOTE : Game has been reset!");
								gameStart=true;
								break;
							}else if(Integer.parseInt(input)%size==0 && !input.equals("0")) {
								if(!board[Integer.parseInt(input)/size-1][size-1].equals("X") 
										&& !board[Integer.parseInt(input)/size-1][size-1].equals("O")) {
									board[Integer.parseInt(input)/size-1][size-1]="O";
									turn+=1;
									printBoard();
								}else {
									System.out.println("Invalid input, please try place O in a valid position");
								}	
							}else if(!board[Integer.parseInt(input)/size][Integer.parseInt(input)%size-1].equals("X") 
									&& !board[Integer.parseInt(input)/size][Integer.parseInt(input)%size-1].equals("O")
									&& !input.equals("0")) {
								board[Integer.parseInt(input)/size][Integer.parseInt(input)%size-1]="O";
								turn+=1;
								printBoard();
							}else {
								System.out.println("Invalid input, please try place O in a valid position");
							}
						}
					}
					if(!gameStart) {
						System.out.println("Game Finish!");
						if(testWinner().equals("X")){
							System.out.println("Player 1 with Symbol X wins.");
						}else {
							System.out.println("Player 2 with Symbol O wins.");
						}
						return;
					}
				}else if(chooseMode==2){
					int turn=0;
					int[] record=new int[size*size];
					for(int i=1;i<=size*size;i++) {
						record[i-1]=i;
					}
					while(testWinner().equals("drawn")) {
						if(turn==size*size) {
							System.out.println("It's a draw! Thanks for playing.");
							return;
						}
						if(turn%2==0) {
							System.out.println("Turn for Player with symbol X. Enter a slot number to place X in:");
							input = in.nextLine();
							if(input.equals("RESET")) {
								System.out.println("NOTE : Game has been reset!");
								gameStart=true;
								break;
							}else if(Integer.parseInt(input)%size==0 && !input.equals("0")) {
								if(!board[Integer.parseInt(input)/size-1][size-1].equals("X") 
										&& !board[Integer.parseInt(input)/size-1][size-1].equals("O")) {
									board[Integer.parseInt(input)/size-1][size-1]="X";
									turn+=1;
									int[] memory=record;
									record = new int[record.length-1];
									int j=0;
									for(int i : memory) {
										if(i!=Integer.parseInt(input)) {
											record[j]=i;
											j++;
										}
									}
									printBoard();
								}else {
									System.out.println("Invalid input, please try place X in a valid position");
								}	
							}else if(!board[Integer.parseInt(input)/size][Integer.parseInt(input)%size-1].equals("X") 
									&& !board[Integer.parseInt(input)/size][Integer.parseInt(input)%size-1].equals("O")
									&& !input.equals("0")) {
								board[Integer.parseInt(input)/size][Integer.parseInt(input)%size-1]="X";
								turn+=1;
								printBoard();
							}else {
								System.out.println("Invalid input, please try place X in a valid position");
							}
						}else if(turn%2==1) {
							System.out.println("Turn for computer opponent with symbol O.");
							int random = record[(int) (Math.random() * record.length)];
							if(random%size==0) {
								if(!board[random/size-1][size-1].equals("X") 
										&& !board[random/size-1][size-1].equals("O")) {
									board[random/size-1][size-1]="O";
									turn+=1;
									int[] memory=record;
									record = new int[record.length-1];
									int j=0;
									for(int i : memory) {
										if(i!=random) {
											record[j]=i;
											j++;
										}
									}
									printBoard();
								}else {
									System.out.println("Invalid input, please try place O in a valid position");
								}	
							}else if(!board[random/size][random%size-1].equals("X") 
									&& !board[random/size][random%size-1].equals("O")) {
								board[random/size][random%size-1]="O";
								turn+=1;
								int[] memory=record;
								record = new int[record.length-1];
								int j=0;
								for(int i : memory) {
									if(i!=random) {
										record[j]=i;
										j++;
									}
								}
								printBoard();
							}else {
								System.out.println("Invalid input, please try place O in a valid position");
							}
						}
					}
					if(!gameStart) {
						System.out.println("Game Finish!");
						if(testWinner().equals("X")){
							System.out.println("Player 1 with Symbol X wins.");
						}else {
							System.out.println("Player 2 with Symbol O wins.");
						}
						return;
					}
				}
				
				
				
	        }
	        catch (InputMismatchException e) {
	        	System.out.println("Invalid input; re-enter slot number:");
	            continue;
	        }
		}
		System.out.println("test begin");
		printBoard();
		System.out.println("test finish");
	}
	
	public static void initialise() {
		board = new String[size][size];
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				board[i][j]=Integer.toString(size*i+j+1);
			}
		}
	}
	
	public static void printBoard() {
		for(int i=0;i<size;i++) {
			System.out.print("|");
			for(int j=0;j<size;j++) {
				System.out.print(board[i][j]+"|");
			}
			System.out.println();
		}
	}
	
	public static String testWinner() {
		String winner;
	    Boolean same;
	    for(int i=0;i<size;i++) {
	    	winner = board[i][0];
	        same=true;
	        for (int j=0;j<size;j++) {
	            if(!winner.equals(board[i][j])) {
	                same=false;
	                break;
	            }
	        }
	        if(same) {
	            return winner;
	        }
	    }
	    for(int i=0;i<size;i++) {
	        winner = board[0][i];
	        same=true;
	        for (int j =0;j<size;j++) {
	            if(!winner.equals(board[j][i])) {
	                same=false;
	                break;
	            }
	        }
	        if(same) {
	            return winner;
	        }
	    }
	    if(board[0][0].equals("X") || board[0][0].equals("O")) {
	    	winner=board[0][0];
	    	same = true;
	    	for(int i=0;i<size;i++) {
	    		if(!winner.equals(board[i][i])) {
	    			same=false;
	    			break;
	    		}
	    	}
	    	if(same) {
	    		return winner;
	    	}
	    }
	    if(board[0][size-1].equals("X") || board[0][size-1].equals("O")) {
	    	winner=board[0][size-1];
	    	same = true;
	    	for(int i=0;i<size;i++) {
	    		if(!winner.equals(board[i][size-i-1])) {
	    			same=false;
	    			break;
	    		}
	    	}
	    	if(same) {
	    		return winner;
	    	}
	    }
	    return "drawn";
	 }


}
