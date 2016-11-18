package MyClass.Point;

public class Point2D {
	public int x, y;

	public Point2D(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public void offset(int a, int b) {
		this.x += a;
		this.y += b;
	}

	public void print() {
		System.out.println("x=" + x + "\ny=" + y);
	}
}