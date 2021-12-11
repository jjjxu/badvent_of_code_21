package day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class hexdisplay {
	ArrayList<Set<Character>> inputs = new ArrayList<>();
	char[] wires = new char[7];
	Set<Character>[] data = new Set[10];
	
	public void insert(String input) {
		Set<Character> toAdd = new HashSet<>();
		char[] in = input.toCharArray();
		Arrays.sort(in);
		for (int i = 0; i < in.length; i++) {
			toAdd.add(in[i]);
		}
		inputs.add(toAdd);
	}
	
	public void calculate() {
		//calculate A
		Set<Character> oneSet = new HashSet<>();
		Set<Character> sevenSet = new HashSet<>();
		Set<Character> fourSet = new HashSet<>();
		Set<Character> eightSet = new HashSet<>();
		for (int i = inputs.size() - 1; i >= 0; i--) {
			if (inputs.get(i).size() == 2) {
				oneSet = inputs.get(i);
				data[1] = Set.copyOf(oneSet);
				inputs.remove(i);
			}
			else if (inputs.get(i).size() == 3) {
				sevenSet = inputs.get(i);
				data[7] = Set.copyOf(sevenSet);
				inputs.remove(i);
			}
			else if (inputs.get(i).size() == 4) {
				fourSet = inputs.get(i);
				data[4] = Set.copyOf(fourSet);
				inputs.remove(i);
			}
			else if (inputs.get(i).size() == 7) {
				eightSet = inputs.get(i);
				data[8] = Set.copyOf(eightSet);
				inputs.remove(i);
			}
		}
		Set<Character> aSet = new HashSet<>(Set.copyOf(sevenSet));
		aSet.removeAll(oneSet);
		wires[0] = aSet.iterator().next();
		Set<Character> nineSet = new HashSet<>(Set.copyOf(fourSet));
		nineSet.add(wires[0]);
		
		for (int i = inputs.size() - 1; i >= 0; i--) {
			Set<Character> tempSet = inputs.get(i);
			if(tempSet.containsAll(nineSet)) {
				data[9] = Set.copyOf(tempSet);
				Set<Character> gSet = new HashSet<>(Set.copyOf(tempSet));
				gSet.removeAll(nineSet);
				wires[6] = gSet.iterator().next();
				
				nineSet = Set.copyOf(tempSet);
				
				Set<Character> eSet = new HashSet<>(Set.copyOf(eightSet));
				eSet.removeAll(nineSet);
				wires[4] = eSet.iterator().next();
				
				inputs.remove(i);
				break;
			}
		}
		Set<Character> sixSet = new HashSet<>(Set.copyOf(nineSet));
		sixSet.removeAll(oneSet);
		sixSet.add(wires[4]);
		
		for (int i = inputs.size() - 1; i >= 0; i--) {
			Set<Character> tempSet = inputs.get(i);
			if(tempSet.containsAll(sixSet)) {
				data[6] = Set.copyOf(tempSet);
				Set<Character> fSet = new HashSet<>(Set.copyOf(tempSet));
				fSet.removeAll(sixSet);
				wires[5] = fSet.iterator().next();
				
				sixSet = Set.copyOf(tempSet);
				
				inputs.remove(i);
				break;
			}
		}
		
		Set<Character> fiveSet = new HashSet<>(Set.copyOf(sixSet));
		fiveSet.remove(wires[4]);
		for (int i = inputs.size() - 1; i >= 0; i--) {
			Set<Character> tempSet = inputs.get(i);
			if(tempSet.containsAll(fiveSet) && fiveSet.containsAll(tempSet)) {
				data[5] = Set.copyOf(tempSet);
				
				inputs.remove(i);
				break;
			}
		}
		
		Set<Character> threeSet = new HashSet<>(Set.copyOf(sevenSet));
		threeSet.add(wires[6]);
		for (int i = inputs.size() - 1; i >= 0; i--) {
			Set<Character> tempSet = inputs.get(i);
			if(tempSet.containsAll(threeSet) && tempSet.size() == 5) {
				Set<Character> dSet = new HashSet<>(Set.copyOf(tempSet));
				dSet.removeAll(threeSet);
				wires[3] = dSet.iterator().next();
				
				data[3] = Set.copyOf(tempSet);
				inputs.remove(i);
				break;
			}
		}
		Set<Character> zeroSet = new HashSet<>(Set.copyOf(eightSet));
		zeroSet.remove(wires[3]);
		for (int i = inputs.size() - 1; i >= 0; i--) {
			Set<Character> tempSet = inputs.get(i);
			if(tempSet.containsAll(zeroSet) && zeroSet.containsAll(tempSet)) {
				
				data[0] = Set.copyOf(tempSet);
				inputs.remove(i);
				break;
			}
		}
		System.out.println(inputs.size());
		data[2] = Set.copyOf(inputs.get(0));
	}
	
	public int convert(String in) {
		Set<Character> toComp = new HashSet<>();
		char[] in_char_arr = in.toCharArray();
		for (int i = 0; i < in_char_arr.length; i++) {
			toComp.add(in_char_arr[i]);
		}
		for (int i = 0; i < data.length; i++) {
			if(data[i].containsAll(toComp) && toComp.containsAll(data[i])) {
				return i;
			}
		}
		return -1;
	}
	
}

public class day8 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader("day_8_input.txt"));
		ArrayList<Integer> inputs = new ArrayList<>();
		
		String readLine = input.readLine();
		StringTokenizer st = new StringTokenizer(readLine);
		
		int total = 0;
		int hextotal = 0;
		while (readLine != null) {
			hexdisplay mysegment = new hexdisplay();
			String rest = readLine;
			
			String[] two = readLine.split("\\|");
			
			st = new StringTokenizer(two[1], " ");
			while (st.hasMoreTokens()) {
				String temp = st.nextToken();
				if (temp.length() == 2 || temp.length() == 3 || temp.length() == 4 || temp.length() == 7) {
					total++;
				}
			}
			
			st = new StringTokenizer(two[0], " ");
			while (st.hasMoreTokens()) {
				mysegment.insert(st.nextToken());
			}
			mysegment.calculate();
			
			int tempTotal = 0;
			st = new StringTokenizer(two[1], " ");
			while (st.hasMoreTokens()) {
				String temp = st.nextToken();
				tempTotal *= 10;
				tempTotal += mysegment.convert(temp);
			}
			hextotal += tempTotal;
			readLine = input.readLine();
		}
		System.out.println(total);
		System.out.println(hextotal);
	}
	
}
