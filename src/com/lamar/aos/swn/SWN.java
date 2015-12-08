/*
 * COSC-5302 AOS, 2015 Spring /Project
 * 
 * Author: Shaomin (Samuel) Zhang
 * 
 * Email : smicn@foxmail.com
 * */
package com.lamar.aos.swn;

import com.lamar.aos.swn.Exceptions.ExceptionN;

/**
 * Small-World Network
 **/
public class SWN {

	/**
	 * The interface for GUI 
	 **/
	public interface SWNListener {
		/**
		 * Please note that both the src and dst are point index. 
		 **/
		public void onPointCreated(int src);
		public void onPointsConnected(int src, int dst);
		public void onPointsDisconnected(int src, int dst);
	}
	
	public SWN() {
		super();
		mN = mK = 0;
	}
	
	public SWN(int N, int K, float p) {	
		super();
		mN = N;
		mK = K;
		mP = p;
	}
	
	public void setN(int N) throws Exceptions.ExceptionN {
		mN = N;
	}
	
	public void setK(int K) throws Exceptions.ExceptionN, Exceptions.ExceptionK {
		mK = K;
	}
	
	public void setP(float p) throws Exceptions.ExceptionN, 
					Exceptions.ExceptionK, Exceptions.ExceptionP {
		if (0.0 < p && p < 1.0) {
			mP = p;
		}
		else {
			System.out.println("error: unexpected p=" + p);
		}
	}
	
	public void registerListener(SWNListener listener) {
		mListener = listener;
	}
	
	public void initSimulation() {
	}
	
	public void startSimulate() {
	}
	
	public void stopSimulate() {
	}
	
	public void stepSimulate() throws Exceptions.ExceptionN, 
					Exceptions.ExceptionK, Exceptions.ExceptionP {	
	}
	
	public double getClusteringCoefficient() {
		return 0.0;
	}
	
	public int getDiameter() {
		return Integer.MAX_VALUE;
	}
	
	public int[] getNeighbors(int src) {
		return null;
	}
	
	protected int mN;
	protected int mK;
	protected float mP;
	protected SWNListener mListener;
}
