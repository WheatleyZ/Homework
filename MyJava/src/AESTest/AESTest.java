package AESTest;

import java.io.*;
import java.math.*;

public class AESTest {
	static int[] key = { 0x2B, 0x7E, 0x15, 0x16, 0x28, 0xAE, 0xD2, 0xA6, 0xAB, 0xF7, 0x15, 0x88, 0x09, 0xCF, 0x4F,
			0x3C };
	static int[] finalkey = new int[44];
	static String plaintext;
	static int[] sBox = { 0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30, 0x01, 0x67, 0x2B, 0xFE, 0xD7, 0xAB,
			0x76, 0xCA, 0x82, 0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C, 0xA4, 0x72, 0xC0, 0xB7,
			0xFD, 0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC, 0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15, 0x4, 0xC7, 0x23,
			0xC3, 0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75, 0x09, 0x83, 0x2C, 0x1A, 0x1B,
			0x6E, 0x5A, 0xA0, 0x52, 0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84, 0x53, 0xD1, 0x00, 0xED, 0x20, 0xFC, 0xB1,
			0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58, 0xCF, 0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45,
			0xF9, 0x02, 0x7F, 0x50, 0x3C, 0x9F, 0xA8, 0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D, 0x38, 0xF5, 0xBC, 0xB6, 0xDA,
			0x21, 0x10, 0xFF, 0xF3, 0xD2, 0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E, 0x3D, 0x64,
			0x5D, 0x19, 0x73, 0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A, 0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B,
			0xDB, 0xE0, 0x32, 0x3A, 0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62, 0x91, 0x95, 0xE4, 0x79, 0xE7,
			0xC8, 0x37, 0x6D, 0x8D, 0xD5, 0x4E, 0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08, 0xBA, 0x78, 0x25,
			0x2E, 0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B, 0xBD, 0x8B, 0x8A, 0x70, 0x3E, 0xB5, 0x66, 0x48,
			0x03, 0xF6, 0x0E, 0x61, 0x35, 0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E, 0xE1, 0xF8, 0x98, 0x11, 0x69, 0xD9, 0x8E,
			0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55, 0x28, 0xDF, 0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68, 0x41,
			0x99, 0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16 };
	static int[] rCon = { 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1B, 0x36 };

	public static void main(String[] args) {
		enCipher();
	}

	private static void rotWord(int[] w) {
		int temp = w[0];
		for (int i = 0; i < w.length - 1; i++) {
			w[i] = w[i + 1];
		}
		w[w.length - 1] = temp;
	}

	private static void subWord(int[] w) {
		for (int i = 0; i < w.length; i++) {
			int x = w[i] / 0x10;
			int y = w[i] % 0x10;
			w[i] = sBox[0x10 * x + y];
		}
	}

	private static void keyExp(int[] key) {
		for (int i = 0; i < 4; i++) {
			finalkey[i] = (((key[4 * i]) * 0x100 + key[4 * i + 1]) * 0x100 + key[4 * i + 2]) * 0x100 + key[4 * i + 3];
		}
		for (int i = 4; i < 44; i++) {
			long a = finalkey[i - 1];
			while (a < 0)
				a += 0x100000000l;
			int[] temp = { (int) ((a / 0x1000000) % 0x100), (int) (((a % 0x1000000 - a % 0x1000) / 0x10000) % 0x100),
					(int) (((a % 0x10000 - a % 0x100) / 0x100) % 0x100), (int) (a % 0x100) };
			if (i % 4 == 0) {
				rotWord(temp);
				subWord(temp);
				temp[0] ^= rCon[i / 4 - 1];
			}
			finalkey[i] = (((temp[0]) * 0x100 + temp[1]) * 0x100 + temp[2]) * 0x100 + temp[3];
			finalkey[i] ^= finalkey[i - 4];
		}
	}

	static void enCipher() {
		keyExp(key);
		int[][] state = { { 0x32, 0x43, 0xf6, 0xa8 }, { 0x88, 0x5a, 0x30, 0x8d }, { 0x31, 0x31, 0x98, 0xa2 },
				{ 0xe0, 0x37, 0x07, 0x34 } };
		addRoundKey(state, 0);
		for (int i = 1; i <= 9; i++) {
			subBytes(state);
			shiftRows(state);
			mixColumns(state);
			addRoundKey(state, i);
		}
		subBytes(state);
		shiftRows(state);
		addRoundKey(state, 10);
		System.out.println("密钥：");
		for(int i:finalkey){			
			System.out.print(String.format("%2s", Integer.toHexString(i)).replaceAll(" ", "0"));
		}
		System.out.println("\n密文：");
		for(int[] i:state){
			for(int j:i){
				System.out.print(String.format("%2s", Integer.toHexString(j)).replaceAll(" ", "0"));
			}
			System.out.print("\n");
		}
	}

	private static void subBytes(int[][] state) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int x = state[i][j] / 0x10;
				int y = state[i][j] % 0x10;
				state[i][j] = sBox[0x10 * x + y];
			}
		}
	}

	private static void shiftRows(int[][] state) {
		for (int i = 0; i < 4; i++) {
			for (int j = i; j > 0; j--) {
				shiftOnce(state, i);
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

	private static void mixColumns(int[][] state) {
		for (int[] column : state) {
			int[] t = new int[4];
			for (int i = 0; i < column.length; i++) {
				t[i] = column[i];
			}
			int[] u = new int[4];
			u[0] = fieldMulti(2, t[0]) ^ fieldMulti(3, t[1]) ^ t[2] ^ t[3];
			u[1] = fieldMulti(2, t[1]) ^ fieldMulti(3, t[2]) ^ t[3] ^ t[0];
			u[2] = fieldMulti(2, t[2]) ^ fieldMulti(3, t[3]) ^ t[0] ^ t[1];
			u[3] = fieldMulti(2, t[3]) ^ fieldMulti(3, t[0]) ^ t[1] ^ t[2];
			for (int i = 0; i < 4; i++) {
				column[i] = u[i];
			}
		}
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

	private static void addRoundKey(int[][] state, int round) {
		int[][] roundKey = new int[4][4];
		for (int i = round * 4; i < round * 4 + 4; i++) {
			long a = finalkey[i];
			while (a < 0)
				a += 0x100000000l;
			int[] temp = { (int) ((a / 0x1000000) % 0x100), (int) (((a % 0x1000000 - a % 0x1000) / 0x10000) % 0x100),
					(int) (((a % 0x10000 - a % 0x100) / 0x100) % 0x100), (int) (a % 0x100) };
			for (int j = 0; j < 4; j++) {
				roundKey[i % 4][j] = temp[j];
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				state[i][j] ^= roundKey[i][j];
			}
		}
	}

}
