package day3;

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
		int size = 0;
		while (readLine != null) {
			inputs.add(readLine);
			size = readLine.length();
			readLine = input.readLine();
			
		}
		
		int[] gamma = new int[size];
		for (int k = 0; k < inputs.size(); k++) {
			String token = inputs.get(k);
			for (int i = 0; i < size; i++) {
				if (token.charAt(i) == '1') {
					gamma[i]++;
				}
			}
		}
		int gamma_result = 0;
		int epsilon_result = 0;
		for (int i = 0; i < size; i++) {
			gamma_result *= 2;
			epsilon_result *= 2;
			if (gamma[i] > (inputs.size() / 2)) {
				gamma_result += 1;
			} else {
				epsilon_result += 1;
			}
		}
		System.out.println("Gamma: " + gamma_result);
		System.out.println(gamma_result * epsilon_result);
		
		ArrayList<String> max_o2 = (ArrayList<String>) inputs.clone();
		ArrayList<String> min_co2 = (ArrayList<String>) inputs.clone();
		String o2 = "";
		String co2 = "";
		for (int i = 0; i < size; i++) {

			if (max_o2.size() == 1) {
				o2 = max_o2.get(0);
			} else {
				int max = 0;
				for (int j = 0; j < max_o2.size(); j++) {
					if (max_o2.get(j).charAt(i) == '1') {
						max++;
					}
				}
				boolean remove_0 = max * 2 > (max_o2.size());
				if (max * 2 == (max_o2.size())) {
					remove_0 = true;
				}
				for (int j = max_o2.size() - 1; j >= 0; j--) {
					if ((max_o2.get(j).charAt(i) == '0') == remove_0) {
						max_o2.remove(j);
					}
				}
			}
			if (min_co2.size() == 1) {
				continue;
			} else {
				int min = 0;
				for (int j = 0; j < min_co2.size(); j++) {
					if (min_co2.get(j).charAt(i) == '1') {
						min++;
					}
				}
				boolean remove_0 = min * 2 < (min_co2.size());
				if (min * 2 == (min_co2.size())) {
					remove_0 = false;
				}
				for (int j = min_co2.size() - 1; j >= 0; j--) {
					if ((min_co2.get(j).charAt(i) == '0') == remove_0) {
						min_co2.remove(j);
					}
				}
			}
//			if (max_o2.size() == min_co2.size() && max_o2.size() == 1) break;
		}
		
		o2 = max_o2.get(0);
		co2 = min_co2.get(0);
		int o2_result = 0;
		int co2_result = 0;
		for (int i = 0; i < size; i++) {
			o2_result *= 2;
			co2_result *= 2;
			if (o2.charAt(i) == '1') {
				o2_result += 1;
			}
			if (co2.charAt(i) == '1') {
				co2_result += 1;
			}
			
		}
		System.out.println(o2_result * co2_result);
		
	}
}
