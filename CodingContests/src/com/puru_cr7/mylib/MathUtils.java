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

	public static int gcd(final int a, final int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	// pascals triangle wrt to mod
	public static long[][] generatePascalsTriangle(final int n, final int mod) {
		final long[][] ps = new long[n + 1][];
		for (int i = 1; i <= n; i++) {
			ps[i] = new long[i + 1];
			ps[i][0] = 1l;
			ps[i][i] = 1l;
			for (int j = 1; j < i; j++) {
				ps[i][j] = (ps[i - 1][j - 1] + ps[i - 1][j]) % mod;
			}
		}
		return ps;
	}

	/**
	 * computing a^x mod p using fermat's little theorem
	 * fermats little theorem : a^(p-1)=1 mod p
	 * So a^x=a^(x (mod p-1)) mod p
	 */
	public static int power(final int a, final int x, final int p) {
		return (int) Math.pow(a, x % (p - 1)) % p;
	}

	/**
	 * Computes a^(-1) mod p
	 * According to FL theorem, a^(p-1)= 1 mod p
	 * So, a^-1 mod p= a^m-2 mod p
	 */
	public static int modInv(final int a, final int p) {
		return power(a, p - 2, p);
	}

}
