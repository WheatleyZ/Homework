package naturalLanguageUnderstanding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.Map.Entry;

public class 词频统计 {

	public static void main(String[] args) throws IOException {
		HashMap<String, Integer> monoGram = new HashMap<>();
		HashMap<String, Integer> biGram = new HashMap<>();
		HashMap<String, Integer> triGram = new HashMap<>();
		String pathname = "C:/users/zheng/desktop/Ci.txt";
		String buffer = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathname), "UTF-8"));
		while (br.ready()) {
			String line = br.readLine();
			if (line.length() > 10) {
				String[] read = line.split("，|。|、|：|“|”|！|？|《|》|<|>|（|）|【|】|●|■|□|〓|〔|〕|‘|’| ");
				for (String str : read) {
					for (int i = 0; i < str.length(); i++) {
						if (monoGram.containsKey("" + str.charAt(i))) {
							monoGram.replace("" + str.charAt(i), monoGram.get("" + str.charAt(i)) + 1);
						} else {
							monoGram.put("" + str.charAt(i), 1);
						}
					}
					for (int i = 0; i < str.length() - 1; i++) {
						if (biGram.containsKey(str.substring(i, i + 2))) {
							biGram.replace(str.substring(i, i + 2), biGram.get(str.substring(i, i + 2)) + 1);
						} else {
							biGram.put(str.substring(i, i + 2), 1);
						}
					}
					for (int i = 0; i < str.length() - 2; i++) {
						if (triGram.containsKey(str.substring(i, i + 3))) {
							triGram.replace(str.substring(i, i + 3), triGram.get(str.substring(i, i + 3)) + 1);
						} else {
							triGram.put(str.substring(i, i + 3), 1);
						}
					}
				}
			}
		}
		List<Map.Entry<String, Integer>> monoGramList = new ArrayList<Map.Entry<String, Integer>>(monoGram.entrySet());
		List<Map.Entry<String, Integer>> biGramList = new ArrayList<Map.Entry<String, Integer>>(biGram.entrySet());
		List<Map.Entry<String, Integer>> triGramList = new ArrayList<Map.Entry<String, Integer>>(triGram.entrySet());
		Collections.sort(monoGramList, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		Collections.sort(biGramList, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		Collections.sort(triGramList, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		// System.out.println(monoGramList.toString());
		// System.out.println(biGramList.toString());
		BufferedWriter mbw = new BufferedWriter(new FileWriter("C:/users/zheng/desktop/CoM.txt"));
		BufferedWriter bbw = new BufferedWriter(new FileWriter("C:/users/zheng/desktop/CoB.txt"));
		BufferedWriter tbw = new BufferedWriter(new FileWriter("C:/users/zheng/desktop/CoT.txt"));
		for (Entry<String, Integer> item : monoGramList) {
			mbw.write(item.getKey() + " " + item.getValue() + "\r\n");
		}
		for (Entry<String, Integer> item : biGramList) {
			bbw.write(item.getKey() + " " + item.getValue() + "\r\n");
		}
		for (Entry<String, Integer> item : triGramList) {
			tbw.write(item.getKey() + " " + item.getValue() + "\r\n");
		}
		mbw.close();
		bbw.close();
		tbw.close();
		return;
	}

}
