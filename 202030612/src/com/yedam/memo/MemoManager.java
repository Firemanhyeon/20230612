package com.yedam.memo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemoManager {
	List<Memo> storage = new ArrayList<>();
	Scanner sc = new Scanner(System.in);

	MemoManager(){
		readFromFile();
	}
	//등록.
	public void inputData() {
		System.out.println("번호> ");
		int no = Integer.parseInt(sc.nextLine());
		System.out.println("날짜> ");
		String date = sc.nextLine();
		System.out.println("내용> ");
		String content = sc.nextLine();

		storage.add(new Memo(no, date, content));
	}

	// 조회
	public void searchData() {
		System.out.print("날짜> ");
		String date = sc.nextLine();
		boolean isYn = false;
		for (int i = 0; i < storage.size(); i++) {
			if (date.equals(storage.get(i).getDate())) {
				System.out.println(storage.get(i));
				isYn = true;
			}
		}
	}

	// 삭제
	public void deleteData() {
		System.out.print("번호>");
		int num = Integer.parseInt(sc.nextLine());
		boolean isYn = false;
		for (int i = 0; i < storage.size(); i++) {
			if (num==storage.get(i).getNo()) {
				storage.remove(storage.get(i));
				System.out.println("삭제완료");
				isYn = true;
			}
		}

	}

	// 실행
	public void readFromFile() {
		try {
			FileInputStream fis = new FileInputStream("c:/temp/memobook.dat");

			ObjectInputStream ois = new ObjectInputStream(fis);
			storage = (List<Memo>) ois.readObject();
			ois.close();
			fis.close();
		} catch (FileNotFoundException | ClassNotFoundException e) {

//				e.printStackTrace();
		} catch (IOException e) {

		}
	}

	// 파일저장
	public void storeToFile() {
		try {
			FileOutputStream fos = new FileOutputStream("c:/temp/memobook.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(storage);
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
