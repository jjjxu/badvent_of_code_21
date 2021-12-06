package day6;

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

public class day6 {
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader("day_6_input.txt"));
		ArrayList<Integer> inputs = new ArrayList<>();
		String readLine = input.readLine();
		StringTokenizer st = new StringTokenizer(readLine, ",");
		long[] fishies = new long[9];
		while (st.hasMoreTokens()) {
			int number = Integer.parseInt(st.nextToken());
			inputs.add(number);
			fishies[number]++;
		}
		for (int i = 0; i < 80; i++) {
			int start_size = inputs.size();
			for (int k = 0; k < start_size; k++) {
				int number = inputs.get(k);
				number -= 1;
				inputs.set(k, number);
				if (number < 0) {
					inputs.set(k, 6);
					inputs.add(8);
				}
			}
		}
		for (int i = 0; i < 256; i++) {
			long generate = fishies[0];
			for (int k = 0; k < 8; k++) {
				fishies[k] = fishies[k + 1];
			}
			fishies[8] = generate;
			fishies[6] += generate;
		}
		long count = 0;
		for (int i = 0; i < fishies.length; i++) {
			count += fishies[i];
		}
		
		
		System.out.println(inputs.size());
		System.out.println(count);
	}
	
}
