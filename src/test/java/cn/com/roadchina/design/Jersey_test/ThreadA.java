package cn.com.roadchina.design.Jersey_test;


public class ThreadA implements Runnable{

	public void run() {
		try {
			System.out.println("A++"+Thread.currentThread().getName());
			Thread.sleep(100);
			for (int i = 0; i < 10; i++) {
				System.out.println("A"+i);
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
