package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class day10b {
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
		
		int count = 0;
		int count2 = 0;
		ArrayList<Long> counts = new ArrayList<>();
		outer: while (readLine != null) {
			String rest = readLine.trim();
			char[] chars = rest.toCharArray();
			Stack<Character> mySyntax = new Stack<>();
			for (int i = 0; i < chars.length; i++) {
				if (chars[i] == '(') {
					mySyntax.push(chars[i]);
				}
				else if (chars[i] == ')') {
					char test = mySyntax.pop();
					if (test != '(') {
						count += 3;
						readLine = input.readLine();
						continue outer;
					}
				}
				else if (chars[i] == '[') {
					mySyntax.push(chars[i]);
				}
				else if (chars[i] == ']') {
					char test = mySyntax.pop();
					if (test != '[') {
						count += 57;
						readLine = input.readLine();
						continue outer;
					}
				}
				else if (chars[i] == '{') {
					mySyntax.push(chars[i]);
				}
				else if (chars[i] == '}') {
					char test = mySyntax.pop();
					if (test != '{') {
						count += 1197;
						readLine = input.readLine();
						continue outer;
					}
				}
				else if (chars[i] == '<') {
					mySyntax.push(chars[i]);
				}
				else if (chars[i] == '>') {
					char test = mySyntax.pop();
					if (test != '<') {
						count += 25137;
						readLine = input.readLine();
						continue outer;
					}
				}
			}
			
			if (!mySyntax.isEmpty()) {
				long tempCount = 0;
				for (int i = mySyntax.size() - 1; i >= 0; i--) {
					tempCount *= 5;
					if (mySyntax.get(i) == '(') {
						tempCount += 1;
					} else if (mySyntax.get(i) == '[') {
						tempCount += 2;
					} else if (mySyntax.get(i) == '{') {
						tempCount += 3;
					} else if  (mySyntax.get(i) == '<') {
						tempCount += 4;
					}
				}
				counts.add(tempCount);
				count2 += tempCount;
			}

			readLine = input.readLine();
		}
		
		Collections.sort(counts);
		
		System.out.println(count);
		System.out.println(count2);
		
		System.out.println(counts.get(counts.size() / 2));
		
	}
	
	
}
