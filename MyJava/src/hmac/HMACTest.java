package hmac;

import java.util.ArrayList;

public class HMACTest {

	public static void main(String[] args) {

	}

	private void hmac (int[] k, String message) {
		int[] key = new int[64];
	    if (k.length > 64) {
	        key = hash(k); // keys longer than block size are shortened
	    }
	    if (k.length < 64) {
	        // keys shorter than block size are zero-padded (where ∥ is concatenation)
	        for(int i=k.length;i<64;i++)key[i]=0; // Where * is repetition.
	    }
	    
	    o_key_pad = [0x5c * blocksize] ⊕ key; // Where block size is that of the underlying hash function
	    i_key_pad = [0x36 * blocksize] ⊕ key; // Where ⊕ is exclusive or (XOR)
	    
	    return hash(o_key_pad ∥ hash(i_key_pad ∥ message)); // Where ∥ is concatenation
	}

	private String sha1(String message) {
		long h0 = 0x67452301, h1 = 0xEFCDAB89, h2 = 0x98BADCFE, h3 = 0x10325476, h4 = 0xC3D2E1F0;
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

		for (int i = 0; i < block.length; i++) {
			int[][] word = new int[80][32];
			for (int j = 0; j < 16; j++) {
				for (int k = 0; k < 32; k++) {
					word[j][k] = Character.getNumericValue(block[i].charAt(32 * j + k));
				}
			}
			for (int j = 16; j < 80; j++) {
				int[] temp = new int[32];
				temp = leftShift(xor(word[j - 3], xor(word[j - 8], xor(word[j - 14], word[j - 16]))));
			}
			for (int i = 0; i < 80; i++) {
				
			}
		}
	

		return hashresult;
	}

	private int[] xor(int[] a, int[] b) throws ArrayIndexOutOfBoundsException {
		ArrayIndexOutOfBoundsException e = new ArrayIndexOutOfBoundsException();
		if (a.length != b.length)
			throw e;
		int[] tmp = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			tmp[i] = a[i] ^ b[i];
		}
		return tmp;

	}

	private int[] leftShift(int[] word) {
		int[] tmp = new int[word.length];
		for (int i = 0; i < word.length; i++) {
			tmp[i] = word[(i + 1) % word.length];
		}
		return tmp;
	}

}