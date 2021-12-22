package day22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class day22 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader("day_22_input.txt"));
		
		String readLine = input.readLine();
		StringTokenizer st = new StringTokenizer(readLine);
		ArrayList<String> raw_lines = new ArrayList<String>();
		ArrayList<char[]> raw_chars = new ArrayList<>();
		
		int x = 0;
		outer:while (readLine != null) {
			String rest = readLine.trim();
			char[] chars = rest.toCharArray();
			st = new StringTokenizer(rest, "");
			int y = 0;
			raw_lines.add(rest);
			raw_chars.add(chars);
			
			x++;
			readLine = input.readLine();
			
		}
		
		System.out.println(raw_lines.size());
		
	}
	
	
}
