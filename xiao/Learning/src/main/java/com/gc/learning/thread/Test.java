package com.gc.learning.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/** 
* @ClassName: Test 
* @Description: TODO
* @author chen.gao@baidao.com   
* @date 2017年5月31日 上午11:20:48 
*  
*/

public class Test {

	static volatile int[] n = { 0, 0, 0 };

	static AtomicInteger s = new AtomicInteger(0);

	static volatile List<Integer> l = new ArrayList<Integer>();

	static {
		l.add(0);
	}

	public static void main(String[] args) throws InterruptedException {
		//		Thread a = new Thread("a") {
		//			@Override
		//			public void run() {
		//				int i = 1000;
		//				while (i-- > 0) {
		//					n[0]++;
		//					s++;
		//				}
		//			}
		//		};
		//		Thread b = new Thread(a);
		//		Thread c = new Thread(a);
		//		a.start();
		//		b.start();
		//		c.start();
		int i = 10000;
		while (i-- > 0) {
			new Thread("a") {
				@Override
				public void run() {
					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					n[0]++;
					s.addAndGet(1);
					l.set(0, l.get(0) + 1);
				}
			}.start();
		}
		Thread.sleep(2000);
		System.out.println(n[0]);
		System.out.println(s);
		System.out.println(l.get(0));
	}

}
