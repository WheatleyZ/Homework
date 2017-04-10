package hmac;

import java.util.ArrayList;

public class HMACTest {

	public static void main(String[] args) {
		String str = "111111";
		sha1(str.getBytes());
	}

	// private void hmac (int[] k, String message) {
	// int[] key = new int[64];
	// if (k.length > 64) {
	// key = hash(k); // keys longer than block size are shortened
	// }
	// if (k.length < 64) {
	// // keys shorter than block size are zero-padded (where ∥ is
	// concatenation)
	// for(int i=k.length;i<64;i++)key[i]=0; // Where * is repetition.
	// }
	//
	// o_key_pad = [0x5c * blocksize] ⊕ key; // Where block size is that of the
	// underlying hash function
	// i_key_pad = [0x36 * blocksize] ⊕ key; // Where ⊕ is exclusive or (XOR)
	//
	// return hash(o_key_pad ∥ hash(i_key_pad ∥ message)); // Where ∥ is
	// concatenation
	// }

	private static String sha1(byte[] bs) {
		String message = "";
		for (byte a : bs) {
			message += Integer.toBinaryString(a);
		}
		String msg = message + '1';
		while (msg.length() % 512 < 448) {
			msg += '0';
		}
		msg += String.format("%64s", Integer.toBinaryString(message.length())).replaceAll(" ", "0");
		String[] block = new String[msg.length() / 512];
		for (int i = 0; i < block.length; i++) {
			String tmp = "";
			for (int j = 0; j < 512; j++) {
				tmp += msg.charAt(512 * i + j);
			}
			block[i] = tmp;
		}

		int[][] hashres = new int[5][32];
		hashres[0] = iToA_32(0x67452301);
		hashres[1] = iToA_32(0xEFCDAB89);
		hashres[2] = iToA_32(0x98BADCFE);
		hashres[3] = iToA_32(0x10325476);
		hashres[4] = iToA_32(0xC3D2E1F0);

		for (int i = 0; i < block.length; i++) {
			int[][] hash = new int[5][32];
			int[][] word = new int[80][32];
			for (int j = 0; j < 16; j++) {
				for (int k = 0; k < 32; k++) {
					word[j][k] = Character.getNumericValue(block[i].charAt(32 * j + k));
				}
			}
			for (int j = 16; j < 80; j++) {
				word[j] = leftShift(xor(word[j - 3], xor(word[j - 8], xor(word[j - 14], word[j - 16]))));
			}
			for (int j = 0; j < 5; j++) {
				hash[j] = word[j];
			}
			for (int j = 0; j < 80; j++) {
				int[] f = new int[32];
				int[] k = new int[32];
				if (j < 20) {
					f = or(and(hash[1], hash[2]), and(not(hash[1]), hash[3]));
					k = iToA_32(0x5a827999);
				} else if (j < 40) {
					f = xor(hash[1], xor(hash[2], hash[3]));
					k = iToA_32(0x6ed9eba1);
				} else if (j < 60) {
					f = or(and(hash[1], hash[2]), or(and(hash[1], hash[3]), and(hash[2], hash[3])));
					k = iToA_32(0x8f1bbcdc);
				} else {
					f = xor(hash[1], xor(hash[2], hash[3]));
					k = iToA_32(0xca62c1d6);
				}
				for (int l = 0; l < 5; l++) {
					leftShift(hash[0]);
				}
				hash[0] = addM32(hash[0], addM32(f, addM32(hash[4], addM32(k, word[i]))));
				for (int l = 0; i < 30; i++) {
					leftShift(hash[1]);
				}
				leftShift(hash);
			}
			for (int j = 0; j < 5; j++) {
				hashres[j] = addM32(hashres[j], hash[j]);
			}
		}

		int[] ans = new int[160];
		for (int j = 0; j < 160; j++) {
			ans[j] = hashres[j / 32][j % 32];
		}

		for (int i = 0; i < 40; i++) {
			int k = ans[i * 4];
			for (int j = 0; j < 4; j++) {
				k *= 2;
				k += ans[i * 4 + j];
			}
			System.out.printf("%h",k);
		}

		return null;
	}

	private static void leftShift(int[][] word) {
		int[][] tmp = new int[word.length][word[0].length];
		for (int i = 0; i < word.length; i++) {
			for (int j = 0; j < tmp[0].length; j++) {
				tmp[i][j] = word[(i + 1) % word.length][j];
			}
		}
	}

	private static int[] addM32(int[] a, int[] b) {
		int[] ans = new int[32];
		int addFlag = 0;
		for (int i = 31; i > 0; i--) {
			ans[i] = a[i] ^ b[i] ^ addFlag;
			addFlag = (a[i] & b[i]) | (addFlag & (a[i] ^ b[i]));
		}
		return ans;
	}

	private static int[] iToA_32(long Int) {
		int[] str = new int[32];
		String num = String.format("%32s", Long.toBinaryString(Int).replaceAll(" ", "0"));
		for (int i = 0; i < 32; i++) {
			str[i] = Character.getNumericValue(num.charAt(i));
		}
		return str;
	}

	private static int[] xor(int[] a, int[] b) throws ArrayIndexOutOfBoundsException {
		ArrayIndexOutOfBoundsException e = new ArrayIndexOutOfBoundsException();
		if (a.length != b.length)
			throw e;
		int[] tmp = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			tmp[i] = a[i] ^ b[i];
		}
		return tmp;

	}

	private static int[] not(int[] a) {
		int[] tmp = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			tmp[i] = ~a[i];
		}
		return tmp;

	}

	private static int[] and(int[] a, int[] b) throws ArrayIndexOutOfBoundsException {
		ArrayIndexOutOfBoundsException e = new ArrayIndexOutOfBoundsException();
		if (a.length != b.length)
			throw e;
		int[] tmp = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			tmp[i] = a[i] & b[i];
		}
		return tmp;

	}

	private static int[] or(int[] a, int[] b) throws ArrayIndexOutOfBoundsException {
		ArrayIndexOutOfBoundsException e = new ArrayIndexOutOfBoundsException();
		if (a.length != b.length)
			throw e;
		int[] tmp = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			tmp[i] = a[i] | b[i];
		}
		return tmp;

	}

	private static int[] leftShift(int[] word) {
		int[] tmp = new int[word.length];
		for (int i = 0; i < word.length; i++) {
			tmp[i] = word[(i + 1) % word.length];
		}
		return tmp;
	}

}