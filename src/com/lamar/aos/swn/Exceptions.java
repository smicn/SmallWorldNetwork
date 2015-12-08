/*
 * COSC-5302 AOS, 2015 Spring /Project
 * 
 * Author: Shaomin (Samuel) Zhang
 * 
 * Email : smicn@foxmail.com
 * */
package com.lamar.aos.swn;

/**
 * Definition of all exceptions with Small-World Network
 **/
public class Exceptions {

	public static class ExceptionN extends Exception {
		private int mN;
		ExceptionN(int N) { mN = N; }
		public int getN() { return mN; }
	}
	
	public static class ExceptionK extends Exception {
		private int mK;
		ExceptionK(int K) { mK = K; }
		public int getK() { return mK; }
	}
	
	public static class ExceptionP extends Exception {
		private float mP;
		ExceptionP(float p) { mP = p; }
		public float getP() { return mP; }
	}
}
