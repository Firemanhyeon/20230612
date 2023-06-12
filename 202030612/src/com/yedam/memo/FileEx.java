package com.yedam.memo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileEx {
	
	public static void main(String[] args) {
		try {
//			FileOutputStream fos = new FileOutputStream("src/com/yedam/memo/sample.txt");//아무것도 안적으면 워크스페이스에 해당폴더에 생성 , 만들고있는 폴더에 만들고싶으면 src/com....
//			fos.write(10);
//			fos.close();// 출력
			
			//MemoApp.java 파일을 읽어서 화면(콘솔)에 출력
			InputStream is = new FileInputStream("src/com/yedam/memo/MemoApp.java");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String str = "";
			
			while((str = br.readLine())!= null) {
				System.out.println(str);
			}
			br.close();
			isr.close();
			is.close();
		} catch(Exception e ) {
			e.printStackTrace();
		}
		System.out.println("end of prog");
	}

}
