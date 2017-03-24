<<<<<<< HEAD
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
=======
package testing;

public class testing {
	public static void main(String[] args) {
		int[][] state = { { 11, 12, 13, 14 }, { 21, 22, 23, 24 }, { 31, 32, 33, 34 }, { 41, 42, 43, 44 } };
		shiftRows(state);
		for (int[] x : state) {
			for (int y : x) {
				System.out.println(y);
			}
		}
	}

	private static void shiftRows(int[][] state) {
		for(int i=0;i<4;i++){
			for(int j=i;j>0;j--){
				shiftOnce(state,i);
			}
		}
	}

	static void shiftOnce(int[][] state, int row) {
		int temp = state[0][row];
		for (int i = 0; i < 3; i++) {
			state[i][row] = state[i + 1][row];
		}
		state[3][row] = temp;
	}
>>>>>>> branch 'master' of https://github.com/WheatleyZ/Homework.git
}