package com.bridgelabz;

import java.util.Scanner;

public class TicTacToeGame {
	private char[] board = new char[10];
	private boolean player1Turn = true;
	private String player1Letter = "";
	private String gameStatusMessage = "";
	Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		TicTacToeGame ticTacToeGame = new TicTacToeGame();
		Scanner scanner = new Scanner(System.in);
		ticTacToeGame.createBoard(ticTacToeGame);
		ticTacToeGame.toss(ticTacToeGame);
		ticTacToeGame.selectLetter(ticTacToeGame);
		ticTacToeGame.createBoard(ticTacToeGame);
		System.out.println("Select an index between 1 to 9");
		ticTacToeGame.makeAMove(ticTacToeGame);
	}

	/**
	 * This method is used to create a board
	 * 
	 * @param ticTacToeGame
	 */
	private void createBoard(TicTacToeGame ticTacToeGame) {
		char[] board = ticTacToeGame.board;
		System.out.println(board[1] + " | " + board[2] + " | " + board[3]);
		System.out.println("--|---|--");
		System.out.println(board[4] + " | " + board[5] + " | " + board[6]);
		System.out.println("--|---|--");
		System.out.println(board[7] + " | " + board[8] + " | " + board[9]);
	}

	/**
	 * This method is checking whether to enter X or 0
	 * 
	 * @param ticTacToeGame
	 */
	private void selectLetter(TicTacToeGame ticTacToeGame) {
		String player = ticTacToeGame.player1Turn ? "Player1" : "Player2";
		System.out.print(player + " to select a letter X or 0 ");
		String p1 = scanner.nextLine();
		if (p1.equalsIgnoreCase("X")) {
			player1Letter = ticTacToeGame.player1Turn ? p1.toUpperCase() : "0";
		} else if (p1.equalsIgnoreCase("0")) {
			player1Letter = ticTacToeGame.player1Turn ? p1.toUpperCase() : "X";
		} else {
			System.out.println("Please select a valid value");
			selectLetter(ticTacToeGame);
		}
	}

	/**
	 * This method is deciding the move to be done
	 * 
	 * @param ticTacToeGame
	 * @return
	 */
	private boolean makeAMove(TicTacToeGame ticTacToeGame) {
		String player2Letter = ticTacToeGame.player1Letter.equalsIgnoreCase("X") ? "0" : "X";
		if (ticTacToeGame.player1Turn) {
			System.out.println("Player1's turn(" + ticTacToeGame.player1Letter + ")");
			checkForWinningMove(ticTacToeGame);
		} else {
			System.out.println("Player2's turn(" + player2Letter + ")");
			checkForWinningMove(ticTacToeGame);
		}
		int index = scanner.nextInt();
		if (index >= 1 && index <= 9) {
			if (ticTacToeGame.board[index] != 'X' && ticTacToeGame.board[index] != '0') {
				if (ticTacToeGame.player1Turn) {
					ticTacToeGame.board[index] = ticTacToeGame.player1Letter.toCharArray()[0];
				} else {
					ticTacToeGame.board[index] = player2Letter.toCharArray()[0];
				}
				ticTacToeGame.player1Turn = !ticTacToeGame.player1Turn;
				createBoard(ticTacToeGame);
				boolean gameStatus = checkGameStatus(ticTacToeGame);
				if (gameStatus) {
					System.out.println(ticTacToeGame.gameStatusMessage);
					System.exit(0);
				} else {
					makeAMove(ticTacToeGame);
				}
			} else {
				System.out.println("This index is already taken. Please choose another index");
				makeAMove(ticTacToeGame);
			}
		} else {
			System.out.println("Please enter a valid input");
			makeAMove(ticTacToeGame);
		}
		return false;
	}

	/**
	 * This method is suggesting the player to decide which move to play to win the
	 * game and for Player1 it is suggesting to block the Player2 index to block
	 * 
	 * @param ticTacToeGame
	 */
	private void checkForWinningMove(TicTacToeGame ticTacToeGame) {
		char[] board = ticTacToeGame.board;
		char player2Letter = ticTacToeGame.player1Letter.equalsIgnoreCase("X") ? '0' : 'X';

		if ((board[2] == player2Letter && board[3] == player2Letter)
				|| (board[4] == player2Letter && board[7] == player2Letter)
				|| (board[5] == player2Letter && board[9] == player2Letter)) {
			if (isIndexFree(1, board)) {
				if (ticTacToeGame.player1Turn) {
					System.out.println("Player2 has have winning chance at index 1. Play to block it");
				} else {
					System.out.println("You have winning chance at index 1");
				}

			}
		}

		if ((board[1] == player2Letter && board[3] == player2Letter)
				|| (board[5] == player2Letter && board[8] == player2Letter)) {
			if (isIndexFree(2, board)) {
				if (ticTacToeGame.player1Turn) {
					System.out.println("Player2 has have winning chance at index 2. Play to block it");
				} else {
					System.out.println("You have winning chance at index 2");
				}
			}
		}

		if ((board[1] == player2Letter && board[2] == player2Letter)
				|| (board[6] == player2Letter && board[9] == player2Letter)
				|| (board[5] == player2Letter && board[7] == player2Letter)) {
			if (isIndexFree(3, board)) {
				if (ticTacToeGame.player1Turn) {
					System.out.println("Player2 has have winning chance at index 3. Play to block it");
				} else {
					System.out.println("You have winning chance at index 3");
				}
			}
		}

		if ((board[5] == player2Letter && board[6] == player2Letter)
				|| (board[1] == player2Letter && board[7] == player2Letter)) {
			if (isIndexFree(4, board)) {
				if (ticTacToeGame.player1Turn) {
					System.out.println("Player2 has have winning chance at index 4. Play to block it");
				} else {
					System.out.println("You have winning chance at index 4");
				}
			}
		}
		if ((board[4] == player2Letter && board[6] == player2Letter)
				|| (board[2] == player2Letter && board[8] == player2Letter)
				|| (board[9] == player2Letter && board[1] == player2Letter)) {
			if (isIndexFree(5, board)) {
				if (ticTacToeGame.player1Turn) {
					System.out.println("Player2 has have winning chance at index 5. Play to block it");
				} else {
					System.out.println("You have winning chance at index 5");
				}
			}
		}
		if ((board[4] == player2Letter && board[5] == player2Letter)
				|| (board[3] == player2Letter && board[9] == player2Letter)) {
			if (isIndexFree(6, board)) {
				if (ticTacToeGame.player1Turn) {
					System.out.println("Player2 has have winning chance at index 6. Play to block it");
				} else {
					System.out.println("You have winning chance at index 6");
				}
			}
		}
		if ((board[1] == player2Letter && board[4] == player2Letter)
				|| (board[8] == player2Letter && board[9] == player2Letter)
				|| (board[3] == player2Letter && board[5] == player2Letter)) {
			if (isIndexFree(7, board)) {
				if (ticTacToeGame.player1Turn) {
					System.out.println("Player2 has have winning chance at index 7. Play to block it");
				} else {
					System.out.println("You have winning chance at index 7");
				}
			}
		}
		if ((board[7] == player2Letter && board[9] == player2Letter)
				|| (board[2] == player2Letter && board[5] == player2Letter)) {
			if (isIndexFree(8, board)) {
				if (ticTacToeGame.player1Turn) {
					System.out.println("Player2 has have winning chance at index 8. Play to block it");
				} else {
					System.out.println("You have winning chance at index 8");
				}
			}
		}
		if ((board[7] == player2Letter && board[8] == player2Letter)
				|| (board[3] == player2Letter && board[6] == player2Letter)
				|| (board[1] == player2Letter && board[5] == player2Letter)) {
			if (isIndexFree(9, board)) {
				if (ticTacToeGame.player1Turn) {
					System.out.println("Player2 has have winning chance at index 9. Play to block it");
				} else {
					System.out.println("You have winning chance at index 9");
				}
			}
		}
	}

	/**
	 * This method is checking the current status of the game
	 * 
	 * @param ticTacToeGame
	 * @return
	 */
	private boolean checkGameStatus(TicTacToeGame ticTacToeGame) {
		boolean status = false;
		if (playerHasWon(ticTacToeGame.board) == 'X') {
			ticTacToeGame.gameStatusMessage = ticTacToeGame.player1Letter.equalsIgnoreCase("X") ? "Player1 won"
					: "Player2 won";
			status = true;
		} else if (playerHasWon(ticTacToeGame.board) == '0') {
			ticTacToeGame.gameStatusMessage = ticTacToeGame.player1Letter.equalsIgnoreCase("0") ? "Player1 won"
					: "Player2 won";
			status = true;
		} else {
			if (!isTie(ticTacToeGame.board)) {
				makeAMove(ticTacToeGame);
			} else {
				ticTacToeGame.gameStatusMessage = "Game ended in a tie";
				status = true;
			}
		}
		return status;

	}

	/**
	 * This method is checking if there is tie between the players
	 */
	private boolean isTie(char[] board) {
		for (int j = 1; j <= 9; j++) {
			if (board[j] != 'X' && board[j] != '0') {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method is checking if there are any free space in the board
	 * 
	 * @param index
	 * @param board
	 * @return
	 */
	private boolean isIndexFree(int index, char[] board) {
		if (board[index] == '\u0000') {
			return true;
		}
		return false;
	}

	/**
	 * This method is used to toss to decide who plays first
	 * 
	 * @param ticTacToeGame
	 */
	private void toss(TicTacToeGame ticTacToeGame) {
		System.out.println("Player1 to call the toss: Head or Tail");
		String headOrTail = scanner.nextLine();
		if (headOrTail.equalsIgnoreCase("head") || headOrTail.equalsIgnoreCase("tail")) {
			double random = Math.random();
			if (headOrTail.equalsIgnoreCase("head") && random >= 0.5) {
				ticTacToeGame.player1Turn = true;
				System.out.println("Player1 won the toss");
			} else {
				ticTacToeGame.player1Turn = false;
				System.out.println("Player2 won the toss");
			}
		} else {
			System.out.println("Incorrect input. Please call Head or Tail");
			toss(ticTacToeGame);
		}
	}

	/**
	 * This method is written to check which player has won
	 */
	private char playerHasWon(char[] board) {
		for (int a = 1; a < 9; a++) {
			String line = null;

			switch (a) {
			case 1:
				line = String.valueOf(board[1]) + board[2] + board[3];
				break;
			case 2:
				line = String.valueOf(board[4]) + board[5] + board[6];
				break;
			case 3:
				line = String.valueOf(board[7]) + board[8] + board[9];
				break;
			case 4:
				line = String.valueOf(board[1]) + board[4] + board[7];
				break;
			case 5:
				line = String.valueOf(board[2]) + board[5] + board[8];
				break;
			case 6:
				line = String.valueOf(board[3]) + board[6] + board[9];
				break;
			case 7:
				line = String.valueOf(board[1]) + board[5] + board[9];
				break;
			case 8:
				line = String.valueOf(board[3]) + board[5] + board[7];
				break;
			}
			// For X winner
			if (line.equals("XXX")) {
				return 'X';
			}

			// For O winner
			else if (line.equals("000")) {
				return '0';
			}
		}
		return 0;

	}
}
