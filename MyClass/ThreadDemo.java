import java.util.Date;

public class ThreadDemo {

	public static void main(String[] args) throws Exception {
		Thread t1 = new MyThread("hello");
		Thread t2 = new MyThread("goodbye");
		Thread t3 = new Thread(new MyThread2(),"one more thing");
		t1.start();
		t1.sleep(1000);
		t2.start();
		t2.sleep(1000);
		t3.start();
		
		
	}

}

class MyThread extends Thread {
	public MyThread(String name) {
		super(name);
	}
	@Override
	public void run() {
		while (true) {
			Date d = new Date();
			System.out.println(getName()+","+d.toLocaleString());
			try {
				sleep(3000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}

class MyThread2 implements Runnable {
	
	@Override
	public void run() {
		while (true) {
			Date d = new Date();
			Thread current = Thread.currentThread();
			System.out.println(current.getName()+","+d.toLocaleString());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}