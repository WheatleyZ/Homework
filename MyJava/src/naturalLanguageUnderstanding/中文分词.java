package naturalLanguageUnderstanding;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 中文分词 {

    public static void main(String args[]) throws Exception {
        String dicPath = "/Users/admin/Desktop/Co2M.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dicPath)));
        String rawStr = "香港女记者速度惊人";
        HashMap<String, Integer> dic = new HashMap<>();
        Vector<String> processedStr = new Vector<>();
        while (br.ready()) {
            String[] tmp = br.readLine().split(" ");
            dic.put(tmp[0], Integer.parseInt(tmp[2]));
        }
        int head = 0;
        int tail;
        while (head < rawStr.length()) {
            tail = rawStr.length();
            while (head <= tail) {
                if (dic.containsKey(rawStr.substring(head, tail)) || head == tail) {
                    processedStr.add(rawStr.substring(head, tail));
                    head = tail;
                    break;
                } else tail--;
            }
        }
        System.out.println(processedStr);
    }
}
