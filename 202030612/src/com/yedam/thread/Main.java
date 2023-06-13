package com.yedam.thread;

import com.yedam.synchronize.Calculator;

public class Main {
	public static void main(String[] args) {
		Calculator cal = new Calculator();
		
		User1 user1 = new User1();
		user1.setCalculator(cal);
		user1.start();
	}
}
