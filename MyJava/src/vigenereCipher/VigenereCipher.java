package vigenereCipher;

import java.util.Vector;

public class VigenereCipher {
	static String msg = "ILEARNEDHOWTOCALCULATETHEAMOUNTOFPAPERNEEDEDFORAROOMWHENIWASATSCHOOLYOUMULTIPLYTHESQUAREFOOTAGEOFTHEWALLSBYTHECUBICCONTENTSOFTHEFLOORANDCEILINGCOMBINEDANDDOUBLEITYOUTHENALLOWHALFTHETOTALFOROPENINGSSUCHASWINDOWSANDDOORSTHENYOUALLOWTHEOTHERHALFFORMATCHINGTHEPATTERNTHENYOUDOUBLETHEWHOLETHINGAGAINTOGIVEAMARGINOFERRORANDTHENYOUORDERTHEPAPER";
	static String key = "XIBKGL";
	static String cipher = "KCCPKBGUFDPHQTYAVINRRTMVGRKDNBVFDETDGILTXRGUDDKOTFMBPVGEGLTGCKQRACQCWDNAWCRXIZAKFTLEWRPTYCQKYVXCHKFTPONCQQRHJVAJUWETMCMSPKQDYHJVDAHCTRLSVSKCGCZQQDZXGSFRLSWCWSJTBHAFSIASPRJAHKJRJUMVGKMITZHFPDISPZLVLGWTFPLKKEBDPGCEBSHCTJRWXBAFSPEZQNRWXCVYCGAONWDDKACKAWBBIKFTIOVKCGGHJVLNHIFFSQESVYCLACNVRWBBIREPBBVFEXOSCDYGZWPFDTKFQIYCWHJVLNHIQIBTKHJVNPIST";
	static char[] alph = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	static Vector<Character> alphabet = new Vector<Character>();

	public static void main(String[] args) {
		for (int i = 0; i < 26; i++) {
			alphabet.add(alph[i]);
		}
		for (int i = 0; i < msg.length(); i++) {
			int a = alphabet.indexOf(msg.charAt(i));
			int b = alphabet.indexOf(key.charAt(i%key.length()));
			System.out.print(alphabet.get((a-b+25)%26));
		}
		System.out.println("");
		for (int i = 0; i < cipher.length(); i++) {
			int a = alphabet.indexOf(cipher.charAt(i));
			int b = alphabet.indexOf(key.charAt(i%key.length()));
			System.out.print(alphabet.get((a+b+27)%26));
		}
	}

}
