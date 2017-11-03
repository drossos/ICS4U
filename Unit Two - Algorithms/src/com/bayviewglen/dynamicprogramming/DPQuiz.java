package com.bayviewglen.dynamicprogramming;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DPQuiz {

	public static void main(String[] args) throws FileNotFoundException {
		// BufferedReader read = new BufferedReader(new FileReader(new
		// File("data/Quiz.dat")));
		Scanner read = new Scanner(new File("data/Quiz.dat"));

		while (read.hasNext()) {
			// String x = read.nextLine();
			int n = Integer.parseInt(read.nextLine());
			String[] x = read.nextLine().split(" ");
			int frstOpt = Integer.parseInt(x[0]);
			int scndOpt = Integer.parseInt(x[1]);
			int thrdOpt = Integer.parseInt(x[2]);

			// [][0] is num steps to get here
			// [][1] is what to do to get here

			int[][] sln = new int[n + 1][2];
			sln[0][0] = 0;
			sln[1][0] = 0;
			for (int i = 2; i < sln.length; i++) {
				sln[i][0] = 1 + sln[i - 1][0];
				sln[i][1] = -1;
				if (i % frstOpt == 0) {
					boolean check = sln[i][0] > 1 + sln[i / frstOpt][0];
					sln[i][0] = Math.min(sln[i][0], 1 + sln[i / frstOpt][0]);
					if (check)
						sln[i][1] = frstOpt;
				}
				if (i % scndOpt == 0) {
					boolean check = sln[i][0] > 1 + sln[i / scndOpt][0];
					sln[i][0] = Math.min(sln[i][0], 1 + sln[i / scndOpt][0]);
					if (check)
						sln[i][1] = scndOpt;
				}
				if (i % thrdOpt == 0) {
					boolean check = sln[i][0] > 1 + sln[i / thrdOpt][0];
					sln[i][0] = Math.min(sln[i][0], 1 + sln[i / thrdOpt][0]);
					if (check)
						sln[i][1] = thrdOpt;
				}
			}
			System.out.println("Took " + sln[sln.length - 1][0] + " steps. Process was:");
			boolean notSolved = true;
			int i = sln.length - 1;
			while (notSolved) {
				if (sln[i][1] == -1) {
					System.out.println("Subtract 1");
					i--;
				} else {
					System.out.println("Divide by " + sln[i][1]);
					i /= sln[i][1];
				}
				if (sln[i][0] == 0)
					notSolved = false;

			}
			System.out.println();

		}

	}

}
