package com.puru_cr7.mylib;

/**
 * This class is a library for all math related methods with best possible complexities.
 *
 * @author Purnendu Rath (puru_cr7).
 *         Created Jun 19, 2018.
 */
public class MathUtils {

	public static boolean isOdd(final int n) {
		return (n & 1) != 0;
	}

	public static boolean isPrime(final long no) {
		for (long i = 2; i * i <= no; i++) {
			if (no % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static long[][] generatePascalsTriangle(final int n, final int mod) {
		final long[][] ps = new long[n + 1][];
		for (int i = 1; i <= n; i++) {
			ps[i] = new long[i + 1];
			ps[i][0] = 1l;
			ps[i][i] = 1l;
			for (int j = 1; j < i; j++) {
				ps[i][j] = ps[i - 1][j - 1] + ps[i - 1][j];
			}
		}
		return ps;
	}
}
