package com.puru_cr7.mylib;

/**
 * This class stores efficient data sturctures, to enable better time and space complexities.
 *
 * @author Purnendu Rath (puru_cr7)
 *         Created Oct 18, 2018.
 */

public class DataStructures {

	/**
	 * Segment Tree DataStructure for answering multiple range minimum queries efficiently
	 * This is to be used when no. of updtae and query operations in the array are almost equal
	 * computes at O(log n)
	 *
	 * @author puru_cr7
	 *         Created Oct 18, 2018.
	 */
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

		int constructSTUtil(final int arr[], final int ss, final int se, final int si) {
			if (ss == se) {
				this.st[si] = arr[ss];
				return arr[ss];
			}

			final int mid = getMid(ss, se);
			this.st[si] = minVal(constructSTUtil(arr, ss, mid, si * 2 + 1), constructSTUtil(arr, mid + 1, se, si * 2 + 2));
			return this.st[si];
		}

		void constructST(final int arr[], final int n) {
			final int x = (int) Math.ceil(Math.log(n) / Math.log(2));
			final int max_size = 2 * (int) Math.pow(2, x) - 1;
			this.st = new int[max_size];
			constructSTUtil(arr, 0, n - 1, 0);
		}
	}

}
