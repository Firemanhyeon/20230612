package com.yedam.thread;

import com.yedam.synchronize.Calculator;

public class User2 extends Thread{
	private Calculator calculator;
	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
	@Override
	public void run() {
		calculator.setMemory(200);
	}
}
