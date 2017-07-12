package com.gc.learning.thread;

import java.util.concurrent.TimeUnit;

public class Daemon {
	public static void main(String[] args) {
		Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
		thread.setDaemon(true);
		thread.start();
	}

	static class DaemonRunner implements Runnable {
		@Override
		public void run() {
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println("DaemonThread finally run.");
			}
		}
	}
}