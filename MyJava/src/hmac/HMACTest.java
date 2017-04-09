package hmac;

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
			
		}

		return hashresult;
	}

}