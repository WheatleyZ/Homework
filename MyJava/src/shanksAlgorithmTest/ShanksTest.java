package shanksAlgorithmTest;

import java.math.*;

public class ShanksTest {

	public static void main(String[] args) {
		System.out.println(shankes(809, 3, 525));
	}

	private static long shankes(long n, long alpha, long beta) throws ArrayIndexOutOfBoundsException {
		long m = (long) Math.sqrt(n) + 1;
		long[][] amj = new long[(int) m][2];
		long[][] bai = new long[(int) m][2];
		for (int i = 0; i <= m - 1; i++) {
			amj[i][0] = i;
			bai[i][0] = i;
			amj[i][1] = GetRemainder(alpha, m * i, n);
			long bi = (long) Math.pow(alpha, i);
			long revi = ExtendedEuclid(bi, n);
			bai[i][1] = (revi * beta) % n;
			if (bai[i][1] < 0)
				bai[i][1] += n;
		}
		for (int i = (int) (m - 1); i > 0; i--) {
			for (int j = 0; j < i - 1; j++) {
				if (amj[j][1] > amj[j + 1][1]) {
					long[] tmp = amj[j + 1];
					amj[j + 1] = amj[j];
					amj[j] = tmp;
				}
				if (bai[j][1] > bai[j + 1][1]) {
					long[] tmp = bai[j + 1];
					bai[j + 1] = bai[j];
					bai[j] = tmp;
				}
			}
		}
		int i = 0, j = 0;
		while (bai[i][1] != amj[j][1]) {
			if (bai[i][1] > amj[j][1])
				j++;
			else if (bai[i][1] < amj[j][1])
				i++;
			if (i == bai.length || j == amj.length) {
				throw new ArrayIndexOutOfBoundsException();
			}
		}
		return (m * amj[j][0] + bai[i][0]) % n;
	}

	public static long GetRemainder(long baseNum, long power, long modelNum) {
		// 判断各个值正确性
		long tempNum = 1;
		long remainder = 0;
		if (power == 0)
			remainder = 1;
		while (power >= 1) {
			// 当为1的时候，得出结果
			if (power == 1) {
				remainder = (tempNum * baseNum) % modelNum;
				break;
			} else {
				// 如果指数是偶数，将基数平方，指数除以2
				if ((power & 1) == 0) {
					baseNum = (baseNum * baseNum) % modelNum;
					power >>= 1;
				}
				// 如果是奇数，将tempNum乘以基数 并取模，指数减1
				else {
					tempNum = (tempNum * baseNum) % modelNum;
					power -= 1;
				}
			}
			// System.out.println(baseNum + power + modelNum + tempNum);
		}
		return remainder;
	}

	private static long ExtendedEuclid(long a, long m) {
		long i = 0, j, r1, r2, q, s1, s2, t1, t2, s, t, a1;
		r1 = a;
		r2 = m;
		s1 = 1;
		s2 = 0;
		t1 = 0;
		t2 = 1;
		while (r2 != 0) {
			q = r1 / r2;
			j = s2;
			s2 = s1 - q * s2;
			s1 = j;
			j = t2;
			t2 = t1 - q * t2;
			t1 = j;
			j = r2;
			r2 = r1 - q * r2;
			r1 = j;
		}
		s = s1;
		t = t1;
		a1 = s % m;
		a1 = a1 % m;
		if (a1 < 0)
			a1 = a1 + m;
		return a1;
	}
}
