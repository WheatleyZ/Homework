package MyClass;

import java.awt.*;

public class FileCopyDemo {
	Frame f = new Frame("文件复制Demo");
	Panel p = new Panel();
	TextField t = new TextField();
	Button open = new Button("打开");
	Button copy = new Button("复制");

	public static void main(String[] args) {
		
	}
	public void init(){
		f.add(p);
		p.add(t);
		p.add(open);
		p.add(copy);
	}

}
