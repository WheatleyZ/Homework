package testing;

public class testing {
	public static void main(String[] args) {
		System.out.println(Integer.toHexString(fieldMulti(0x57, 0x13)));
	}

	private static int fieldMulti(int a, int b) {
		int answer = 0;
		int[] aMulti = new int[8];
		aMulti[0] = a;
		for (int i = 1; i < 8; i++) {
			aMulti[i] = aMulti[i - 1] * 2 % 0x100;
			if (aMulti[i - 1] > 0x7f)
				aMulti[i] ^= 0x1b;
		}
		String bBinary = Integer.toBinaryString(b);
		for (int i = bBinary.length() - 1; i >= 0; i--) {
			if (bBinary.charAt(i) == '1') {
				answer ^= aMulti[bBinary.length() - i - 1];
			}
		}
		return answer;
	}
}