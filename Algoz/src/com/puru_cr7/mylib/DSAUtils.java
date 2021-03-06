package com.puru_cr7.mylib;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * This class stores efficient data sturctures, to enable better time and space complexities.
 *
 * @author Purnendu Rath (puru_cr7)
 *         Created Oct 18, 2018.
 */

public class DSAUtils {

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
	 * The following classes and methods are used to perform BFS to find shortest path by avoiding obstacles in a grid.
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

	/**
	 * Computes the min cost matrix using DP for a grid from (0,0) to (m,n)
	 * possible movement are donward, sideways nad diagonal
	 *
	 * @param cost
	 * @param m
	 * @param n
	 * @return min cost to reach (m,n)
	 */
	private static int minCostDP(final int cost[][], final int m, final int n) {
		int i, j;
		final int tc[][] = new int[m + 1][n + 1];

		tc[0][0] = cost[0][0];

		for (i = 1; i <= m; i++) {
			tc[i][0] = tc[i - 1][0] + cost[i][0];
		}

		for (j = 1; j <= n; j++) {
			tc[0][j] = tc[0][j - 1] + cost[0][j];
		}

		for (i = 1; i <= m; i++) {
			for (j = 1; j <= n; j++) {
				tc[i][j] = Math.min(tc[i - 1][j - 1], Math.min(
						tc[i - 1][j],
						tc[i][j - 1])) + cost[i][j];
			}
		}

		return tc[m][n];
	}

	// find all combinations of given size of an array
	static void findCombinations(final int arr[], final int n, final int r, final List<List<Integer>> list) {
		final int data[] = new int[r];
		combinationUtil(arr, data, 0, n - 1, 0, r, list);
	}

	private static void combinationUtil(final int arr[], final int data[], final int start,
			final int end, final int index, final int r, final List<List<Integer>> list) {

		final List<Integer> l = new ArrayList<>();
		if (index == r) {
			for (int j = 0; j < r; j++) {
				l.add(data[j]);
			}
			list.add(l);
			return;
		}

		for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
			data[index] = arr[i];
			combinationUtil(arr, data, i + 1, end, index + 1, r, list);
		}
	}

	// permute a given array and return List of permutations
	public static ArrayList<ArrayList<Integer>> permute(final Object[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		result.add(new ArrayList<Integer>());
		for (int i = 0; i < num.length; i++) {
			final ArrayList<ArrayList<Integer>> current = new ArrayList<>();
			for (final ArrayList<Integer> l : result) {
				for (int j = 0; j < l.size() + 1; j++) {
					l.add(j, (Integer) num[i]);
					final ArrayList<Integer> temp = new ArrayList<>(l);
					current.add(temp);
					l.remove(j);
				}
			}
			result = new ArrayList<>(current);
		}
		return result;
	}

	public static int daysInMonth(final int mon) {
		switch (mon) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return 31;
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			case 2:
				return 28;
		}
		return 0;
	}

	public static boolean isLeapYear(final int year) {
		return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;

	}

}
