package cn.com.roadchina.design.Jersey_test;

public class MulThreadTest {
	
	public static void main(String[] args) {
		System.out.println("2323");
		Thread thread =new Thread(new ThreadA());
		thread.setDaemon(true);
		loop();
		thread.start();
		System.out.println("2323");
	}
	
	public static void loop(){
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				for (int j2 = 0; j2 < 100; j2++) {
					System.out.print(j2);
				}
			}
		}
	}

}
