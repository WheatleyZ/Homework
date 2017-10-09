package naturalLanguageUnderstanding;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 中文分词 {

    private static Frame window;
    private static Panel top, bottom;
    private static Button gen;
    private static TextField model;
    private static Label generated;

    public static void main(String args[]) throws Exception {
        window = new Frame("中文分词");
        top = new Panel();
        bottom = new Panel();
        generated = new Label();
        generated.setSize(600, 300);
        generated.setAlignment(Label.CENTER);
        model = new TextField(80);
        gen = new Button("拆！");
        window.setSize(600, 400);
        window.setVisible(true);
        window.add(top, "North");
        window.add(generated, "Center");
        window.add(bottom, "South");
        top.add(model);
        bottom.add(gen);
        gen.addActionListener(e -> {
            try {
                generated.setText(splitWord(model.getText()));
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
    }

    private static String splitWord(String rawStr) throws Exception {
        String dicPath = "/Users/admin/Desktop/Co2M.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dicPath)));
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
        StringBuffer result = new StringBuffer();
        for (String s : processedStr) {
            result.append(" ").append(s);
        }
        return result.toString();
    }
}
