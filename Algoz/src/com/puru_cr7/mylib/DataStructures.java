package com.puru_cr7.mylib;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This class stores efficient data sturctures, to enable better time and space complexities.
 *
 * @author Purnendu Rath (puru_cr7)
 *         Created Oct 18, 2018.
 */

public class DataStructures {

	/**
	 * Segment Tree DataStructure for answering multiple range minimum queries efficiently
	 * This is to be used when no. of update and query operations in the array are almost equal
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

	/**
	 * The following classes and methods are used to berform BFS to find shortest paths by avoiding obstacles in a grid.
	 * All 0's in the grid are obstacles
	 *
	 * @author Purnendu Rath (puru_cr7).
	 *         Created Feb 25, 2019.
	 */
	private static final class Point {
		public int x;
		public int y;

		@Override
		public String toString() {
			return "Point [x=" + this.x + ", y=" + this.y + "]";
		}

	}

	private static final class Node {
		public Point pt;
		public int dist;
	}

	public static int rowNum[] = {-1, 0, 0, 1};
	public static int colNum[] = {0, -1, 1, 0};

	public static int BFS(final boolean[][] mat, final Point src, final Point dest, final int n, final int m) {

		final boolean[][] visited = new boolean[n][m];
		visited[src.x][src.y] = true;

		final Queue<Node> q = new LinkedList<>();

		final Node s = new Node();
		s.pt = src;
		s.dist = 0;
		q.add(s);

		while (!q.isEmpty()) {

			final Node curr = q.peek();
			final Point pt = curr.pt;

			if (pt.x == dest.x && pt.y == dest.y) {
				return curr.dist;
			}

			q.remove();

			for (int i = 0; i < 4; i++) {
				final int row = pt.x + rowNum[i];
				final int col = pt.y + colNum[i];

				if (isValid(row, col, n, m) && mat[row][col] &&
						!visited[row][col]) {
					visited[row][col] = true;
					final Point pt1 = new Point();
					pt1.x = row;
					pt1.y = col;

					final Node Adjcell = new Node();
					Adjcell.dist = curr.dist + 1;
					Adjcell.pt = pt1;
					q.add(Adjcell);

				}
			}
		}

		return -1;
	}

	static boolean isValid(final int row, final int col, final int ROW, final int COL) {
		return row >= 0 && row < ROW && col >= 0 && col < COL;
	}

}
