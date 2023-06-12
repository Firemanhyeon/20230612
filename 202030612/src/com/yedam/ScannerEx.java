package com.yedam;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ScannerEx {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("C:/temp/sample.txt"));
		while (true) {
			try {
				String input = sc.nextLine();
				System.out.println(input);
//				if (input == null) {
//					break;
//				}
			} catch (NoSuchElementException e) {
				break;

			}
		}
		System.out.println("end of prog");
	}

}
