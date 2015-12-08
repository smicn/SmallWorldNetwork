/*
 * COSC-5302 AOS, 2015 Spring /Project
 * 
 * Author: Shaomin (Samuel) Zhang
 * 
 * Email : smicn@foxmail.com
 * */
package com.lamar.aos.swn;

/**
 * This class works at the early stage of development and 
 * it works as the promise and interfaces to seperate the 
 * GUI modules and the rest of the software.
 **/
public class SWNStub extends SWN {

	public SWNStub() {
		super();
	}
	
	public SWNStub(int N, int K, float p) {	
		super(N, K, p);
	}
	
	public void stepSimulate() {
		int src = mCurrent;
		int dst = (mCurrent + 1) % mN;
		
		if (mListener != null) {
			mListener.onPointsConnected(src, dst);
		}
		
		mCurrent = dst;
	}
	
	private int mCurrent = 0;
}
