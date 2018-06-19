package com.puru_cr7.mylib;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * This class is depricated, although all the methods are fine,
 * but are not necessarily run at best possible complexity
 *
 * @author Purnendu Rath (puru_cr7)
 */
@Deprecated
public class MyLibrary {
	// calculates the max sub-array sum in an aaray.
	public static int maxSubArraySum(final int a[]) {
		final int size = a.length;
		int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;

		for (int i = 0; i < size; i++) {
			max_ending_here = max_ending_here + a[i];
			if (max_so_far < max_ending_here) {
				max_so_far = max_ending_here;
			}
			if (max_ending_here < 0) {
				max_ending_here = 0;
			}
		}
		return max_so_far;
	}

	// toggle character case in a String
	public static String toggleString(final String sentence) {
		final StringBuilder toggled = new StringBuilder(sentence.length());
		for (char letter : sentence.toCharArray()) {
			if (Character.isUpperCase(letter)) {
				letter = Character.toLowerCase(letter);
			}
			else if (Character.isLowerCase(letter)) {
				letter = Character.toUpperCase(letter);
			}

			toggled.append(letter);

		}
		return toggled.toString();
	}

	// right circular shift
	public static int rtCircShift(final int bits, final int k) {
		return bits >>> k | bits << Integer.SIZE - k;
	}

	// max consecutive 1s in a binary String represented by this integer
	public static int maxConsecutiveOnes(int x) {
		int count = 0;
		while (x != 0) {
			x = x & x << 1;
			count++;
		}
		return count;
	}

	// max consecutive 0s in a binary String represented by this integer
	public static int maxConsecutiveZeroes(Integer x) {
		String s = Integer.toBinaryString(x);
		s = s.replaceAll("0", "2")
				.replaceAll("1", "0")
				.replaceAll("2", "1");
		x = Integer.parseInt(s, 2);
		int count = 0;
		while (x != 0) {
			x = x & x << 1;
			count++;
		}
		return count;
	}

	// returns modInv of a long
	public static long modInv(final long x, final long m) {
		return BigInteger.valueOf(x)
				.modInverse(BigInteger.valueOf(m))
				.longValue();
	}

	// finds smallest factor of a given integer in sqrt(n) complexity
	public static long smallestFactor(final int C) {
		for (int i = 2; i * i <= C; i++) {
			if (C % i == 0) {
				return i;
			}
		}

		return C;
	}

	// checks if a string is a subsequence of another string
	public static boolean isSubSequence(final String main, final String sub) {
		int i = 0;
		int j = 0;
		while (i < main.length() && j < sub.length()) {
			if (main.charAt(i) == sub.charAt(j)) {
				i++;
				j++;
			}
			else {
				i++;
			}
		}
		return j == sub.length();
	}

	// checks if a number is prime in sqrt(n) complexity
	public static boolean isPrime(final long no) {
		for (long i = 2; i * i <= no; i++) {
			if (no % i == 0) {
				return false;
			}
		}
		return true;
	}

	// returns totient of a number
	public static long getTotient(long n) {
		long tot = n; // this will be the totient at the end of the sample
		for (long p = 2; p * p <= n; p++) {
			if (n % p == 0) {
				tot /= p;
				tot *= p - 1;
				while (n % p == 0) {
					n /= p;
				}
			}
		}
		if (n > 1) { // now n is the largest prime divisor
			tot /= n;
			tot *= n - 1;
		}
		return tot;
	}

	// returns odd days in a month with jan as 1
	public static int returnOddDays(final int m) {
		switch (m) {
			case 1:
				return 3;
			case 2:
				return 3;
			case 3:
				return 6;
			case 4:
				return 1;
			case 5:
				return 4;
			case 6:
				return 6;
			case 7:
				return 2;
			case 8:
				return 5;
			case 9:
				return 0;
			case 10:
				return 3;
			case 11:
				return 5;
			default:
				return 0;
		}
	}

	// sum of squares of numbers till n
	public static long sumOfSquares(final long n) {
		return n * (n + 1) * (2 * n + 1) / 6;
	}

	// square of sum of numbers till n
	public static long squareOfSum(final long n) {
		return (long) Math.pow(n * (n + 1) / 2, 2);
	}

	// lcm of two numbers
	public static long lcm(final long a, final long b) {
		return a * b / gcd(a, b);
	}

	// largest prime factor of a number in sqrt(n) complexity
	public static long maxPrimeFactor(long n) {
		long max = 1;
		while (n % 2 == 0) {
			max = 2;
			n = n / 2;
		}
		for (int i = 3; i <= Math.sqrt(n); i = i + 2) {
			while (n % i == 0) {
				max = Math.max(max, i);
				n = n / i;
			}
		}
		if (n > 2) {
			max = n;
		}
		return max;
	}

	// gcd of two numbers
	public static long gcd(final long a, final long b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	// prime numbers list using seive till limit
	public static List<Integer> generatePrimesBySeive(final int limit) {
		final int numPrimes = limit > 1
				? (int) (1.25506 * limit / Math.log(limit))
				: 0;
		final List<Integer> primes = new ArrayList<>(numPrimes);
		final boolean[] isComposite = new boolean[limit];
		final int sqrtLimit = (int) Math.sqrt(limit);
		for (int i = 2; i <= sqrtLimit; i++) {
			if (!isComposite[i]) {
				primes.add(i);
				for (int j = i * i; j < limit; j += i) {
					isComposite[j] = true;
				}
			}
		}
		for (int i = sqrtLimit + 1; i < limit; i++) {
			if (!isComposite[i]) {
				primes.add(i);
			}
		}
		return primes;
	}

	// same as above just that it returns an array rather than a list
	public static int[] sieveEratosthenes(final int limit) {
		if (limit < 33) {
			final int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
			for (int i = 0; i < 11; i++) {
				if (limit < primes[i]) {
					return Arrays.copyOf(primes, i);
				}
			}
			return primes;
		}
		final int u = limit + 32;
		final double lu = Math.log(u);
		final int[] ret = new int[(int) (u / lu + u / lu / lu * 1.5)];
		ret[0] = 2;
		int pos = 1;
		final int sup = (limit + 1 >> 6) + 1;
		final int[] isnp = new int[sup];
		final int[] tprimes = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
		int[] ptn;
		for (final int tp : tprimes) {
			ret[pos++] = tp;
			ptn = new int[tp];
			for (int i = tp - 3 >> 1; i < tp << 5; i += tp) {
				ptn[i >> 5] |= 1 << (i & 31);
			}
			for (int j = 0; j < sup; j += tp) {
				for (int i = 0; i < tp && i + j < sup; i++) {
					isnp[j + i] |= ptn[i];
				}
			}
		}
		final int[] magic = {0, 1, 23, 2, 29, 24, 19, 3, 30, 27, 25, 11, 20, 8,
				4, 13, 31, 22, 28, 18, 26, 10, 7, 12, 21, 17, 9, 6, 16, 5, 15,
				14};
		int p;
		final int h = limit >> 1;
		for (int i = 0; i < sup; i++) {
			for (int j = ~isnp[i]; j != 0; j &= j - 1) {
				if ((p = ((i << 5 | magic[(j & -j) * 0x076be629 >>> 27]) << 1)
						+ 3) > limit) {
					break;
				}
				ret[pos++] = p;
				if ((long) p * p > limit) {
					continue;
				}
				for (int q = p * p - 3 >> 1; q <= h; q += p) {
					isnp[q >> 5] |= 1 << q;
				}
			}
		}
		return Arrays.copyOf(ret, pos);
	}

	// to reverse any given string.
	public static String reverse(final String s) {
		return new StringBuilder(s).reverse()
				.toString();
	}

	// to test whether the given string is a palindrome.
	public static boolean isPalindrome(final String s) {
		return s.equals(reverse(s));
	}

	// to test whether the given integer is a palindrome.
	public static boolean isPalindrome(final int x) {
		return isPalindrome(Integer.toString(x));
	}

	// calculates ncr mod m
	public static long calcNcrModm(long n, long r, final int mod) {
		long ans;
		int a, b;
		ans = 1;
		while (n > 0) {
			a = (int) (n % mod);
			b = (int) (r % mod);
			if (b > a) {
				ans = 0;
				break;
			}
			long temp = 1;
			for (int j = 0; j < b; j++) {
				temp = temp * a % mod;
				a--;
			}
			BigInteger temp2 = BigInteger.ONE;
			for (int j = 1; j <= b; j++) {
				temp2 = temp2.multiply(BigInteger.valueOf(j)
						.modInverse(BigInteger.valueOf(mod)))
						.mod(BigInteger.valueOf(mod));
			}
			ans = ans * temp * temp2.intValue() % mod;
			n = n / mod;
			r = r / mod;
		}
		return ans;
	}

	@SuppressWarnings("unused")
	private static final class Graph<T> {
		private final Map<T, List<T>> g = new HashMap<>();

		public void addNode(final T node) {
			this.g.put(node, new ArrayList<>());
		}

		public void addEdge(final T source, final T destination) {
			this.g.get(source)
					.add(destination);
			this.g.get(destination)
					.add(source);
		}

		public List<List<T>> getAllPaths(final T source, final T destination) {
			final List<List<T>> paths = new ArrayList<>();
			try {
				recursive(source, destination, paths, new LinkedHashSet<T>());
			}
			catch (final Exception e) {
			}
			return paths;
		}

		private void recursive(final T current, final T destination, final List<List<T>> paths, final LinkedHashSet<T> path) {
			path.add(current);

			if (current.equals(destination)) {
				paths.add(new ArrayList<>(path));
				// path.remove(current);
				// return;
				throw new RuntimeException();
			}

			for (final T t : this.g.get(current)) {
				if (!path.contains(t)) {
					recursive(t, destination, paths, path);
				}
			}

			path.remove(current);
		}
	}

	// segment tree
	@SuppressWarnings("unused")
	private static final class SegmentTreeRMQ {
		int st[];

		int minVal(final int x, final int y) {
			return x > y ? x : y;
		}

		int getMid(final int s, final int e) {
			return s + (e - s) / 2;
		}

		int RMQUtil(final int ss, final int se, final int qs, final int qe,
				final int index) {
			if (qs <= ss && qe >= se) {
				return this.st[index];
			}

			if (se < qs || ss > qe) {
				return Integer.MIN_VALUE;
			}

			final int mid = getMid(ss, se);
			return minVal(RMQUtil(ss, mid, qs, qe, 2 * index + 1),
					RMQUtil(mid + 1, se, qs, qe, 2 * index + 2));
		}

		int RMQ(final int n, final int qs, final int qe) {
			if (qs < 0 || qe > n - 1 || qs > qe) {
				System.out.println("Invalid Input");
				return -1;
			}

			return RMQUtil(0, n - 1, qs, qe, 0);
		}

		int constructSTUtil(final int arr[], final int ss, final int se,
				final int si) {
			if (ss == se) {
				this.st[si] = arr[ss];
				return arr[ss];
			}

			final int mid = getMid(ss, se);
			this.st[si] = minVal(constructSTUtil(arr, ss, mid, si * 2 + 1),
					constructSTUtil(arr, mid + 1, se, si * 2 + 2));
			return this.st[si];
		}

		void constructST(final int arr[], final int n) {

			final int x = (int) Math.ceil(Math.log(n) / Math.log(2));
			final int max_size = 2 * (int) Math.pow(2, x) - 1;
			this.st = new int[max_size];
			constructSTUtil(arr, 0, n - 1, 0);
		}
	}

	// Trie node class
	private static final class TrieNode {
		private final TrieNode[] links;

		private final int R = 26;

		private boolean isEnd;

		private int size;

		public TrieNode() {
			this.links = new TrieNode[this.R];
		}

		public boolean containsKey(final char ch) {
			return this.links[ch - 'a'] != null;
		}

		public TrieNode get(final char ch) {
			return this.links[ch - 'a'];
		}

		public void put(final char ch, final TrieNode node) {
			this.links[ch - 'a'] = node;
			this.size++;
		}

		public void setEnd() {
			this.isEnd = true;
		}

		public boolean isEnd() {
			return this.isEnd;
		}

		public int getLinks() {
			return this.size;
		}
	}

	// Trie implementation
	@SuppressWarnings("unused")
	private static final class Trie {

		private final TrieNode root;

		public Trie() {
			this.root = new TrieNode();
		}

		public void insert(final String word) {
			TrieNode node = this.root;
			for (int i = 0; i < word.length(); i++) {
				final char currentChar = word.charAt(i);
				if (!node.containsKey(currentChar)) {
					node.put(currentChar, new TrieNode());
				}
				node = node.get(currentChar);
			}
			node.setEnd();
		}

		private TrieNode searchPrefix(final String word) {
			TrieNode node = this.root;
			for (int i = 0; i < word.length(); i++) {
				final char curLetter = word.charAt(i);
				if (node.containsKey(curLetter)) {
					node = node.get(curLetter);
				}
				else {
					return null;
				}
			}
			return node;
		}

		public boolean search(final String word) {
			final TrieNode node = searchPrefix(word);
			return node != null && node.isEnd();
		}

		public boolean startsWith(final String prefix) {
			final TrieNode node = searchPrefix(prefix);
			return node != null;
		}

		private String searchLongestPrefix(final String word) {
			TrieNode node = this.root;
			final StringBuilder prefix = new StringBuilder();
			for (int i = 0; i < word.length(); i++) {
				final char curLetter = word.charAt(i);
				if (node.containsKey(curLetter) && node.getLinks() == 1
						&& !node.isEnd()) {
					prefix.append(curLetter);
					node = node.get(curLetter);
				}
				else {
					return prefix.toString();
				}

			}
			return prefix.toString();
		}
	}

}
