package MyClass;

import java.awt.*;

public class FileCopyDemo {
	Frame f = new Frame("�ļ�����Demo");
	Panel p = new Panel();
	TextField t = new TextField();
	Button open = new Button("��");
	Button copy = new Button("����");

	public static void main(String[] args) {
		
	}
	public void init(){
		f.add(p);
		p.add(t);
		p.add(open);
		p.add(copy);
	}

}
