package com.bridgelabz;

import java.util.Scanner;

public class TicTacToeGame {
	private char[] board = new char[10];
	private boolean player1Turn = true;
	private String player1Letter = "";
	private String gameStatusMessage = "";
	Scanner scanner = new Scanner(System.in);
	private boolean neitherPlayerHasWinningChance = true;

	public static void main(String[] args) {
		TicTacToeGame ticTacToeGame = new TicTacToeGame();
		ticTacToeGame.playTicTacToeGame(ticTacToeGame);
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
			ticTacToeGame.player1Letter = ticTacToeGame.player1Turn ? p1.toUpperCase() : "0";
		} else if (p1.equalsIgnoreCase("0")) {
			ticTacToeGame.player1Letter = ticTacToeGame.player1Turn ? p1.toUpperCase() : "X";
		} else {
			System.out.println("Please select a valid value");
			selectLetter(ticTacToeGame);
		}
	}

	/**
	 * This method is deciding the move to be done Derive player and player2Letter.
	 * Algorithm for smart moves. Make the move if index is free. Update the board.
	 * Check game status.
	 * 
	 * @param ticTacToeGame
	 * @return
	 */
	private boolean makeAMove(TicTacToeGame ticTacToeGame) {
		String player2Letter = ticTacToeGame.player1Letter.equalsIgnoreCase("X") ? "0" : "X";
		algorithmForSmartPlay(ticTacToeGame, player2Letter);
		int index = scanner.nextInt();
		if (index >= 1 && index <= 9) {
			if (isIndexFree(index, ticTacToeGame.board)) {
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
					System.out.println("Do you want to play again? Y or N");
					playAgain();
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
	 * This method is to look for intelligent moves. If player1 is playing, he
	 * checks if he has a winning move, if not he checks if the other player has
	 * winning move and play to block it, if neither of them has a winning move,
	 * player1 to look for best moves available. Look for corners first,then the
	 * center,then the sides. If player2 is playing,he checks if he has a winning
	 * move.
	 * 
	 * @param ticTacToeGame
	 * @param player2Letter
	 */
	private void algorithmForSmartPlay(TicTacToeGame ticTacToeGame, String player2Letter) {
		if (ticTacToeGame.player1Turn) {
			System.out.println("Player1's turn(" + ticTacToeGame.player1Letter + ")");
			boolean checkOtherPlayerWinningMove = checkForWinningMove(ticTacToeGame, "player1");
			if (checkOtherPlayerWinningMove) {
				checkForWinningMove(ticTacToeGame, "player2");
			}
			if (ticTacToeGame.neitherPlayerHasWinningChance) {
				getBestMovesAvailable(ticTacToeGame.board);
			}
		} else {
			System.out.println("Player2's turn(" + player2Letter + ")");
			checkForWinningMove(ticTacToeGame, "player2");
		}
	}

	/**
	 * This method is making the game play again
	 * 
	 * @param ticTacToeGame
	 */
	private void playAgain() {
		String playAgain = scanner.next();
		if (playAgain.equalsIgnoreCase("Y") || playAgain.equalsIgnoreCase("N")) {
			if (playAgain.equalsIgnoreCase("Y")) {
				TicTacToeGame ticTacToeGame = new TicTacToeGame();
				playTicTacToeGame(ticTacToeGame);
			} else {
				scanner.close();
				System.exit(0);
			}
		} else {
			System.out.println("Please enter a valid input");
			playAgain();
		}
	}

	private void playTicTacToeGame(TicTacToeGame ticTacToeGame) {

		Scanner scanner = new Scanner(System.in);
		ticTacToeGame.createBoard(ticTacToeGame);
		ticTacToeGame.toss(ticTacToeGame);
		ticTacToeGame.selectLetter(ticTacToeGame);
		ticTacToeGame.createBoard(ticTacToeGame);
		System.out.println("Select an index between 1 to 9");
		ticTacToeGame.makeAMove(ticTacToeGame);
	}

	/**
	 * This method is suggesting the player to decide which move to play to win the
	 * game and for Player1 it is suggesting to block the Player2 index to block and
	 * if neither are winning take available corner
	 * 
	 * @param ticTacToeGame
	 */
	private boolean checkForWinningMove(TicTacToeGame ticTacToeGame, String player) {
		boolean checkOtherPlayerWinningMove = false;
		boolean neitherPlayerHasWinningChance = true;
		char[] board = ticTacToeGame.board;
		char player2Letter = ticTacToeGame.player1Letter.equalsIgnoreCase("X") ? '0' : 'X';
		char playerLetter = 0;
		if (ticTacToeGame.player1Turn && player.equalsIgnoreCase("Player1")) {
			playerLetter = ticTacToeGame.player1Letter.toCharArray()[0];
		} else if (ticTacToeGame.player1Turn && player.equalsIgnoreCase("Player2")) {
			playerLetter = player2Letter;
		} else if (!ticTacToeGame.player1Turn && player.equalsIgnoreCase("Player1")) {
			playerLetter = ticTacToeGame.player1Letter.toCharArray()[0];
		} else if (!ticTacToeGame.player1Turn && player.equalsIgnoreCase("Player2")) {
			playerLetter = player2Letter;
		}
		if ((board[2] == playerLetter && board[3] == playerLetter)
				|| (board[4] == playerLetter && board[7] == playerLetter)
				|| (board[5] == playerLetter && board[9] == playerLetter)) {
			if (isIndexFree(1, board)) {
				neitherPlayerHasWinningChance = false;
				checkOtherPlayerWinningMove = getWinningChanceMessage(ticTacToeGame.player1Turn, player, 1);
			}
		}

		if ((board[1] == playerLetter && board[3] == playerLetter)
				|| (board[5] == playerLetter && board[8] == playerLetter)) {
			if (isIndexFree(2, board)) {
				neitherPlayerHasWinningChance = false;
				checkOtherPlayerWinningMove = getWinningChanceMessage(ticTacToeGame.player1Turn, player, 2);
			}
		}

		if ((board[1] == playerLetter && board[2] == playerLetter)
				|| (board[6] == playerLetter && board[9] == playerLetter)
				|| (board[5] == playerLetter && board[7] == playerLetter)) {
			if (isIndexFree(3, board)) {
				neitherPlayerHasWinningChance = false;
				checkOtherPlayerWinningMove = getWinningChanceMessage(ticTacToeGame.player1Turn, player, 3);
			}
		}

		if ((board[5] == playerLetter && board[6] == playerLetter)
				|| (board[1] == playerLetter && board[7] == playerLetter)) {
			if (isIndexFree(4, board)) {
				neitherPlayerHasWinningChance = false;
				checkOtherPlayerWinningMove = getWinningChanceMessage(ticTacToeGame.player1Turn, player, 4);
			}
		}
		if ((board[4] == playerLetter && board[6] == playerLetter)
				|| (board[2] == playerLetter && board[8] == playerLetter)
				|| (board[9] == playerLetter && board[1] == playerLetter)) {
			if (isIndexFree(5, board)) {
				neitherPlayerHasWinningChance = false;
				checkOtherPlayerWinningMove = getWinningChanceMessage(ticTacToeGame.player1Turn, player, 5);
			}
		}
		if ((board[4] == playerLetter && board[5] == playerLetter)
				|| (board[3] == playerLetter && board[9] == playerLetter)) {
			if (isIndexFree(6, board)) {
				neitherPlayerHasWinningChance = false;
				checkOtherPlayerWinningMove = getWinningChanceMessage(ticTacToeGame.player1Turn, player, 6);
			}
		}
		if ((board[1] == playerLetter && board[4] == playerLetter)
				|| (board[8] == playerLetter && board[9] == playerLetter)
				|| (board[3] == playerLetter && board[5] == playerLetter)) {
			if (isIndexFree(7, board)) {
				neitherPlayerHasWinningChance = false;
				checkOtherPlayerWinningMove = getWinningChanceMessage(ticTacToeGame.player1Turn, player, 7);
			}
		}
		if ((board[7] == playerLetter && board[9] == playerLetter)
				|| (board[2] == playerLetter && board[5] == playerLetter)) {
			if (isIndexFree(8, board)) {
				neitherPlayerHasWinningChance = false;
				checkOtherPlayerWinningMove = getWinningChanceMessage(ticTacToeGame.player1Turn, player, 8);
			}
		}
		if ((board[7] == playerLetter && board[8] == playerLetter)
				|| (board[3] == playerLetter && board[6] == playerLetter)
				|| (board[1] == playerLetter && board[5] == playerLetter)) {
			if (isIndexFree(9, board)) {
				neitherPlayerHasWinningChance = false;
				checkOtherPlayerWinningMove = getWinningChanceMessage(ticTacToeGame.player1Turn, player, 9);
			}
		}
		ticTacToeGame.neitherPlayerHasWinningChance = neitherPlayerHasWinningChance;
		return checkOtherPlayerWinningMove;
	}

	/**
	 * This method is giving us the winning message conditions
	 * 
	 * @param player1Turn
	 * @param player
	 * @param i
	 * @return
	 */
	private boolean getWinningChanceMessage(boolean player1Turn, String player, int i) {
		if (player1Turn && player.equalsIgnoreCase("Player1") || !player1Turn && player.equalsIgnoreCase("Player2")) {
			System.out.println("You have winning chance at index " + i);
			return false;
		} else if (player1Turn && player.equalsIgnoreCase("Player2")) {
			System.out.println("Player2 has winning chance at index " + i + ". Play to block it");
		} else if (!player1Turn && player.equalsIgnoreCase("Player1")) {
			System.out.println("Player1 has winning chance at index " + i + ". Play to block it");
		}
		return true;
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
	 * If neither of players winning the first choice should be taken from corners
	 * else choose center or the other sides
	 * 
	 * @param index
	 * @param board
	 * @return
	 */
	private void getBestMovesAvailable(char[] board) {
		boolean isPreferredChoiceAvailable = false;
		if (!isPreferredChoiceAvailable) {
			String cornerIndexList = "It is suggested to select from corners ";
			if (board[1] == '\u0000') {
				cornerIndexList = cornerIndexList.concat("1, ");
			}
			if (board[3] == '\u0000') {
				cornerIndexList = cornerIndexList.concat("3, ");
			}
			if (board[7] == '\u0000') {
				cornerIndexList = cornerIndexList.concat("7, ");
			}
			if (board[9] == '\u0000') {
				cornerIndexList = cornerIndexList.concat("9, ");
			}
			if (board[1] == '\u0000' || board[3] == '\u0000' || board[7] == '\u0000' || board[9] == '\u0000') {
				System.out.println(cornerIndexList);
			} else {
				System.out.println("Corners not available");
				isPreferredChoiceAvailable = false;
				if (!isPreferredChoiceAvailable && board[5] == '\u0000') {
					System.out.println("It is suggested to select the center");
					isPreferredChoiceAvailable = true;
				} else if (!isPreferredChoiceAvailable) {
					String availableSides = "It is suggested to select from sides ";
					if (board[2] == '\u0000') {
						availableSides = availableSides.concat("2, ");
					}
					if (board[4] == '\u0000') {
						availableSides = availableSides.concat("4, ");
					}
					if (board[6] == '\u0000') {
						availableSides = availableSides.concat("6, ");
					}
					if (board[8] == '\u0000') {
						availableSides = availableSides.concat("8, ");
					}
					if (board[2] == '\u0000' || board[4] == '\u0000' || board[6] == '\u0000' || board[8] == '\u0000') {
						System.out.println(availableSides);
					} else {
						System.out.println("Sides not available");
					}

				}
			}
		}
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
