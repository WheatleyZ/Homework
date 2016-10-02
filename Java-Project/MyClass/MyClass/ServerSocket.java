package MyClass;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class ServerSocket {
	TextField said;
	TextArea disp;
	public static void main(String[] args) {
		new ServerSocket().initial();
	}
	public void initial(){
		Frame mainf = new Frame("客户端");
		Panel head = new Panel();
		Panel foot = new Panel();
		Label pt = new Label("Port:");
		Label s = new Label("Say:");
		TextField port = new TextField(30);
		said = new TextField(30);
		disp = new TextArea();
		Button portConf = new Button("确定");
		Button conf = new Button("确定");
		Confirm c = new Confirm(this);
		
		mainf.setSize(500, 500);
		mainf.add(head, "North");
		mainf.add(disp, "Center");
		mainf.add(foot, "South");
		head.add(pt);
		head.add(port);
		head.add(portConf);
		foot.add(s);
		foot.add(said);
		foot.add(conf);
		conf.addActionListener(c);
		disp.setFont(new Font(null, 0, 20));
		mainf.show();
	}
}

class Confirm implements ActionListener{
	ServerSocket demo;
	@Override
	public void actionPerformed(ActionEvent e) {
		demo.disp.append(new java.util.Date().toString()+" Said:\n"+demo.said.getText()+"\n");
		demo.said.setText(null);
	}
	public Confirm(ServerSocket demo) {
		this.demo = demo;
	}
}