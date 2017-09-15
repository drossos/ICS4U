package com.bayviewglen.quizone;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QuizOne {

	public static void main(String[] args) {
		char[][] game = new char[3][3];
		Scanner input = null;
		try {
			input = new Scanner(new File("data/quizOne.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		game = fillGame(game, input);
		boolean valid = checkValid(game);
		if (!valid)
			System.out.println("Not a Valid game (because the number of Xs and Os do not differ by 1");
		else {
			boolean xwin = analyzeSpots(game, 'x');
			boolean owin = analyzeSpots(game, 'o');
			
			if (xwin && owin)
				System.out.println("tie");
			else if (xwin)
				System.out.println("X WON");
			else if (owin)
				System.out.println("O WON");
			else if (!xwin && !owin)
				System.out.println("game is not finished yet");
		}
	}

	private static boolean analyzeSpots(char[][] game, char check) {
		boolean horz = checkHorz(game,check);
		boolean vert = checkVert(game,check);
		boolean diag = checkDiag(game,check);
		
		if (horz == true || vert == true || diag == true)
			return true;
		else
			return false;
	}

	private static boolean checkDiag(char[][] game, char check) {
		if (game[0][0] == check && game[1][1] == check && game[2][2] == check)
			return true;
		if (game[0][2] == check && game[1][1] == check && game[0][2] == check)
			return true;
		return false;
	}

	private static boolean checkVert(char[][] game, char check) {
		for (int i = 0; i < 3; i++) {
			if (game[0][i] == check && game[1][i] == check && game[2][i] == check)
				return true;
			}
		return false;
	}

	private static boolean checkHorz(char[][] game, char check) {
		for (int i = 0; i < game.length; i++) {
			if (game[i][0] == check && game[i][1] == check && game[i][2] == check)
				return true;
			}
		return false;
		}
	

	private static boolean checkValid(char[][] game) {
		int xs = 0;
		int os = 0;
		for (int i = 0; i < game.length; i++) {
			for (int j = 0; j < game[i].length; j++) {
				if (game[i][j] == 'x')
					xs++;
				if (game[i][j] == 'o')
					os++;
			}
		}
		if (Math.abs(xs - os) > 1)
			return false;
		else
			return true;
	}

	private static char[][] fillGame(char[][] game, Scanner input) {
		for (int i = 0; i < 3; i++) {
			game[i] = input.nextLine().toCharArray();
		}
		return game;
	}

}
