package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.StringTokenizer;

class bingo {
	int[][] board;
	boolean[][] used;
	
	public bingo(int[][] board) {
		this.board = board;
		used = new boolean[5][5];
	}
	
	public void mark(int number) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (board[i][j] == number) {
					used[i][j] = true;
				}
			}
		}
	}
	
	public int getSumUnused() {
		int retVal = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (!used[i][j]) {
					retVal += board[i][j];
				}
			}
		}
		return retVal;
	}
	
	public boolean hasWon() {
		boolean retVal = false;
		//check rows
		outer:
		for (int i = 0; i < 5; i++) {
			retVal = true;
			for (int j = 0; j < 5; j++) {
				if (!used[i][j]) {
					retVal = false;
					continue outer;
				}
				
			}
			if (retVal)
				return true;
		}
		
		//check columns
		outer:
		for (int j = 0; j < 5; j++) {
			retVal = true;
			for (int i = 0; i < 5; i++) {
				
				if (!used[i][j]) {
					retVal = false;
					continue outer;
				}
				
			}
			if (retVal)
				return true;
		}
		return false;
	}
}

public class day4 {
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader("day_4_input.txt"));
		ArrayList<Integer> numbers = new ArrayList<>();
		ArrayList<bingo> boards = new ArrayList<>();
		
		String readLine = input.readLine();
		StringTokenizer st = new StringTokenizer(readLine, ",");
		
		while (st.hasMoreTokens()) {
			numbers.add(Integer.parseInt(st.nextToken()));
		}
		
		readLine = input.readLine();
		readLine = input.readLine();
		
		while (readLine != null) {
			String line1 = readLine.trim();
			String line2 = input.readLine().trim();
			String line3 = input.readLine().trim();
			String line4 = input.readLine().trim();
			String line5 = input.readLine().trim();
			int[][] given_board = new int[5][5];
			st = new StringTokenizer(line1, " ");
			for (int i = 0; i < 5; i++) {
				given_board[i][0] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(line2, " ");
			for (int i = 0; i < 5; i++) {
				given_board[i][1] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(line3, " ");
			for (int i = 0; i < 5; i++) {
				given_board[i][2] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(line4, " ");
			for (int i = 0; i < 5; i++) {
				given_board[i][3] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(line5, " ");
			for (int i = 0; i < 5; i++) {
				given_board[i][4] = Integer.parseInt(st.nextToken());
			}
			bingo b = new bingo(given_board);
			boards.add(b);
			input.readLine();
			
			readLine = input.readLine();
			
		}
		int[] scores = new int[boards.size()];
		
		for (int i = 0; i < numbers.size(); i++) {
			
			
			int to_mark = numbers.get(i);
			for (int j = 0; j < boards.size(); j++) {
				if (scores[j] == 0) {
					
					boards.get(j).mark(to_mark);
					if (boards.get(j).hasWon()) {
						scores[j] = to_mark * boards.get(j).getSumUnused();
						System.out.println(scores[j]);
					}
				}
			}
		}
		
	}
	
}
