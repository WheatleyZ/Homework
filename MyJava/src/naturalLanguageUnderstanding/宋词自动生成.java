package naturalLanguageUnderstanding;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.swing.*;
import java.util.*;

public class 宋词自动生成 {

	static Frame window;
	static Panel top, bottom;
	static Button gen;
	static TextField model;
	static Label generated;
	static File f;
	static BufferedImage bi;
	static JScrollPane scrollPane;
	static JFileChooser fileChoose;

	public static void main(String[] args) throws Exception {
		window = new Frame("宋词自动生成");
		top = new Panel();
		bottom = new Panel();
		generated = new Label();
		generated.setSize(600, 300);
		generated.setAlignment(Label.CENTER);
		model = new TextField(80);
		gen = new Button("生成！");
		window.setSize(600, 400);
		window.setVisible(true);
		window.add(top, "North");
		window.add(generated, "Center");
		window.add(bottom, "South");
		top.add(model);
		bottom.add(gen);
		gen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					generated.setText(generate(model.getText()));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	static String generate(String model) throws Exception {
		BufferedReader mbr = new BufferedReader(
				new InputStreamReader(new FileInputStream("C:/users/zheng/desktop/CoM.txt"), "UTF-8"));
		BufferedReader bbr = new BufferedReader(
				new InputStreamReader(new FileInputStream("C:/users/zheng/desktop/CoB.txt"), "UTF-8"));
		BufferedReader tbr = new BufferedReader(
				new InputStreamReader(new FileInputStream("C:/users/zheng/desktop/CoT.txt"), "UTF-8"));
		Vector<String> monoWord = new Vector<String>();
		Vector<String> biWord = new Vector<String>();
		Vector<String> triWord = new Vector<String>();
		Random r = new Random();
		while (mbr.ready()) {
			monoWord.add(mbr.readLine().split(" ")[0]);
		}
		while (bbr.ready()) {
			biWord.add(bbr.readLine().split(" ")[0]);
		}
		while (tbr.ready()) {
			triWord.add(tbr.readLine().split(" ")[0]);
		}
		String output = "";
		for (int i = 0; i < model.length(); i++) {
			if (model.charAt(i) == '1') {
				output += monoWord.elementAt(r.nextInt(100));
			} else if (model.charAt(i) == '2') {
				output += biWord.elementAt(r.nextInt(100));
			} else if (model.charAt(i) == '3') {
				output += triWord.elementAt(r.nextInt(100));
			} else {
				output += model.charAt(i);
			}
		}
		return output;
	}
}
