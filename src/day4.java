import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class day4 {
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader("day_4_input.txt"));
		ArrayList<String> inputs = new ArrayList<>();
		String readLine = input.readLine();
		
		while (readLine != null) {
			inputs.add(readLine);
			
			readLine = input.readLine();
			
		}
		System.out.println(inputs.size());
		
	}
}
