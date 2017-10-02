package naturalLanguageUnderstanding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

public class 中文词频统计 {
	class triple implements Comparable<triple> {
		String word;
		String type;
		Integer count;

		triple(String w, String t, Integer c) {
			this.word = w;
			this.type = t;
			this.count = c;
		}

		@Override
		public int compareTo(triple o) {
			return this.count - o.count;
		}
	}

	class dualTriple implements Comparable<triple> {
		String word1;
		String type1;
		String word2;
		String type2;
		Integer count;

		dualTriple(String w1, String t1, String w2, String t2, Integer c) {
			this.word1 = w1;
			this.type1 = t1;
			this.word2 = w2;
			this.type2 = t2;
			this.count = c;
		}

		@Override
		public int compareTo(triple o) {
			return this.count - o.count;
		}
	}

	public static void main(String[] args) throws IOException {
		HashMap<String, Integer> monoGram = new HashMap<>();
		HashMap<String, Integer> biGram = new HashMap<>();
		String pathname = "C:/users/zheng/desktop/Ci2.txt";
		String buffer = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathname)));
		while (br.ready()) {
			String[] read = br.readLine().split("  ");
			for (String str : read) {
				if (str.length() > 1) {
					if (monoGram.containsKey(str)) {
						monoGram.replace(str, monoGram.get(str) + 1);
					} else {
						monoGram.put(str, 1);
					}
				}
			}
			for (int i = 0; i < read.length - 1; i++) {
				if (biGram.containsKey(read[i] + " " + read[i + 1])) {
					biGram.replace(read[i] + " " + read[i + 1], biGram.get(read[i] + " " + read[i + 1]) + 1);
				} else {
					biGram.put(read[i] + " " + read[i + 1], 1);
				}
			}
		}
		List<Map.Entry<String, Integer>> monoGramList = new ArrayList<Map.Entry<String, Integer>>(monoGram.entrySet());
		List<Map.Entry<String, Integer>> biGramList = new ArrayList<Map.Entry<String, Integer>>(biGram.entrySet());
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
		Vector<triple> monoV = new Vector<>();
		for (Map.Entry<String, Integer> e : monoGramList) {
			String[] str = e.getKey().split("/");
			if (str.length > 1) {
				中文词频统计 c = new 中文词频统计();
				monoV.add(c.new triple(str[0], str[1], e.getValue()));
			}
		}
		Vector<dualTriple> biV = new Vector<>();
		for (Map.Entry<String, Integer> e : biGramList) {
			String[] str = e.getKey().split(" |/");
			if (str.length > 1) {
				中文词频统计 c = new 中文词频统计();
				biV.add(c.new dualTriple(str[0], str[1], str[2], str[3], e.getValue()));
			}
		}
		BufferedWriter mbw = new BufferedWriter(new FileWriter("C:/users/zheng/desktop/Co2M.txt"));
		BufferedWriter bbw = new BufferedWriter(new FileWriter("C:/users/zheng/desktop/Co2B.txt"));
		for (triple t : monoV) {
			mbw.write(t.word + " " + t.type + " " + t.count + "\r\n");
		}
		for (dualTriple d : biV) {
			bbw.write(d.word1 + " " + d.word2 + " " + d.type1 + " " + d.type2 + " " + d.count + "\r\n");
		}
		mbw.close();
		bbw.close();
		return;
	}

}
