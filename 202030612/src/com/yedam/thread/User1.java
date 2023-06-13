package com.yedam.thread;

import com.yedam.synchronize.Calculator;
//User1 스레드 : calculator memory = 
//100 계산기메모리에 100이라는값을담아주는 
public class User1 extends Thread{
	private Calculator calculator;
	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
	@Override
	public void run() {
		calculator.setMemory(100);
	}
}
