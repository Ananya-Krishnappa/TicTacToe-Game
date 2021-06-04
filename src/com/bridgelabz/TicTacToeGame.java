package com.bridgelabz;

import java.util.Scanner;

public class TicTacToeGame {
	public static void main(String[] args) {
		char[] board = new char[10];
		boolean gameEnded = false;
		drawBoard(board);
		String player1 = "";
		player1 = selectLetter(player1);
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
	private static String selectLetter(String player1) {
		Scanner in = new Scanner(System.in);
		System.out.println("Let's play Tic Tac Toe!");
		System.out.print("Select a letter X or O ");
		String p1 = in.nextLine();
		if (p1.equalsIgnoreCase("X")) {
			System.out.println("Player1 selects X");
			System.out.println("Player2 to play with O");
			player1 = "X";
		} else if (p1.equalsIgnoreCase("0")) {
			System.out.println("Player1 selects O");
			System.out.println("Player2 to play with X");
			player1 = "O";
		} else {
			System.out.println("Please select a valid value");
			selectLetter(player1);
		}
		return player1;
	}
}
