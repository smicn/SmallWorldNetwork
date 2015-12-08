/*
 * COSC-5302 AOS, 2015 Spring /Project
 * 
 * Author: Shaomin (Samuel) Zhang
 * 
 * Email : smicn@foxmail.com
 * */
package com.lamar.aos.swn;

import java.util.Random;
import java.util.Vector;
import java.util.Iterator;

/**
 * Watts-Strogatz's model of Small-World Network
 **/
public class WattsStrogatz extends SWN {
	
	private Vector<Floyd.VVE> mGRAPH;
	private Floyd mFloyd;
	
	private Random mRand;
	private int mDiffPairsCount;
	private int mDiffPairsCountStep;
	private int mCurrentStep;
	private int mDiameterStep[] = new int[4];
	
	public WattsStrogatz() {
		super();
		mN = mK = 0;
		mP = 0;
	}
	
	public WattsStrogatz(int N, int K, float p) {	
		super(N, K, p);
	}
	
	public void setN(int N) throws Exceptions.ExceptionN {
		
		if (N <= 0) {
			throw new Exceptions.ExceptionN(N);
		}
		else {
			mN = N;
			
			if (mListener != null) {
				for (int ii = 0; ii < mN; ii++) {
					mListener.onPointCreated(ii);
				}
			}
			
			mCurrentStep = 0;
		}
	}
	
	public void setK(int K) throws Exceptions.ExceptionN, Exceptions.ExceptionK {

		if (mN <= 0) {
			throw new Exceptions.ExceptionN(mN);
		}
		else if (K <= 0 || K >= mN) {
			throw new Exceptions.ExceptionK(K);
		}
		else {
			mK = K;
			
			mGRAPH = new Vector<Floyd.VVE>();
			
			for (int ii = 0; ii < mN; ii++) {
				for (int jj = 1; jj <= mK; jj++) {
					int dst = (ii + jj) % mN;
					
					Floyd.VVE ge = new Floyd.VVE(ii, dst);
					mGRAPH.add(ge);
					
					if (mListener != null) {
						mListener.onPointsConnected(ii, dst);
					}
				}
			}
			
			mCurrentStep = 0;
			
			//printGraph();
		}
	}
	
	public void setP(float p) throws Exceptions.ExceptionN, 
					Exceptions.ExceptionK, Exceptions.ExceptionP {
		
		if (mN <= 0) {
			throw new Exceptions.ExceptionN(mN);
		}
		else if (mK <= 0 || mK >= mN) {
			throw new Exceptions.ExceptionK(mK);
		}
		else if (!(0.0 < p && p < 1.0)) {
			throw new Exceptions.ExceptionP(mP);
		}
		else {
			mP = p;
			
			mRand = new Random(System.currentTimeMillis());
			
			mDiffPairsCount = (int)(p * mN);
			mDiffPairsCountStep = 0;
			mCurrentStep = 0;
		}
	}
	
	public void stepSimulate() throws Exceptions.ExceptionN, 
					Exceptions.ExceptionK, Exceptions.ExceptionP {
		if (mN <= 0) {
			throw new Exceptions.ExceptionN(mN);
		}
		else if (mK <= 0 || mK >= mN) {
			throw new Exceptions.ExceptionK(mK);
		}
		else if (!(0.0 < mP && mP < 1.0)) {
			throw new Exceptions.ExceptionP(mP);
		}
		else if (false) {
			int count = 0;
			
			switch (mCurrentStep) {
			case 0:
			case 1:
				count = mDiffPairsCount/8;
				doStep(count);
				break;
				
			case 2:
				count = mDiffPairsCount/4;
				doStep(count);
				break;
				
			case 3:
				count = mDiffPairsCount - mDiffPairsCountStep;
				doStep(count);
				return;
				
			default:
				return;
			}
			
			mDiffPairsCountStep += count;
			mCurrentStep++;
		}
		else {
			doStep(mDiffPairsCount);
		}
	}
	
	private int selectRandomNeighbor(int src) {
		return src + mRand.nextInt(mK);
	}
	
	private int selectRandomLongRangeNeighbor(int src) {
		while (true) {
			int rand = mRand.nextInt(mN);
			if (!(src <= rand && rand < (src + mK))) {
				return rand;
			}
		}
	}
	
	public void startSimulate() {
		DBG.d("startSimu(): now rebuild the graph:" );

		mFloyd = new Floyd(mN, mGRAPH);
		DBG.d("startSimu(): graph.length=" + mGRAPH.size());
		DBG.d("startSimu(): calc by Floyd:" );
		mFloyd.compute();
		DBG.d("startSimu(): rebuilding graph finished." );
		
		DBG.d("startSimu(): now, analyze result:" );
		//mFloyd.print();
		mFloyd.analyze();
		Analyzer.getInstance().refresh(mFloyd.getDiameter(), mFloyd.getMeanDiameter());
		DBG.d("startSimu(): analysis finished." );
		DBG.d("startSimu(): now, here's the report:" );
		report();
	}
	
	private void doStep(int count) {
		
		DBG.d("doStep(" + mCurrentStep + ", " + count + "): change the links:" );
		
		for (int ii = 0; ii < count; ii++) {
			while (true) {
				int current = mRand.nextInt(mN);
				int local = selectRandomNeighbor(current);
				int distant = selectRandomLongRangeNeighbor(current);
				//TODO: to be continued... TUE, April 21.
				
				Floyd.VVE geLocal = contains(current, local);
				Floyd.VVE geDistant = contains(current, distant);
				
				/* *
				 * check if the local neighbor exists while 
				 * the distant one does not.
				 * **/
				if ((geLocal != null) && (null == geDistant)) {
					mGRAPH.remove(geLocal);
					
					geDistant = new Floyd.VVE(current, distant);
					mGRAPH.add(geDistant);
	
					if (mListener != null) {
						mListener.onPointsDisconnected(current, local);
						mListener.onPointsConnected(current, distant);
					}
					
					break;
				}
			}
		}
		
		//printGraph();
		
		/*
		 * now we have to rebuild the graph
		 * **/
		DBG.d("doStep(" + count + "): rebuild the graph:" );
		mFloyd = new Floyd(mN, mGRAPH);
		DBG.d("doStep(" + count + "): graph.length=" + mGRAPH.size());
		DBG.d("doStep(" + count + "): calc by Floyd:" );
		mFloyd.compute();
		DBG.d("doStep(" + count + "): rebuilding graph finished." );
		
		DBG.d("doStep(" + count + "): now, analyze result:" );
		mFloyd.analyze();
		Analyzer.getInstance().setDiameters(mCurrentStep+1, mFloyd.getDiameter(), mFloyd.getMeanDiameter());
		DBG.d("doStep(" + count + "): analysis finished." );
		DBG.d("doStep(" + count + "): now, here's the report:" );
		report();
		
		mCurrentStep++;
	}
	
	private void report() {
		/*
		 * TODO: report it to GUI and let it be updated, if needed.
		 * */
		DBG.d("floyd this graph finished, max-diameter=" + mFloyd.getDiameter() +
				", mean-diameter=" + mFloyd.getMeanDiameter());
	}
	
	private Floyd.VVE contains(int src, int dst) {
		Iterator iter = (Iterator)mGRAPH.iterator();
		while (iter.hasNext()) {
			Floyd.VVE ge = (Floyd.VVE )iter.next();
			if (((ge.v1 == src) && (ge.v2 == dst)) ||
			    ((ge.v1 == dst) && (ge.v2 == src))) {
				return ge;
			}
		}
		return null;
	}
	
	private void printGraph() {
		DBG.d("print the graph:");
		Iterator iter = (Iterator)mGRAPH.iterator();
		while (iter.hasNext()) {
			Floyd.VVE ge = (Floyd.VVE)iter.next();
			DBG.d("  (" + ge.v1 + ", " + ge.v2 + ", " + ge.e + ")");
		}
		DBG.d("printing graph end!");
	}
}
