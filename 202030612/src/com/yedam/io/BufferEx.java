package com.yedam.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferEx {

	public static void main(String[] args) {
		try {
			bufferStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("end of prog");
	}
	public static void bufferStream() throws IOException {
		//읽고쓰고하는 값들을 한번에 용량을 크게해서 성능향상시킴
		FileInputStream fis= new FileInputStream("c:/temp/vscode.exe");
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		FileOutputStream fos = new FileOutputStream("C:/temp/copy2.exe");
		BufferedOutputStream bos= new BufferedOutputStream(fos);
		
		while(true) {
			int readBuf = bis.read();//readbuf = 읽은 값을 버퍼에 저장하겠습니다
			if(readBuf==-1) {
				break;
			}
			bos.write(readBuf);//읽은 값을 쓰겠습니다
		}
		bos.flush();
		bos.close();
		fos.close();
		bis.close();
		bos.close();
	}

}
