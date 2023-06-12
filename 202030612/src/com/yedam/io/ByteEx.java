package com.yedam.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ByteEx {

	public static void main(String[] args) {
		//입력(inputStream) 후 출력(OutputStream)을 해본다
		//카피
		try {
			FileInputStream fis = new FileInputStream("c:/temp/vscode.exe");//읽어들이기위한 용도
			FileOutputStream fos = new FileOutputStream("c:/temp/copy.exe");
			byte[]bytes=new byte[100];
			while(true) {
				int readInt = fis.read(bytes);
				System.out.println(readInt);
				if(readInt ==-1) {
					break;
				}
				fos.write(bytes);
				
			}
			fis.close();
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void write1() {
		// 출력스트림 외부파일에 저장하겠단 의미
		// 최상위 스트림 : 아웃풋스트림
		// 파일아웃풋스트림:
		try {
			OutputStream os = new FileOutputStream("c:/temp/data1.dat");
			byte a = 10;
			byte b = 20;// 값저장
			os.write(a);//
			os.write(b);
			os.flush();// 캐시 출력후지우기
			os.close();// 리소스환원
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void read1() {
		// 입력스트림 inptstream
		try {
			InputStream is = new FileInputStream("c:/temp/data1.dat");

			while (true) {// 안에 들어가있는값 리턴 
				int readInt = is.read();
				if (readInt == -1) {//파일의 끝부분 
					break;
				}
				System.out.println(readInt);
			}
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end of prog");
	}
}
