package com.yedam.phonebook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Iterator;

import lombok.Data;

@Data
class User {
	String id;
	String pw;

	public User(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}
}

public class UserCheck {
	HashSet<User> userList = new HashSet<>();

	UserCheck() {
		readFromFile();
	}

	// id,passwd를 입력받으면 ture / false
	public boolean loginCheck(String id, String pw) {
		Iterator<User> iter = userList.iterator();
		while (iter.hasNext()) {
			User user = iter.next();
			if (user.id.equals(id) && user.pw.equals(pw)) {
				return true;
			}
		}
		return false;
	}

	public void readFromFile() {
		try {
			FileReader fr = new FileReader("c:/temp/userList.txt");
			BufferedReader br = new BufferedReader(fr);
			while (true) {
				String str = br.readLine();// 읽어들일 라인에 한라인씩 읽어들이겠다
											// user1 1111
				if (str == null) {
					break;
				}
				String[] record = str.split(" ");// 읽어들인 라인 배열로 나누기
				userList.add(new User(record[0], record[1]));
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// c:/temp/userList.txt
		// 목록정보 사용자 입력정보랑 비교할수있도록 처리
	}
}
