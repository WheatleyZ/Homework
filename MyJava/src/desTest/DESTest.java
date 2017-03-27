package desTest;

import java.lang.Exception;

public class DESTest {
	static int[] ip = { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64,
			56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29,
			21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 };
	static int[] fp = { 40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37,
			5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2, 42, 10, 50,
			18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25 };
	static int[] e = { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18,
			19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1 };
	static int[] pc1 = { 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60,
			52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28,
			20, 12, 4 };
	static int[] pc2 = { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52,
			31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32 };
	static int[][] sBox = {
			{ 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7, 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3,
					8, 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0, 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10,
					0, 6, 13 },
			{ 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10, 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11,
					5, 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15, 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0,
					5, 14, 9 },
			{ 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8, 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15,
					1, 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7, 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11,
					5, 2, 12 },
			{ 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15, 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14,
					9, 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4, 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12,
					7, 2, 14 },
			{ 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9, 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8,
					6, 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14, 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9,
					10, 4, 5, 3 },
			{ 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11, 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3,
					8, 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6, 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6,
					0, 8, 13 },
			{ 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1, 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8,
					6, 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2, 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14,
					2, 3, 12 },
			{ 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7, 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9,
					2, 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8, 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3,
					5, 6, 11 } };
	static int[] p = { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13,
			30, 6, 22, 11, 4, 25 };
	static int[][] plainText = new int[2][32];
	static int[][] cipherText = new int[2][32];
	static int[] keySrc;
	static long[] finalKey = new long[16];

	public static void main(String[] args) {
		String msg = "8f03456d3f78e2c5";
		String key = "3b3898371520f75e";
		System.out.println("message:" + msg);
		System.out.println("key:" + key);
		System.out.println("cipher:" + enCipher(msg, key));
		System.out.println("after decipher:" + deCipher(enCipher(msg, key), key));
	}

	static String enCipher(String msg, String key) {
		for (int i = 0; i < 16; i++) {
			String msgi = String.format("%4s", Integer.toBinaryString(Character.getNumericValue(msg.charAt(i))))
					.replaceAll(" ", "0");
			for (int j = 0; j < 4; j++) {
				plainText[(i * 4 + j) / 32][(i * 4 + j) % 32] = Character.getNumericValue(msgi.charAt(j));
			}
		}
		try {
			keyGenenate(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int[][] pInput = new int[2][32];
		for (int i = 0; i < 64; i++) {
			pInput[i / 32][i % 32] = plainText[(ip[i] - 1) / 32][(ip[i] - 1) % 32];
		}
		for (int i = 0; i < 16; i++) {
			int[] temp = fFunc(pInput[1], finalKey[i]);
			for (int j = 0; j < 32; j++) {
				pInput[0][j] ^= temp[j];
			}
			if (i == 15)
				break;
			swap(pInput);
		}
		for (int i = 0; i < 64; i++) {
			cipherText[i / 32][i % 32] = pInput[(fp[i] - 1) / 32][(fp[i] - 1) % 32];
		}
		String cipherString = "";
		for (int i[] : cipherText) {
			for (int j : i) {
				cipherString += j;
			}
		}
		long ans = Character.getNumericValue(cipherString.charAt(0));
		for (int i = 1; i < 64; i++) {
			ans *= 2;
			ans += Character.getNumericValue(cipherString.charAt(i));
		}
		return Long.toHexString(ans);
	}

	static String deCipher(String msg, String key) {
		for (int i = 0; i < 16; i++) {
			String msgi = String.format("%4s", Integer.toBinaryString(Character.getNumericValue(msg.charAt(i))))
					.replaceAll(" ", "0");
			for (int j = 0; j < 4; j++) {
				plainText[(i * 4 + j) / 32][(i * 4 + j) % 32] = Character.getNumericValue(msgi.charAt(j));
			}
		}
		try {
			keyGenenate(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int[][] pInput = new int[2][32];
		for (int i = 0; i < 64; i++) {
			pInput[i / 32][i % 32] = plainText[(ip[i] - 1) / 32][(ip[i] - 1) % 32];
		}
		for (int i = 0; i < 16; i++) {
			int[] temp = fFunc(pInput[1], finalKey[15 - i]);
			for (int j = 0; j < 32; j++) {
				pInput[0][j] ^= temp[j];
			}
			if (i == 15)
				break;
			swap(pInput);
		}
		for (int i = 0; i < 64; i++) {
			cipherText[i / 32][i % 32] = pInput[(fp[i] - 1) / 32][(fp[i] - 1) % 32];
		}
		String cipherString = "";
		for (int i[] : cipherText) {
			for (int j : i) {
				cipherString += j;
			}
		}
		long ans = Character.getNumericValue(cipherString.charAt(0));
		for (int i = 1; i < 64; i++) {
			ans *= 2;
			ans += Character.getNumericValue(cipherString.charAt(i));
		}
		return Long.toHexString(ans);
	}

	private static int[] fFunc(int[] r, long finalKey2) {
		int[] k = new int[48];
		int[] result = new int[32];
		int[] expandR = new int[48];
		int[] aftS = new int[8];
		int[] preP = new int[32];
		int sX = 0;
		int sY = 0;
		String tmpKey = String.format("%48s", Long.toBinaryString(finalKey2)).replaceAll(" ", "0");
		for (int i = 0; i < 48; i++) {
			k[i] = Character.getNumericValue(tmpKey.charAt(i));
		}
		for (int i = 0; i < 48; i++) {
			expandR[i] = r[e[i] - 1];
			expandR[i] ^= k[i];
		}
		for (int i = 0; i < 8; i++) {
			sX = 2 * expandR[i * 6] + expandR[i * 6 + 5];
			sY = 8 * expandR[i * 6 + 1] + 4 * expandR[i * 6 + 2] + 2 * expandR[i * 6 + 3] + expandR[i * 6 + 4];
			aftS[i] = sBox[i][16 * sX + sY];
		}
		for (int i = 0; i < 8; i++) {
			String tmp = String.format("%4s", Integer.toBinaryString(aftS[i])).replaceAll(" ", "0");
			for (int j = i * 4; j < i * 4 + 4; j++) {
				preP[j] = Character.getNumericValue(tmp.charAt(j % 4));
			}
		}
		for (int i = 0; i < 32; i++) {
			result[i] = preP[p[i] - 1];
		}
		return result;
	}

	private static void swap(int[][] pInput) {
		for (int i = 0; i < 32; i++) {
			int temp = pInput[0][i];
			pInput[0][i] = pInput[1][i];
			pInput[1][i] = temp;
		}
	}

	private static void keyGenenate(String key) throws Exception {
		if (key.length() != 16)
			throw new Exception();
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
		// System.out.println("finished");
	}

	private static void generate(int[] keySrc) {
		for (int i = 0; i < 16; i++) {
			leftShift(keySrc);
			if (i != 0 && i != 1 && i != 8 && i != 15)
				leftShift(keySrc);
			long temp = keySrc[pc2[0] - 1];
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

}
