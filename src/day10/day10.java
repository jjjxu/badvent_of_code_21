package day10;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class day10 {
	static boolean[][] is_low = new boolean[100][100];
	static int[][] floodfill = new int[100][100];
	static HashMap<Integer, Integer> map = new HashMap<>();
	static int ymax = 0;
	static int x = 0;
	static int[][] grid = new int[100][100];
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader("day_10_input.txt"));
		
		String readLine = input.readLine();
		StringTokenizer st = new StringTokenizer(readLine);
		

		while (readLine != null) {
			String rest = readLine.trim();
			
			int y = 0;
			st = new StringTokenizer(rest, "");

			readLine = input.readLine();
		}
		
	}
	
	
}
