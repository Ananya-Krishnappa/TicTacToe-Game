package com.bridgelabz;

import java.util.Scanner;

public class TicTacToeGame {
	public static void main(String[] args) {
		char[] board = new char[10];
		boolean gameEnded = false;
		boolean player1Turn = true;
		drawBoard(board);
		String player1Letter = "";
		player1Letter = selectLetter(player1Letter);
		player1Turn = toss(player1Turn);
		drawBoard(board);
		System.out.println("Select an index between 1 to 9");
		makeAMove(player1Turn, player1Letter, board);

	}

	/**
	 * @param board
	 */
	private static void drawBoard(char[] board) {

		System.out.println(board[1] + " | " + board[2] + " | " + board[3]);
		System.out.println("--|---|--");
		System.out.println(board[4] + " | " + board[5] + " | " + board[6]);
		System.out.println("--|---|--");
		System.out.println(board[7] + " | " + board[8] + " | " + board[9]);
	}

	/**
	 * @param player1
	 * @return
	 */
	private static String selectLetter(String player1Letter) {
		Scanner in = new Scanner(System.in);
		System.out.println("Let's play Tic Tac Toe!");
		System.out.print("Player1 to select a letter X or 0 ");
		String p1 = in.nextLine();
		if (p1.equalsIgnoreCase("X")) {
			System.out.println("Player1 selects X");
			System.out.println("Player2 to play with 0");
			player1Letter = "X";
		} else if (p1.equalsIgnoreCase("0")) {
			System.out.println("Player1 selects 0");
			System.out.println("Player2 to play with X");
			player1Letter = "0";
		} else {
			System.out.println("Please select a valid value");
			selectLetter(player1Letter);
		}
		return player1Letter;
	}

	/**
	 * @param player1Turn
	 * @param player1Letter
	 * @param board
	 */
	private static boolean makeAMove(boolean player1Turn, String player1Letter, char[] board) {
		String player2Letter = player1Letter.equalsIgnoreCase("X") ? "0" : "X";
		Scanner sc = new Scanner(System.in);
		if (player1Turn) {
			System.out.println("Player1's turn(" + player1Letter + ")");
		} else {
			System.out.println("Player2's turn(" + player2Letter + ")");
		}

		int a = sc.nextInt();
		if (a >= 1 && a <= 9) {
			System.out.println(board[a]);
			if (board[a] != 'X' && board[a] != '0') {
				if (player1Turn) {
					board[a] = player1Letter.toCharArray()[0];
				} else {
					board[a] = player2Letter.toCharArray()[0];
				}
				player1Turn = !player1Turn;
				drawBoard(board);
				playerHasWon(board);
				String output = "";
				if (playerHasWon(board) == 'X') {
					output = player1Letter.equalsIgnoreCase("X") ? "Player1 won" : "Player2 won";
					System.out.println(output);
					return true;
				} else if (playerHasWon(board) == '0') {
					output = player1Letter.equalsIgnoreCase("0") ? "Player1 won" : "Player2 won";
					System.out.println(output);
					return true;
				} else {
					if (!isTie(board)) {
						makeAMove(player1Turn, player1Letter, board);
					} else {
						System.out.println("Game ended in a tie");
						return true;
					}
				}

			} else {
				System.out.println("This index is already taken. Please choose another index");
				makeAMove(player1Turn, player1Letter, board);
			}
		} else {
			System.out.println("Please enter a valid input");
			makeAMove(player1Turn, player1Letter, board);
		}
		return false;
	}

	/**
	 * @param board
	 * @return
	 */
	private static boolean isTie(char[] board) {
		for (int j = 1; j <= 9; j++) {
			if (board[j] != 'X' && board[j] != '0') {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param player1Turn
	 * @return
	 */
	private static boolean toss(boolean player1Turn) {
		System.out.println("Player1 to call the toss: Head or Tail");
		Scanner sc = new Scanner(System.in);
		String headOrTail = sc.nextLine();
		if (headOrTail.equalsIgnoreCase("head") || headOrTail.equalsIgnoreCase("tail")) {
			System.out.println("....");
			System.out.println("....");
			double random = Math.random();
			if (headOrTail.equalsIgnoreCase("head") && random >= 0.5) {
				player1Turn = true;
				System.out.println("Player1 won the toss");
			} else {
				player1Turn = false;
				System.out.println("Player2 won the toss");
			}
		} else {
			System.out.println("Incorrect input. Please call Head or Tail");
			toss(player1Turn);
		}

		return player1Turn;
	}

	private static char playerHasWon(char[] board) {
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
