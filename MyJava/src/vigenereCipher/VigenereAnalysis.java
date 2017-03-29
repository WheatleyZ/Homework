package vigenereCipher;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Vector;

public class VigenereAnalysis {
	static String c = "KCCPKBGUFDPHQTYAVINRRTMVGRKDNBVFDETDGILTXRGUDDKOTFMBPVGEGLTGCKQRACQCWDNAWCRXIZAKFTLEWRPTYCQKYVXCHKFTPONCQQRHJVAJUWETMCMSPKQDYHJVDAHCTRLSVSKCGCZQQDZXGSFRLSWCWSJTBHAFSIASPRJAHKJRJUMVGKMITZHFPDISPZLVLGWTFPLKKEBDPGCEBSHCTJRWXBAFSPEZQNRWXCVYCGAONWDDKACKAWBBIKFTIOVKCGGHJVLNHIFFSQESVYCLACNVRWBBIREPBBVFEXOSCDYGZWPFDTKFQIYCWHJVLNHIQIBTKHJVNPIST";
	static char[] alph = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	static double[] pI = { 0.082, 0.015, 0.028, 0.043, 0.127, 0.022, 0.020, 0.061, 0.070, 0.002, 0.008, 0.040, 0.024,
			0.067, 0.075, 0.019, 0.001, 0.060, 0.063, 0.091, 0.028, 0.010, 0.023, 0.001, 0.020, 0.001 };

	public static void main(String[] args) {
		findKeySize();
		findKeys(6);
		int[] keys = { 24, 9, 2, 11, 7, 12 };
		deCipher(keys);
	}

	public static void deCipher(int[] keyOffSet) {
		char[] plainText = new char[c.length()];
		for (int offSet = 0; offSet < keyOffSet.length; offSet++) {
			int current = offSet;
			while (current < c.length()) {
				int chNum = 0;
				for (int ch = 0; ch < 26; ch++) {
					if (c.charAt(current) == alph[ch])
						chNum = ch;
				}
				plainText[current] = alph[(chNum + keyOffSet[offSet]) % 26];
				current += keyOffSet.length;
			}
		}
		System.out.println(plainText);
	}

	public static void findKeys(int keySize) {
		for (int offSet = 0; offSet < keySize; offSet++) {
			Vector<Character> part = new Vector<>();
			int current = offSet;
			while (current < c.length()) {
				part.add(c.charAt(current));
				current += keySize;
			}
			// double[] mG = new double[26];
			for (int keyOffSet = 0; keyOffSet < 26; keyOffSet++) {
				double mg = 0;
				for (int ch = 0; ch < 26; ch++) {
					double total = 0;
					for (char currentChar : part) {
						if (currentChar == alph[ch])
							total++;
					}
					mg += (pI[(ch + keyOffSet) % 26] * total);
				}
				mg /= part.size();
				System.out.println(offSet + 1 + " " + alph[keyOffSet] + (keyOffSet + 1)
						+ " " + mg);
			}
		}
	}

	public static void findKeySize() {
		for (int keySize = 1; keySize < 10; keySize++) {
			System.out.println("key size :" + keySize);
			double ave = 0;
			for (int offSet = 0; offSet < keySize; offSet++) {
				Vector<Character> part = new Vector<>();
				int current = offSet;
				while (current < c.length()) {
					part.add(c.charAt(current));
					current += keySize;
				}
				double upperPart = 0;
				for (int ch = 0; ch < 26; ch++) {
					double isEqual = 0;
					for (int i = 0; i < part.size(); i++) {
						if (part.get(i) == alph[ch])
							isEqual++;
					}
					upperPart += isEqual * (isEqual - 1);
				}
				System.out.println(upperPart / (part.size() * (part.size() - 1)));
				ave += upperPart / (part.size() * (part.size() - 1));
			}
			ave /= keySize;
			System.out.println("avgPi(x)=" + ave + "\n");
		}
	}
}
