package day8;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class PairPoint {
	Point p1;
	Point p2;
}

public class day8 {
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader("day_8_input.txt"));
		ArrayList<Integer> inputs = new ArrayList<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		String readLine = input.readLine();
		StringTokenizer st = new StringTokenizer(readLine, ",");
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		while (st.hasMoreTokens()) {
			int number = Integer.parseInt(st.nextToken());
			inputs.add(number);
			if (map.containsKey(number)) {
				map.put(number, map.get(number) + 1);
			} else {
				map.put(number, 1);
			}
			
			if (number < min) {
				min = number;
			}
			if (number > max) {
				max = number;
			}
		}
		
		int minFuel = Integer.MAX_VALUE;
		for (int i = min; i <= max; i++) {
			int dist = 0;
			for(Integer key : map.keySet()){
				dist += Math.abs(key - i) * map.get(key);
			}
			if (dist < minFuel) {
				minFuel = dist;
			}
		}
		
		System.out.println(minFuel);
		
		
		int minFuel2 = Integer.MAX_VALUE;
		for (int i = min; i <= max; i++) {
			int dist = 0;
			for(Integer key : map.keySet()){
				int delta = Math.abs(key - i);
				dist += delta * (delta + 1) / 2 * map.get(key);
			}
			if (dist < minFuel2) {
				minFuel2 = dist;
			}
		}
		
		System.out.println(minFuel2);
		
		
		
	}
	
}
