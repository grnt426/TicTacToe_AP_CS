import java.util.Scanner;

public class TicTacToe {

	Scanner scan = new Scanner(System.in);
	final int X_M = 10; // X's mark make some change
	final int Y_M = 100; // Y's mark
	final int EMPTY = 0; // What an empty space looks like

	// X starts the game
	int currentPlayer = X_M;

	int[][] board = {
			{EMPTY, EMPTY, EMPTY},
			{EMPTY, EMPTY, EMPTY},
			{EMPTY, EMPTY, EMPTY}
	};

	boolean winner = false;
	String winningPlayer = "";

	public static void main(String[] args) {
		TicTacToe t = new TicTacToe();
		t.run();
	}

	public void run() {
		int round = 1;

		// there are only 9 spaces on the board, so only 9 rounds!
		while(!winner && round < 10) {
			printBoard();
			System.out.println((currentPlayer == X_M ? "X " : "Y ") + " take a turn");

			int[] coords = getValidInput();
			board[coords[0]][coords[1]] = currentPlayer;

			checkWinner();

			currentPlayer = currentPlayer == X_M ? Y_M : X_M;
			round++;
		}

		if(winner) {
			System.out.println(winningPlayer + " is the winning player!");
		}
		else {
			System.out.println("There was a tie! No winners.");
		}
	}

	public void checkWinner() {
		for(int r = 0; r < 3; r++) {

			// check vertical
			int sum = board[r][0] + board[r][1] + board[r][2];
			checkSum(sum);

			// check horizontal
			sum = board[0][r] + board[1][r] + board[2][r];
			checkSum(sum);
		}

		// check diagonal
		int sum = board[0][0] + board[1][1] + board[2][2];
		checkSum(sum);
		sum = board[0][2] + board[1][1] + board[2][0];
		checkSum(sum);
	}

	public void checkSum(int sum) {
		if(sum == X_M * 3) {
			winner = true;
			winningPlayer = "X";
		}
		else if(sum == Y_M * 3) {
			winner = true;
			winningPlayer = "Y";
		}
	}

	public void printBoard() {
		for(int[] r : board) {
			for(int c : r) {
				switch(c) {
					case EMPTY: System.out.print("_ "); break;
					case X_M: System.out.print("X "); break;
					case Y_M: System.out.print("Y "); break;
				}
			}
			System.out.println();
		}
	}

	public int[] getValidInput() {
		boolean valid = false;
		int[] coords = new int[2];

		while(!valid) {
			System.out.println("Enter coordinates of the form 'X Y': ");
			String input = scan.nextLine();
			String[] data = input.split(" ");
			if (data.length != 2) {
				System.out.println("Need two coordinates, such as 0, 0. Or 1, 2");
				continue;
			}

			try {
				coords[0] = Integer.parseInt(data[0]);
				coords[1] = Integer.parseInt(data[1]);

				if(coords[0] < 0 || coords[0] > 2 || coords[1] < 0 || coords[1] > 2) {
					System.out.println("Coordinates are 0, 1 , or 2 only.");
					continue;
				}
			}
			catch(Exception e) {
				System.out.println("Integer coordinates only");
				continue;
			}

			if(board[coords[0]][coords[1]] == EMPTY) {
				valid = true;
			}
			else {
				System.out.println("That location already has a mark");
			}
		}

		return coords;
	}
}
