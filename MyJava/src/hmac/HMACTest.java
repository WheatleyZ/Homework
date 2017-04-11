package hmac;

import java.util.ArrayList;

public class HMACTest {

	public static void main(String[] args) {

	}

	private static String hmac(int[] k, String message) {
		byte[] s = message.getBytes();
		String msg = "";
		for (byte a : s) {
			msg += String.format("%8s", Integer.toBinaryString(a)).replaceAll(" ", "0");
		}
		if (k.length > 64) {
			k = sha1(k); // keys longer than blocksize are shortened
		}
		if (k.length < 64) {
			// keys shorter than blocksize are zero-padded (where ∥ is
			// concatenation)
			int[] tmp = new int[64];
			for (int a : tmp)
				a = 0;
			for (int i = 0; i < k.length; i++) {
				tmp[i] = k[i];
			}
			k = tmp;
		}

		 o_key_pad = [0x5c * blocksize] ⊕ key // Where blocksize is that of
		// the underlying hash function
		 i_key_pad = [0x36 * blocksize] ⊕ key // Where ⊕ is exclusive or (XOR)

		 return hash(o_key_pad ∥ hash(i_key_pad ∥ message)); // Where ∥ is
	}

	// private static

	private static int[] sha1(int[] m) {
		String message = "";
		for (int a : m) {
			message += a;
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
		hashres[0] = iToA_32(0x67452301l);
		hashres[1] = iToA_32(0xEFCDAB89l);
		hashres[2] = iToA_32(0x98BADCFEl);
		hashres[3] = iToA_32(0x10325476l);
		hashres[4] = iToA_32(0xC3D2E1F0l);

		for (int i = 0; i < block.length; i++) {
			int[][] hash = new int[5][32];
			for (int j = 0; j < 5; j++) {
				hash[j] = hashres[j];
			}
			int[][] word = new int[80][32];
			for (int j = 0; j < 16; j++) {
				for (int k = 0; k < 32; k++) {
					word[j][k] = Character.getNumericValue(block[i].charAt(32 * j + k));
				}
			}
			for (int j = 16; j < 80; j++) {
				word[j] = leftShift(xor(word[j - 3], xor(word[j - 8], xor(word[j - 14], word[j - 16]))));
			}
			for (int j = 0; j < 80; j++) {
				int[] f = new int[32];
				int[] k = new int[32];
				int[] a = hash[0];
				if (j < 20) {
					f = xor(hash[3], and(hash[1], xor(hash[2], hash[3])));
					k = iToA_32(0x5a827999l);
				} else if (j < 40) {
					f = xor(hash[1], xor(hash[2], hash[3]));
					k = iToA_32(0x6ed9eba1l);
				} else if (j < 60) {
					f = or(and(hash[1], hash[2]), and(hash[3], or(hash[1], hash[2])));
					k = iToA_32(0x8f1bbcdcl);
				} else if (j < 80) {
					f = xor(hash[1], xor(hash[2], hash[3]));
					k = iToA_32(0xca62c1d6l);
				}
				for (int l = 0; l < 5; l++) {
					hash[0] = leftShift(hash[0]);
				}
				hash[4] = addM32(hash[0], addM32(f, addM32(hash[4], addM32(k, word[j]))));
				for (int l = 0; l < 30; l++) {
					hash[1] = leftShift(hash[1]);
				}
				hash[0] = a;
				hash = rightShift(hash);
			}
			for (int j = 0; j < 5; j++) {
				hashres[j] = addM32(hashres[j], hash[j]);
			}
		}

		int[] ans = new int[160];
		for (int j = 0; j < 160; j++) {
			ans[j] = hashres[j / 32][j % 32];
		}
		return ans;
	}

	private static int[] leftShift(int[] word) {
		int[] tmp = new int[word.length];
		for (int i = 0; i < word.length; i++) {
			tmp[i] = word[(i + 1) % word.length];
		}
		return tmp;
	}

	private static int[][] rightShift(int[][] word) {
		int[][] tmp = new int[word.length][word[0].length];
		for (int i = 0; i < word.length; i++) {
			for (int j = 0; j < tmp[0].length; j++) {
				tmp[(i + 1) % word.length][j] = word[i][j];
			}
		}
		return tmp;
	}

	private static int[] addM32(int[] a, int[] b) {
		int[] ans = new int[32];
		int addFlag = 0;
		for (int i = 31; i >= 0; i--) {
			ans[i] = a[i] ^ b[i] ^ addFlag;
			addFlag = (a[i] & b[i]) | (addFlag & (a[i] ^ b[i]));
		}
		return ans;
	}

	private static int[] iToA_32(long Int) {
		int[] str = new int[32];
		String num = String.format("%64s", Long.toBinaryString(Int)).replaceAll(" ", "0");
		for (int i = 0; i < 32; i++) {
			str[i] = Character.getNumericValue(num.charAt(i + 32));
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
			tmp[i] = ~a[i] + 2;
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

}