package day5;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

class PairPoint {
	Point p1;
	Point p2;
}

public class day5 {
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader("day_5_input.txt"));
		ArrayList<PairPoint> inputs = new ArrayList<>();
		String readLine = input.readLine();
		StringTokenizer st = new StringTokenizer(readLine);
		
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		while (readLine != null) {
			String rest = readLine;
//			inputs.add(readLine);
			st = new StringTokenizer(readLine);
			PairPoint p = new PairPoint();
			String x1 = readLine.split(",", 2)[0];
			rest = readLine.split(",", 2)[1];
			String y1 = rest.split(" ", 2)[0];
			rest = rest.split(" ", 2)[1];
			String arrow = rest.split(" ", 2)[0];
			rest = rest.split(" ", 2)[1];
			String x2 = rest.split(",", 2)[0];
			String y2 = rest.split(",", 2)[1];
			p.p1 = new Point(Integer.parseInt(x1), Integer.parseInt(y1));
			p.p2 = new Point(Integer.parseInt(x2), Integer.parseInt(y2));
			inputs.add(p);
			if (p.p1.x > maxX) {
				maxX = p.p1.x;
			}
			if (p.p1.y > maxY) {
				maxY = p.p1.y;
			}
			if (p.p1.x < minX) {
				minX = p.p1.x;
			}
			if (p.p1.y < minY) {
				minY = p.p1.y;
			}
			if (p.p2.x > maxX) {
				maxX = p.p2.x;
			}
			if (p.p2.y > maxY) {
				maxY = p.p2.y;
			}
			if (p.p2.x < minX) {
				minX = p.p2.x;
			}
			if (p.p2.y < minY) {
				minY = p.p2.y;
			}
			
			readLine = input.readLine();
		}
		
		int[][] grid = new int[maxX - minX + 1][maxY - minY + 1];
		
		for (PairPoint p : inputs) {
			if (p.p1.x == p.p2.x) {
				for (int i = Math.min(p.p1.y, p.p2.y); i <= Math.max(p.p1.y, p.p2.y); i++) {
					grid[p.p1.x - minX][i - minY] += 1;
				}
			}
			else if (p.p1.y == p.p2.y) {
				for (int i = Math.min(p.p1.x, p.p2.x); i <= Math.max(p.p1.x, p.p2.x); i++) {
					grid[i - minX][p.p1.y - minY] += 1;
				}
			}
		}
		
		int[][] diagonal_grid = new int[maxX - minX + 1][maxY - minY + 1];
		
		for (PairPoint p : inputs) {
//			if (p.p1.x == p.p2.x) {
//				for (int i = Math.min(p.p1.y, p.p2.y); i <= Math.max(p.p1.y, p.p2.y); i++) {
//					grid[p.p1.x - minX][i - minY] += 1;
//				}
//			}
//			else if (p.p1.y == p.p2.y) {
//				for (int i = Math.min(p.p1.x, p.p2.x); i <= Math.max(p.p1.x, p.p2.x); i++) {
//					grid[i - minX][p.p1.y - minY] += 1;
//				}
//			}
			Point p1 = p.p1;
			Point p2 = p.p2;
			if (p.p1.x == p.p2.x) {
				for (int i = Math.min(p.p1.y, p.p2.y); i <= Math.max(p.p1.y, p.p2.y); i++) {
					diagonal_grid[p.p1.x - minX][i - minY] += 1;
				}
			}
			else if (p.p1.y == p.p2.y) {
				for (int i = Math.min(p.p1.x, p.p2.x); i <= Math.max(p.p1.x, p.p2.x); i++) {
					diagonal_grid[i - minX][p.p1.y - minY] += 1;
				}
			}
			if (Math.abs(p.p1.x - p.p2.x) == Math.abs(p.p1.y - p.p2.y)) {
				int stepx = (p.p1.x - p.p2.x) / Math.abs(p.p1.x - p.p2.x);
				int stepy = (p.p1.y - p.p2.y) / Math.abs(p.p1.y - p.p2.y);
				int gap = p.p2.x - p.p1.x;
				for (int i = 0; i <= Math.max(p.p1.x, p.p2.x) - Math.min(p.p1.x, p.p2.x); i++) {
					int x = p1.x - stepx * i;
					int y = p1.y - stepy * i;
					diagonal_grid[x - minX][y - minY] += 1;
				}
			}
		}
		
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] > 1) {
					count++;
				}
			}
		}
		System.out.println(count);
		
		int count2 = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (diagonal_grid[i][j] > 1) {
					count2++;
				}
			}
		}
		System.out.println(count2);
		
		
	}
	
}
