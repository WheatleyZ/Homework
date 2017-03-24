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
}