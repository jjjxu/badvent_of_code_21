package day1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class day1 {
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader("day_1_input.txt"));
		ArrayList<Integer> inputs = new ArrayList<>();
		String readLine = input.readLine();
		while (readLine != null) {
			inputs.add(Integer.parseInt(readLine));
			readLine = input.readLine();
		}
		
		int counter = 0;
		int lastval = Integer.MAX_VALUE;
		
		for (int i = 0; i < inputs.size(); i++) {
			if (inputs.get(i) > lastval) {
				counter++;
			}
			lastval = inputs.get(i);
		}
		
		int counter2 = 0;
		int[] last3vals = new int[3];
		last3vals[0] = Integer.MAX_VALUE;
		last3vals[1] = Integer.MAX_VALUE;
		last3vals[2] = Integer.MAX_VALUE;
		for (int i = 0; i < inputs.size(); i++) {
			long prev_sum = ((long)last3vals[0]) + ((long)last3vals[1]) + ((long)last3vals[2]);
			last3vals[0] = last3vals[1];
			last3vals[1] = last3vals[2];
			last3vals[2] = inputs.get(i);
			long curr_sum = ((long)last3vals[0]) + ((long)last3vals[1]) + ((long)last3vals[2]);
			if (curr_sum > prev_sum) {
				counter2++;
			}
		}
		
		System.out.println(counter);
		System.out.println(counter2);
	}
}
