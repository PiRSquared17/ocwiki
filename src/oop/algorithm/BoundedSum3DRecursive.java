package oop.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import oop.util.Utils;

public class BoundedSum3DRecursive extends BoundedSum3D {

	private int[][][] x;
	private int f;
	private List<Element> elements;
	private int curr;

	public BoundedSum3DRecursive(int[][][] capacity, int[] r, int[] s, int[] t) {
		super();
		this.r = r;
		this.s = s;
		this.t = t;
		this.m = r.length;
		this.n = s.length;
		this.p = t.length;
		this.f = min(Utils.sum(r), Utils.sum(s), Utils.sum(t));
		this.optF = f + 1;

		this.c = new int[m][n][p];
		elements = new ArrayList<Element>(m * n * p);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < p; k++) {
					c[i][j][k] = min(capacity[i][j][k], r[i], s[j], t[k]);
					elements.add(new Element(i, j, k));
				}
			}
		}
		Collections.sort(elements, new Comparator<Element>() {

			@Override
			public int compare(Element e1, Element e2) {
				return c[e1.i][e1.j][e1.k] - c[e2.i][e2.j][e2.k];
			}
		});

		x = new int[m][n][p];
		opt = new int[m][n][p];
		curr = 0;

		solve();
	}

	private boolean solve() {
		// kiểm tra điều kiện dừng
		if (curr >= elements.size()) {
			configs++;
			if (f < optF) {
				Utils.deepCopy(x, opt);
				optF = f;
			}
			if (f == 0) {
				return true;
			}
			return false;
		}
		Element e = elements.get(curr);

		// chuẩn bị mảng giá trị
		int count = min(c[e.i][e.j][e.k], r[e.i], s[e.j], t[e.k]) + 1;
		ArrayList<Integer> values = new ArrayList<Integer>(count);
		for (int i = 0; i < count; i++) {
			values.add(i);
		}

		// lặp
		boolean solved = false;
		while (!values.isEmpty() && !solved) {
			// sinh ngẫu nhiên giá trị
			int index = rand.nextInt(values.size());
			int value = values.get(index);
			values.remove(index);

			// thử giá trị
			x[e.i][e.j][e.k] = value;
			r[e.i] -= value;
			s[e.j] -= value;
			t[e.k] -= value;
			f -= value;
			curr++;
			if (solve()) {
				solved = true;
			}

			// khôi phục
			curr--;
			f += value;
			r[e.i] += value;
			s[e.j] += value;
			t[e.k] += value;
		}

		return solved;
	}

	public int getF() {
		return optF;
	}

	private static int min(int... values) {
		int min = Integer.MAX_VALUE;
		for (int v : values) {
			if (v < min) {
				min = v;
			}
		}
		return min;
	}

	private static class Element {
		int i, j, k;

		public Element(int i, int j, int k) {
			super();
			this.i = i;
			this.j = j;
			this.k = k;
		}
	}

}
