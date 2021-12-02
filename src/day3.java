import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class day3 {
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader("day_3_input.txt"));
		ArrayList<String> inputs = new ArrayList<>();
		String readLine = input.readLine();
		while (readLine != null) {
			inputs.add(readLine);
			readLine = input.readLine();
		}

	}
}
