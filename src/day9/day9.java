package day9;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class day9 {
	static boolean[][] is_low = new boolean[100][100];
	static int[][] floodfill = new int[100][100];
	static HashMap<Integer, Integer> map = new HashMap<>();
	static int ymax = 0;
	static int x = 0;
	static int[][] grid = new int[100][100];
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader("day_9_input.txt"));
		
		String readLine = input.readLine();
		StringTokenizer st = new StringTokenizer(readLine);
		

		while (readLine != null) {
			String rest = readLine.trim();
			
			int y = 0;
			st = new StringTokenizer(rest, "");
			String[] readCharacters = rest.split("");
			for (y = 0; y < readCharacters.length; y++) {
				String token = readCharacters[y];
				int tokenInt = Integer.parseInt(token);
				grid[x][y] = tokenInt;
			}
			ymax = y;
			x++;
			readLine = input.readLine();
		}
		int total = 0;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < ymax; j++) {
				int val = grid[i][j];
				boolean low = true;
				ArrayList<Point> adj = new ArrayList<Point>();
				adj.add(new Point(i - 1, j));
				adj.add(new Point(i + 1, j));
				adj.add(new Point(i, j - 1));
				adj.add(new Point(i, j + 1));
				if (i == 0) {
					adj.remove(new Point(i - 1, j));
				} else if (i == x - 1) {
					adj.remove(new Point(i + 1, j));
				}
				if (j == 0) {
					adj.remove(new Point(i, j - 1));
				} else if (j == ymax - 1) {
					adj.remove(new Point(i, j + 1));
				}
				for (Point p : adj) {
					if (grid[p.x][p.y] <= grid[i][j]) {
						low = false;
					}
				}
				if (low){
					total += (grid[i][j] + 1);
					is_low[i][j] = true;
				}
			}
		}
		int count = 1;
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < ymax; j++) {
				if (is_low[i][j]) {
					map.put(count, 0);
					ff(new Point(i, j), count);
					
					count++;
				}
			}
		}
		ArrayList<Integer> highestVals = new ArrayList<Integer>();
		
		for (int key : map.keySet()) {
			highestVals.add(map.get(key));
		}
		
		Collections.sort(highestVals);
		Collections.reverse(highestVals);
		
		
		System.out.println(total);
		
		System.out.println(highestVals.get(0) * highestVals.get(1) * highestVals.get(2));
	}
	
	public static void ff(Point p, int val) {
		ArrayList<Point> adj = new ArrayList<Point>();
		adj.add(new Point(p.x - 1, p.y));
		adj.add(new Point( p.x+ 1, p.y));
		adj.add(new Point(p.x, p.y - 1));
		adj.add(new Point(p.x, p.y + 1));
		if (p.x == 0) {
			adj.remove(new Point(p.x - 1, p.y));
		} else if (p.x == x - 1) {
			adj.remove(new Point(p.x + 1, p.y));
		}
		if (p.y == 0) {
			adj.remove(new Point(p.x, p.y - 1));
		} else if (p.y == ymax - 1) {
			adj.remove(new Point(p.x, p.y + 1));
		}
		floodfill[p.x][p.y] = val;
		map.put(val, map.get(val) + 1);
		for (Point p_adj : adj) {
			if (floodfill[p_adj.x][p_adj.y] == 0 && grid[p_adj.x][p_adj.y] > grid[p.x][p.y] && grid[p_adj.x][p_adj.y] != 9) {
				ff(p_adj, val);
			}
		}
	}
	
}
