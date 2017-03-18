package MyClass;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameDemo {
	public static void main(String[] args) {
		MyListener listener1 = new MyListener();
		Frame f = new Frame("เหฃก");
		f.setSize(500, 300);
		f.show(true);// =f.setVisable(true);
		// Button button=new Button("name");
		// f.add(button);
		Panel p = new Panel();
		f.add(p, "North");
		for (int i = 0; i < 20; i++) {
			p.add(new Button("Space" + i));
		}

		String[] location = { "South", "West", "East", "Center" };
		for (int i = 0; i < 10; i++) {
			Button bt = new Button("Student" + i);
			f.add(bt, location[i % 4]);
			bt.addActionListener(listener1);
		}
		
		
	}
}

class MyListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand()+"clicked");
	}
	
}