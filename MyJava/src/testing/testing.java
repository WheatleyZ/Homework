package testing;

public class testing {
	public static void main(String[] args) {
		long a = -619991808;
		while (a < 0)
			a += 0x100000000l;
		int[] temp = { (int) ((a / 0x1000000) % 0x100), (int) (((a % 0x1000000 - a % 0x1000) / 0x10000) % 0x100),
				(int) (((a % 0x10000 - a % 0x100) / 0x100) % 0x100), (int) (a % 0x100) };
		for (int i : temp) {
			System.out.println(Integer.toHexString(i));
		}
	}
}