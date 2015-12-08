/*
 * COSC-5302 AOS, 2015 Spring /Project
 * 
 * Author: Shaomin (Samuel) Zhang
 * 
 * Email : smicn@foxmail.com
 * */
package com.lamar.aos.swn;

public final class DBG {
	private static final int LEVEL_ALL   = 0;
	private static final int LEVEL_INFO  = 1;
	private static final int LEVEL_WARN  = 2;
	private static final int LEVEL_DEBUG = 3;
	private static final int LEVEL_ERROR = 4;
	private static final int LEVEL_CLOSE = 5;
	
	private static final int mLogCtrlLevel = LEVEL_ALL;
	
	public static void i(String msg) {
		if (mLogCtrlLevel <= LEVEL_INFO) {
			System.out.println("[I]" + msg);
		}
	}
	
	public static void w(String msg) {
		if (mLogCtrlLevel <= LEVEL_WARN) {
			System.out.println("[W]" + msg);
		}
	}
	
	public static void d(String msg) {
		if (mLogCtrlLevel <= LEVEL_DEBUG) {
			System.out.println("[D]" + msg);
		}
	}
	
	public static void e(String msg) {
		if (mLogCtrlLevel <= LEVEL_ERROR) {
			System.out.println("[E]" + msg);
		}
	}
}
