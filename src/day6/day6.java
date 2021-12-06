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
		ArrayList<String> inputs = new ArrayList<>();
		String readLine = input.readLine();
		StringTokenizer st = new StringTokenizer(readLine);
		
		while (readLine != null) {
			inputs.add(readLine);
			
			readLine = input.readLine();
			
		}
		System.out.println(inputs.size());
		
	}
	
}
