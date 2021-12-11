package day11;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class day11 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader("day_11_input.txt"));
		
		String readLine = input.readLine();
		StringTokenizer st = new StringTokenizer(readLine);
		int[][] grid = new int[10][10];
		
		
		int count = 0;
		outer:while (readLine != null) {
			String rest = readLine.trim();
			char[] chars = rest.toCharArray();
			st = new StringTokenizer(rest, "");
			int y = 0;
			for (int i = 0; i < chars.length; i++) {
				grid[count][i] = Integer.parseInt(chars[i] + "");
			}
			count++;
			
			readLine = input.readLine();
		}

		int flash_count = 0;
		int step_count = 0;
		stupid: while(true) {
			HashSet<Point> flashed = new HashSet<Point>();
			HashSet<Point> has_flashed = new HashSet<Point>();
			int can_flash = 0;
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 10; k++) {
					grid[j][k]++;
					if (grid[j][k] > 9) {
						flash_count++;
						flashed.add(new Point(j, k));
						grid[j][k] = 0;
						has_flashed.add(new Point(j, k));
					}
				}
			}

			step_count++;
			while(flashed.size() > 0) {

				for (int j = 0; j < 10; j++) {
					for (int k = 0; k < 10; k++) {
						if (flashed.contains(new Point(j, k))) {
							flashed.remove(new Point(j, k));
							HashSet<Point> points = new HashSet<Point>();
							points.add(new Point(j - 1, k - 1));
							points.add(new Point(j - 1, k));
							points.add(new Point(j - 1, k + 1));
							points.add(new Point(j, k - 1));
							points.add(new Point(j, k + 1));
							points.add(new Point(j + 1, k - 1));
							points.add(new Point(j + 1, k));
							points.add(new Point(j + 1, k + 1));
							for (Point p : points) {
								if (p.x >= 0 && p.x < 10 && p.y >= 0 && p.y < 10) {
									if (has_flashed.contains(p)) {
										continue;
									}
									grid[p.x][p.y]++;
									if (grid[p.x][p.y] > 9) {
										flashed.add(p);
										has_flashed.add(p);
										grid[p.x][p.y] = 0;
										flash_count++;
									}
								}
							}
						}
						
					}
				}
			}
			if (has_flashed.size() == 100) {
				System.out.println(step_count);
				break stupid;
			}
//			System.out.println(grid);
			
		}
		
		
//		System.out.println(flash_count);
		
	}
	
	
}
