package com.bridgelabz;

import java.util.Scanner;

public class TicTacToeGame {
	public static void main(String[] args) {
		char[] board = new char[10];
		boolean gameEnded = false;
		boolean player1Turn = true;
		drawBoard(board);
		String player1 = "";
		player1 = selectLetter(player1);
		drawBoard(board);
		System.out.println("Select an index between 1 to 9");
		makeAMove(player1Turn, board);
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
		System.out.print("Select a letter X or 0 ");
		String p1 = in.nextLine();
		if (p1.equalsIgnoreCase("X")) {
			System.out.println("Player1 selects X");
			System.out.println("Player2 to play with 0");
			player1 = "X";
		} else if (p1.equalsIgnoreCase("0")) {
			System.out.println("Player1 selects 0");
			System.out.println("Player2 to play with X");
			player1 = "0";
		} else {
			System.out.println("Please select a valid value");
			selectLetter(player1);
		}
		return player1;
	}

	/**
	 * @param player1Turn
	 * @param board
	 */
	private static void makeAMove(boolean player1Turn, char[] board) {
		Scanner sc = new Scanner(System.in);
		if (player1Turn) {
			System.out.println("Player1's turn(X)");
		} else {
			System.out.println("Player2's turn(0)");
		}

		int a = sc.nextInt();
		if (a >= 1 && a <= 9) {
			System.out.println(board[a]);
			if (board[a] != 'X' && board[a] != '0') {
				if (player1Turn) {
					board[a] = 'X';
				} else {
					board[a] = '0';
				}
				player1Turn = !player1Turn;
				drawBoard(board);
				makeAMove(player1Turn, board);

			} else {
				System.out.println("This index is already taken. Please choose another index");
				makeAMove(player1Turn, board);
			}
		} else {
			System.out.println("Please enter a valid input");
			makeAMove(player1Turn, board);
		}
	}
}
