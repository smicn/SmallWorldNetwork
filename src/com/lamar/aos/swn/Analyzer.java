/*
 * COSC-5302 AOS, 2015 Spring /Project
 * 
 * Author: Shaomin (Samuel) Zhang
 * 
 * Email : smicn@foxmail.com
 * */
package com.lamar.aos.swn;

/**
 * This class is the mediator between Graph domain and GUI.
 **/
public class Analyzer {
	
	private static Analyzer mInst;
	
	private static final int MAXNUM_SAMPLES = 5;

	private int mMaxDiameter[];
	private int mMeanDiameter[];
	
	public static Analyzer getInstance() {
		if (null == mInst) {
			mInst = new Analyzer();
		}
		return mInst;
	}
	
	private Analyzer() {}
	
	public int getSampleCount() {
		return MAXNUM_SAMPLES;
	}
	
	public int getMaxY() {
		return mMaxDiameter[0];
	}
	
	public int getMaxDiameter(int sampleIndex) {
		if (0 <= sampleIndex && sampleIndex < MAXNUM_SAMPLES) {
			return mMaxDiameter[sampleIndex];
		} 
		else {
			DBG.e("getMax() fatal error: out-of-range your sample index=" + sampleIndex);
			return 0;
		}
	}
	
	public int getMeanDiameter(int sampleIndex) {
		if (0 <= sampleIndex && sampleIndex < MAXNUM_SAMPLES) {
			return mMeanDiameter[sampleIndex];
		} 
		else {
			DBG.e("getMean() fatal error: out-of-range your sample index=" + sampleIndex);
			return 0;
		}
	}
	
	public void refresh(int max, int mean) {
		
		DBG.d("Analyzer.refresh(max="  + max + ", mean=" + mean + ").");
		
		mMaxDiameter  = new int[MAXNUM_SAMPLES];
		mMeanDiameter = new int[MAXNUM_SAMPLES];
		
		mMaxDiameter [0] = max;
		mMeanDiameter[0] = mean;
	}
	
	public void setDiameters(int sampleIndex, int max, int mean) {
		
		DBG.d("Analyzer.setD(" + sampleIndex + ", max="  + max + ", mean=" + mean + ").");
		
		if (0 <= sampleIndex && sampleIndex < MAXNUM_SAMPLES) {
			mMaxDiameter [sampleIndex] = max;
			mMeanDiameter[sampleIndex] = mean;
		} 
		else {
			DBG.e("set() fatal error: out-of-range your sample index=" + sampleIndex);
		}
	}
}
