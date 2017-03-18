package specialSwapCipher;

import java.util.Vector;

public class analysis {
	static String c = "MYAMRARUYIQTENCTORAHROYWDSOYEOUARRGDERNOGW";

	public static void main(String[] args) {
		for (int i = 1; i < c.length(); i++) {
			if (c.length() % i == 0) {
				System.out.println("m=" + i);
				for (int j = 0; j < i; j++) {
					Vector<Character> v = new Vector<Character>();
					for (int k = j; k < c.length(); k += i) {
						v.add(c.charAt(k));
					}
					System.out.println(v.toString());
				}
			}
		}
	}
}
