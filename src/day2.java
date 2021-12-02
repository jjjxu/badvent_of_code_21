import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class day2 {
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader("day_2_input.txt"));
		ArrayList<String> inputs = new ArrayList<>();
		String readLine = input.readLine();
		while (readLine != null) {
			inputs.add(readLine);
			readLine = input.readLine();
		}
		
		int vert_counter = 0;
		int horiz_counter = 0;
		
		for (int i = 0; i < inputs.size(); i++) {
			StringTokenizer st = new StringTokenizer(inputs.get(i));
			if (st.hasMoreTokens()) {
				String first = st.nextToken();
				if (first.equals("forward")) {
					horiz_counter += Integer.parseInt(st.nextToken());
				} else if (first.equals("down")) {
					vert_counter -= Integer.parseInt(st.nextToken());
				} else if (first.equals("up")) {
					vert_counter += Integer.parseInt(st.nextToken());
				}
			}
		}
		
		

		
		System.out.println(vert_counter);
		System.out.println(horiz_counter);
		
		vert_counter = 0;
		horiz_counter = 0;
		int aim = 0;
		
		for (int i = 0; i < inputs.size(); i++) {
			StringTokenizer st = new StringTokenizer(inputs.get(i));
			if (st.hasMoreTokens()) {
				String first = st.nextToken();
				if (first.equals("forward")) {
					int move = Integer.parseInt(st.nextToken());
					horiz_counter += move;
					vert_counter += move * aim;
				} else if (first.equals("down")) {
					aim -= Integer.parseInt(st.nextToken());
				} else if (first.equals("up")) {
					aim += Integer.parseInt(st.nextToken());
				}
			}
		}
		
		System.out.println(vert_counter);
		System.out.println(horiz_counter);

	}
}
