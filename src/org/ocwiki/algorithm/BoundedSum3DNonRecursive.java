package org.ocwiki.algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

import org.ocwiki.util.Utils;

public class BoundedSum3DNonRecursive extends BoundedSum3D {

	private int[][][] curr;
	private int currF;
	private Element[] elements;

	public BoundedSum3DNonRecursive(int[][][] capacity, int[] r, int[] s,
			int[] t) {
		super();
		this.r = Arrays.copyOf(r, r.length);
		this.s = Arrays.copyOf(s, s.length);
		this.t = Arrays.copyOf(t, t.length);
		this.m = r.length;
		this.n = s.length;
		this.p = t.length;
		this.currF = Utils.min(Utils.sum(r), Utils.sum(s), Utils.sum(t));
		this.optF = currF + 1;

		this.c = new int[m][n][p];
		elements = new Element[m * n * p];
		int index = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < p; k++) {
					c[i][j][k] = Utils.min(capacity[i][j][k], r[i], s[j], t[k]);
					elements[index++] = new Element(i, j, k);
				}
			}
		}
		Arrays.sort(elements, new Comparator<Element>() {

			@Override
			public int compare(Element e1, Element e2) {
				return c[e1.i][e1.j][e1.k] - c[e2.i][e2.j][e2.k];
			}
		});

		curr = new int[m][n][p];
		opt = new int[m][n][p];

		solve();
	}

	private void solve() {
		LinkedList<Element> stack = new LinkedList<Element>();
		
		Element first = elements[0];
		first.fillValues();
		stack.push(first);

		while (!stack.isEmpty()) {
			Element e = stack.peek();
//			System.out.println(e);

			// xoá giá trị hiện tại
			int value = curr[e.i][e.j][e.k];
			r[e.i] += value;
			s[e.j] += value;
			t[e.k] += value;
			currF += value;
			curr[e.i][e.j][e.k] = 0;

			if (e.hasValue()) {
				// thử giá trị tiếp theo
				value = e.popRandomValue();
				curr[e.i][e.j][e.k] = value;
				r[e.i] -= value;
				s[e.j] -= value;
				t[e.k] -= value;
				currF -= value;

				if (stack.size() >= elements.length) {
					// điền đầy tất cả giá trị 
					configs++;
					if (currF < optF) {
						Utils.deepCopy(curr, opt);
						optF = currF;
					}
					if (currF == 0) {
						return;
					}
				} else {
					// thử giá trị tiếp theo
					Element next = elements[stack.size()];
					next.fillValues();
					stack.push(next);
				}
			} else {
				stack.pop();
			}
		}
	}

	public int getF() {
		return optF;
	}

	private class Element {
		int i, j, k;
		int[] values;
		int valueCount;

		public Element(int i, int j, int k) {
			super();
			this.i = i;
			this.j = j;
			this.k = k;
		}

		public boolean hasValue() {
			return valueCount > 0;
		}

		public int popRandomValue() {
			int index = rand.nextInt(valueCount);
			int value = values[index];
			for (int i = index; i < valueCount - 1; i++) {
				values[i] = values[i + 1];
			}
			valueCount--;
			return value;
		}

		public void fillValues() {
			this.valueCount = Utils.min(c[i][j][k], r[i], s[j], t[k]) + 1;
			values = new int[valueCount];
			for (int i = 0; i < valueCount; i++) {
				values[i] = i;
			}
		}
		
		@Override
		public String toString() {
			return "[" + i + ", " + j + ", " + k + "]: " + valueCount + " values";
		}
		
	}

}
