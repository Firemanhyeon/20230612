package com.yedam.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StudentEx {

	public static void main(String[] args) throws IOException {

		method2();

	}

	public static void method1() throws IOException {
		// C:/temp/students.txt 학생의 점수의 평균을 구하기
		// 최고점수의 학생의 이름과 최고점수출력
		// 평균: ~~
		// 최고점수 ~~점 학생은 ~~~
		Scanner sc = new Scanner(new File("c:/temp/students.txt"));

		while (true) {
			try {
				String input = sc.nextLine();
				String name = "";
				int sum = 0, maxScore = 0;
				double avg = 0;
				int cnt = 0;
				while (true) {
					String values = "";
					try {
						values = sc.nextLine();// 학생정보 한개라인

					} catch (NoSuchElementException e) {
						break;
					}
					String[] valAry = values.split(" ");
					sum += Integer.parseInt(valAry[2]);
					cnt++;
					if (maxScore < Integer.parseInt(valAry[2])) {
						maxScore = Integer.parseInt(valAry[2]);
						name = valAry[1];
					}
				}
				avg = (double) sum / cnt;
				System.out.println("평균점수: " + avg);
				System.out.println("최고점수: " + maxScore + " 학생이름:" + name);
			} catch (NoSuchElementException e) {
				break;

			}
		}
	}

	public static void method2() throws IOException {
		int sum,maxScore;
		double avg;
		InputStream is = new FileInputStream("c:/temp/students.txt");
		InputStreamReader isr = new InputStreamReader(is);// 바이트기반의 메소드에 char 기반
		char[] buf = new char[200];
		isr.read(buf);// read라는메소드가 입력스트림을 통해서 버퍼라는배열에다가 읽은 값을 담아준다

		// 버퍼에있는값을 new라고하는 키워드로 스트링문자열을 만들때 버퍼에있는 모든 내용을 읽어와서 문자열을 하나만든다
		String str = new String(buf);
//		System.out.println(str);
		String[] strAry = str.split("\n");
		for (String student : strAry) {
			if (student != null) {
				String[] std= student.split(" ");
				System.out.printf("학생번호 %s , 이름 %s, 점수 %s",std[0],std[1],std[2]);

			}
		}

		isr.close();
		is.close();
	}
}
