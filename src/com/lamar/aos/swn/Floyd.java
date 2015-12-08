/*
 * COSC-5302 AOS, 2015 Spring /Project
 * 
 * Author: Shaomin (Samuel) Zhang
 * 
 * Email : smicn@foxmail.com
 * */
package com.lamar.aos.swn;

import java.util.Iterator;
import java.util.Vector;

/**
 * O(N3): Floyd's all-pair shortest path algorithm
 **/
public class Floyd {

	public static final int INF = 10000;
	
	private int[][] mDist;
	private final int mN;
	private int mDiameterMax;
	private int mDiameterMean;
	
	public static class VVE {
		public final int v1, v2;
		public final int e;
		public VVE(int v1, int v2, int e) {
			this.v1 = v1;
			this.v2 = v2;
			this.e  = e;
		}
		public VVE(int v1, int v2) {
			this.v1 = v1;
			this.v2 = v2;
			this.e  = 1;
		}
	}
	
	public Floyd(int N, Vector<VVE> graph) {
		mN = N;
		mDist = new int[mN][];
		
		for (int ii = 0; ii < mN; ii++) {
			mDist[ii] = new int[mN];
			for (int jj = 0; jj < mN; jj++) {
				if (ii == jj) {
					mDist[ii][jj] = 0;
				}
				else {
					mDist[ii][jj] = INF;
				}
			}
		}
		
		Iterator iter = (Iterator)graph.iterator();
		while (iter.hasNext()) {
			VVE n = (VVE)iter.next();
			mDist[n.v1][n.v2] = n.e;
			mDist[n.v2][n.v1] = n.e;
		}
	}
	
	/**
	 * It's simple, straightforward but as slow as O(N3).
	 **/
	public void compute() {
		for (int kk = 0; kk < mN; kk++) {
			for (int ii = 0; ii < mN; ii++) {
				for (int jj = 0; jj < mN; jj++) {
					if (mDist[ii][kk] + mDist[kk][jj] < mDist[ii][jj]) {
						mDist[ii][jj] = mDist[ii][kk] + mDist[kk][jj];
          			}
				}
			}
		}
	}

	/**
	 * Calculate the Mean and Maximum Diameter.
	 **/
	public void analyze() {
		int sum = 0, max = 0;
		for (int ii = 0; ii < mN; ii++) {
			for (int jj = 0; jj < mN; jj++) {
				if (ii != jj) {
					sum += mDist[ii][jj];
					if (mDist[ii][jj] > max) {
						max = mDist[ii][jj];
					}
				}
			}
		}
		mDiameterMax = max;
		mDiameterMean = sum/(mN*(mN-1));
	}
	
	public int getDiameter() {
		return mDiameterMax;
	}
	
	public int getMeanDiameter() {
		return mDiameterMean;
	}
	
	public void print() {
		System.out.println("Floyd.matrix prints:");
		for (int ii = 0; ii < mN; ii++) {
			for (int jj = 0; jj < mN; jj++) {
				System.out.print(" " + mDist[ii][jj]);
			}
			System.out.println();
		}
		System.out.println("Floyd.matrix print end!");
	}
}
