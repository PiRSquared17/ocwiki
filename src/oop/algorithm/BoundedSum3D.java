package oop.algorithm;

import java.util.Arrays;
import java.util.Random;

import oop.util.Utils;

public abstract class BoundedSum3D {

	protected int[][][] opt;
	protected int optF;
	protected int[][][] c;
	protected int[] r;
	protected int[] s;
	protected int[] t;
	protected int p;
	protected int m;
	protected int n;
	protected long configs = 0;
	protected Random rand = new Random(System.currentTimeMillis());

	public BoundedSum3D() {
		super();
	}

	public int[][][] copyOfResult() {
		int[][][] copy = new int[m][n][p];
		Utils.deepCopy(opt, copy);
		return copy;
	}

	public int getValue(int i, int j, int k) {
		return opt[i][j][k];
	}

	public long getConfigCount() {
		return configs;
	}

	@Override
	public String toString() {
		return Arrays.deepToString(opt);
	}

}