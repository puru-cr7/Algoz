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

	public static boolean isPrime(final int no) {
		for (int i = 2; i * i <= no; i++) {
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
	 * Computes a^x mod p using fermat's little theorem
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

	/**
	 * Euler’s Totient function ?(n) for an input n is count of numbers
	 * in {1, 2, 3, …, n} that are relatively prime to n,
	 * i.e., the numbers whose GCD (Greatest Common Divisor) with n is 1.
	 * The Eulers formulas states that The formula basically says that the value of ?(n)
	 * is equal to n multiplied by product of (1 – 1/p) for all prime factors p of n.
	 * For example value of ?(6) = 6 * (1-1/2) * (1 – 1/3) = 2.
	 */
	public static long getEulerTotient(int n) {
		int tot = n;
		for (int p = 2; p * p <= n; p++) {
			// x*(1-1/p)=[x*(p-1)/p]=(x/p)*(p-1)
			if (n % p == 0) {
				tot /= p;
				tot *= p - 1;
				while (n % p == 0) {
					n /= p;
				}
			}
		}
		if (n > 1) {
			tot /= n;
			tot *= n - 1;
		}
		return tot;
	}

	// TDOD:Sieve
}
