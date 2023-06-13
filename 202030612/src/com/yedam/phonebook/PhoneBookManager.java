package com.yedam.phonebook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;



//추가 , 조회 ,삭제 , 종료(파일에 저장)
public class PhoneBookManager implements Serializable{
	// 친구이름은 중복 x Set 컬렉션으로 만들어보기
	HashSet<PhoneInfo> infoStorage = new HashSet<>();
	File dataFile = new File("c:/temp/phonebook.txt");
	File dataStream = new File("c:/temp/phonebook.dat");

	private static PhoneBookManager instance;

	private PhoneBookManager() {// 파일생성메소드
		readFromFile();// 파일읽기메소드 저장된 정보를 set 컬렉션이 초기화 한다

	}

	public static PhoneBookManager getInstance() {
		if (instance == null) {
			instance = new PhoneBookManager();
		}
		return instance;
	}

	// 등록메소드
	public void inputData() throws MenuChoiceException {
		System.out.println("전화번호 구분");
		System.out.println("1. 일반 2. 대학 3. 회사");
		System.out.print("선택> ");

		PhoneInfo info = null;//
		int menu = MenuViewer.sc.nextInt();
		MenuViewer.sc.nextLine();

		if (menu < INPUT_SELECT.NORMAL || menu > INPUT_SELECT.COMPANY) {
			throw new MenuChoiceException(menu);// 번호 제대로안적을때 오류 예외처리
		}

		switch (menu) {
		case INPUT_SELECT.NORMAL:
			info = readFriendInfo();
			break;
		case INPUT_SELECT.UNIV:
			info = readUnivFriendInfo();
			break;

		case INPUT_SELECT.COMPANY:
			info = readCompanyFriendInfo();
			break;
		}

		boolean isAdded = infoStorage.add(info);// set컬렉션은 중복안되서 정상등록되면 true 실패면 false
		if (isAdded) {
			System.out.println("등록완료");
		} else {
			System.out.println("등록오류");
		}

	}

	// 친구등록 메소드 세 친구 모두 이름,연락처는 입력받아야하니 메소드로 묶어준다
	private PhoneInfo readFriendInfo() {
		System.out.println("이름을 입력하세요");
		String name = MenuViewer.sc.nextLine();
		System.out.println("연락처를 입력하세요");
		String phone = MenuViewer.sc.nextLine();
		return new PhoneInfo(name, phone);
	}

	// 학교친구 메소드
	private PhoneInfo readUnivFriendInfo() {
		System.out.println("이름을 입력하세요");
		String name = MenuViewer.sc.nextLine();
		System.out.println("연락처를 입력하세요");
		String phone = MenuViewer.sc.nextLine();
		System.out.println("전공을 입력하세요");
		String major = MenuViewer.sc.nextLine();
		System.out.println("학년을 입력하세요");
		int year = MenuViewer.sc.nextInt();
		MenuViewer.sc.nextLine();

		return new PhoneUnivInfo(name, phone, major, year);
	}

	// 회사친구 메소드
	private PhoneInfo readCompanyFriendInfo() {
		System.out.println("이름을 입력하세요");
		String name = MenuViewer.sc.nextLine();
		System.out.println("연락처를 입력하세요");
		String phone = MenuViewer.sc.nextLine();
		System.out.println("회사를 입력하세요");
		String company = MenuViewer.sc.nextLine();

		return new PhoneCompanyInfo(name, phone, company);
	}
	
	// 종료후저장메소드 storeToStream() = > ObjectOutputStream: Serializable 으로 바꿀수있음
	public void storeToFile() {
		System.out.println("종료합니다");
		try {
			FileWriter fw = new FileWriter(dataFile);
			Iterator<PhoneInfo> iter = infoStorage.iterator();
			while (iter.hasNext()) {
				fw.write(iter.next().toString());// 이름 연락처 전공 학년
			}
			fw.flush();
			fw.close();

			System.out.println("저장완료");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	// 파일 읽기 메소드
	public void readFromFile() {//readFromStream() = >ObjectInputStream 으로 바꿀수있다 
		if(!dataFile.exists()) {//파일이 있는지 없는지 확인하는 메소드 exists
			return;
		}
		try {
			FileReader fr = new FileReader(dataFile);
			BufferedReader br = new BufferedReader(fr);

			String str = "";
			PhoneInfo info = null;
			while ((str = br.readLine()) != null) {
				String[] record = str.split(",");
				int kind = Integer.parseInt(record[0]);
				switch (kind) {
				case INPUT_SELECT.NORMAL:
					info = new PhoneInfo(record[1], record[2]);
					break;
				case INPUT_SELECT.UNIV:
					info = new PhoneUnivInfo(record[1], record[2], record[3], Integer.parseInt(record[4]));
					break;
				case INPUT_SELECT.COMPANY:
					info = new PhoneCompanyInfo(record[1], record[2], record[3]);
					break;
				}
				infoStorage.add(info);
			}
			br.close();
			fr.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 삭제메소드
	public void deleteData() {
		System.out.println("이름> ");
		String name = MenuViewer.sc.nextLine();

		Iterator<PhoneInfo> iter = infoStorage.iterator();
		while (iter.hasNext()) {
			PhoneInfo curr = iter.next();
			if (curr.getName().equals(name)) {
				iter.remove();
				System.out.println("삭제완료");
				return;
			}
		}
		System.out.println("삭제할 이름이 없습니다");
	}
	//검색메소드
	public void searchData() {
		System.out.println("이름> ");
		String name = MenuViewer.sc.nextLine();
		
		PhoneInfo info = search(name);//search메소드 사용 
		if(info == null) {
			System.out.println("찾는 이름이 없습니다");	
		}
		else {
			info.showPhoneInfo();
		}
	}
	
	//검색메소드(이름 중복확인메소드)
	public PhoneInfo search(String name) {
		Iterator<PhoneInfo>iter = infoStorage.iterator();
		while(iter.hasNext()) {
			PhoneInfo curItem = iter.next();
			if(curItem.getName().equals(name)) {
				return curItem;
			}
		}
		return null;
	}
}
