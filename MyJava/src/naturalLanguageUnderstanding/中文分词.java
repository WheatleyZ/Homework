package naturalLanguageUnderstanding;

import com.sun.org.apache.xpath.internal.operations.Equals;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 中文分词 {

    public static void main(String args[]) throws Exception {
        String dicPath = "/Users/admin/Desktop/Co2M.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dicPath)));
        String rawStr = "香港女记者速度惊人";
        HashMap<String, Integer> weightedDic = new HashMap<>();
        Vector<String> dic = new Vector<>();
        Vector<String> optionalWord = new Vector<>();
        Vector<String> processedStr = new Vector<>();
        while (br.ready()) {
            String[] tmp = br.readLine().split(" ");
            weightedDic.put(tmp[0],tmp[0].length()*Integer.parseInt(tmp[2]));
        }
        List<Map.Entry<String, Integer>> weightedDicList = new ArrayList<>(weightedDic.entrySet());
        weightedDicList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        for (Map.Entry<String, Integer> e : weightedDicList) {
            dic.add(e.getKey());
        }
        int head = 0;
        int tail = rawStr.length();
        while (head < rawStr.length()) {
            tail = rawStr.length();
            while (head <= tail) {
                if (head == tail) {
                    int maxCount = dic.size();
                    for (String str : optionalWord) {
                        if (dic.indexOf(str) < maxCount) maxCount = dic.indexOf(str);
                    }
                    processedStr.add(rawStr.substring(head, head + dic.elementAt(maxCount).length()));
                    head += dic.elementAt(maxCount).length();
                    break;
                } else if (dic.contains(rawStr.substring(head, tail))) {
                    optionalWord.add(rawStr.substring(head, tail));
                    tail--;
                } else tail--;
            }
            optionalWord.clear();
        }
        System.out.println(processedStr);
    }
}
