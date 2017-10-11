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

    private static void addWord(HashMap<String, Integer> Gram, String word) {
        if (Gram.containsKey(word)) {
            Gram.replace(word, Gram.get(word) + 1);
        } else {
            Gram.put(word, 1);
        }
    }

    public static void main(String[] args) throws IOException {
        HashMap<String, Integer> monoGram = new HashMap<>();
        HashMap<String, Integer> biGram = new HashMap<>();
        String pathname = "/Users/admin/Desktop/Ci2.txt";
        String buffer = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathname)));
        while (br.ready()) {
            String[] read = br.readLine().split("  ");
            read[0]="<BOS>/m";
            for (int i=0;i<read.length;i++) {
                if (read[i].contains("[")) {
                    read[i] = read[i].substring(1, read[i].length());
                } else if (read[i].contains("]")) {
                    read[i] = read[i].substring(0, read[i].indexOf("]"));
                }
            }
            for (String aRead : read) {
                addWord(monoGram, aRead);
            }
            for (int i = 1; i < read.length; i++) {
                addWord(biGram, read[i - 1] + " " + read[i]);
            }
        }
        List<Entry<String, Integer>> monoGramList = new ArrayList<>(monoGram.entrySet());
        List<Entry<String, Integer>> biGramList = new ArrayList<>(biGram.entrySet());
        monoGramList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        biGramList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        Vector<triple> monoV = new Vector<>();
        for (Entry<String, Integer> e : monoGramList) {
            String[] str = e.getKey().split("/");
            if (str.length > 1) {
                中文词频统计 c = new 中文词频统计();
                monoV.add(c.new triple(str[0], str[1], e.getValue()));
            }
        }
        Vector<dualTriple> biV = new Vector<>();
        for (Entry<String, Integer> e : biGramList) {
            String[] str = e.getKey().split(" |/");
            if (str.length > 1) {
                中文词频统计 c = new 中文词频统计();
                biV.add(c.new dualTriple(str[0], str[1], str[2], str[3], e.getValue()));
            }
        }
        BufferedWriter mbw = new BufferedWriter(new FileWriter("/Users/admin/Desktop/Co2M.txt"));
        BufferedWriter bbw = new BufferedWriter(new FileWriter("/Users/admin/Desktop/Co2B.txt"));
        for (triple t : monoV) {
            mbw.write(t.word + " " + t.type + " " + t.count + "\r\n");
        }
        for (dualTriple d : biV) {
            bbw.write(d.word1 + " " + d.word2 + " " + d.type1 + " " + d.type2 + " " + d.count + "\r\n");
        }
        mbw.close();
        bbw.close();
    }

}
