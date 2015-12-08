/*
 * COSC-5302 AOS, 2015 Spring /Project
 * 
 * Author: Shaomin (Samuel) Zhang
 * 
 * Email : smicn@foxmail.com
 * */
package com.lamar.aos.swn;

/**
 * This class is a cmdline unit testing module for my debugging. 
 **/
public final class SWNTest {
	public class FakeListener implements SWN.SWNListener {
		
		public void FakeListener() {}
		
		public void onPointCreated(int src) {
			DBG.i("onPointCreated(" + src + ") is called!");
		}
		
		public void onPointsConnected(int src, int dst) {
			DBG.i("onPointsConnected(" + src + "," + dst + ") is called!");
		}
		
		public void onPointsDisconnected(int src, int dst) {
			DBG.i("onPointsDisconnected(" + src + "," + dst + ") is called!");
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Hello, Small-world Network!");
		
		//Graph.testDijkstra();

		SWNTest t = new SWNTest();
		//
		//SWN s = new SWNStub();
		SWN s = new WattsStrogatz();
		s.registerListener(t.new FakeListener());
		
		try {
			if (false) {
				//listbox1.onListBoxSelected();
				s.setN(5000);
				//listbox2.onListBoxSelected();
				s.setK(20);
				//listbox3.onListBoxSelected();
				s.setP((float)0.01);
			} else {
				s.setN(1000);
				s.setK(10);
				s.setP((float)0.01);
			}
			
			s.startSimulate();
			
			s.stepSimulate();
			s.stepSimulate();
			s.stepSimulate();
			s.stepSimulate();
		} catch (Exceptions.ExceptionN e) {
			System.out.println("You should set N first.");
		} catch (Exceptions.ExceptionK e) {
			System.out.println("You should set K first.");
		} catch (Exceptions.ExceptionP e) {
			System.out.println("You should set P first.");
		}

		System.out.println("Goodbye Small-world Network.");
	}
}
