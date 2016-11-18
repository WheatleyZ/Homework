package MyClass.Point;

public class Point3D extends Point2D {
	int x, y, z;

	public void Point3D(Point2D p, int c) {
		this.x = p.x;
		this.y = p.y;
		this.z = c;
	}

	public void Point3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void offset(int a, int b, int c) {
		this.x += a;
		this.y += b;
		this.z += c;
	}

	public static void main(String[] args) {
		Point3D p3d1 = new Point3D();
	}

	public void print() {
		System.out.println("x=" + x + "\ny=" + y + "\nz=" + z);
	}
}