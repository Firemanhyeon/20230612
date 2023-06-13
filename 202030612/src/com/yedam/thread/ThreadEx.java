package com.yedam.thread;

import java.awt.Toolkit;

public class ThreadEx {

	public static void main(String[] args) {
		// 화면에다가 내용을 출력하면서 동시에 소리를 내는 예제

		// 1.Runnable 인터페이스를 구현하는 클래스를 하나 만들어서 사용
//		Thread thread = new Thread(new BeepTask());

		//2.  Runnable이라는 인터페이스를 직접 구현하는 코드를 작성해도 된다
		//    Runnable 인터페이스의 익명구현객체생성
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				for (int i = 0; i < 5; i++) {
					toolkit.beep();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
//		thread.start();
		//3. Thread 클래스를 상속받는 하위클래스를 통해서 생성
		thread = new BeepThread();
		thread.start();

		for (int i = 0; i < 5; i++) {
			System.out.println("print");
			try {
				Thread.sleep(1000);// 1초동안중지했다가 실행함
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
