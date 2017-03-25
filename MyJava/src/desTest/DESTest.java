package desTest;

import java.math.BigInteger;
import java.lang.Exception;

public class DESTest {
	static int[] pc1 = { 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60,
			52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28,
			20, 12, 4 };
	static int[] pc2 = { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52,
			31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32 };
	static int[] plainText = new int[64];
	static String key = "0123456789abcdab";
	static int[] keySrc;
	static int[] finalKey = new int[16];

	public static void main(String[] args) {
		try {
			keyGenenate(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void keyGenenate(String key) throws Exception {
		// if (key.length() != 64)
		// throw new Exception();
		keySrc = new int[56];
		String temp = "";
		for (int i = 0; i < 16; i++) {
			temp += String
					.format("%4s", Integer.toBinaryString(Integer.parseInt(Character.toString(key.charAt(i)), 16)))
					.replace(" ", "0");
		}
		key = temp;
		// key = Integer.toBinaryString(Integer.parseInt(key));
		for (int i = 0; i < 56; i++) {
			keySrc[i] = Integer.parseInt(Character.toString(key.charAt(pc1[i] - 1)));
		}
		generate(keySrc);
		System.out.println("finished");
	}

	private static void generate(int[] keySrc) {
		for (int i = 0; i < 16; i++) {
			leftShift(keySrc);
			int temp = keySrc[pc2[0]];
			for (int j = 1; j < 48; j++) {
				temp *= 2;
				temp += keySrc[pc2[j] - 1];
			}
			finalKey[i] = temp;
		}
	}

	private static void leftShift(int[] keySrc) {
		for (int i = 0; i < 2; i++) {
			int temp = keySrc[28 * i];
			for (int j = 0; j < 27; j++) {
				keySrc[28 * i + j] = keySrc[28 * i + j + 1];
			}
			keySrc[28 * i + 27] = temp;
		}
	}

	static void enCipher() {

	}

	static void deCipher() {

	}

}
