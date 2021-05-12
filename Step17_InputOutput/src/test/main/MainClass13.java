package test.main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainClass13 {
	public static void main(String[] args) {
		File memoFile=new File("c:/acorn202104/myFolder/memo.txt");
		try {
			if(!memoFile.exists()) {
				System.out.println("파일이 존재 하지 않습니다.");
				return; //메소드 끝내기 
			}
			//파일에서 문자열을 읽어들일 객체
			FileReader fr = new FileReader(memoFile);
			while(true) {
				int code = fr.read();
				if (code == -1) {
					break;
				}
				char ch = (char)code;
				System.out.print(ch);
			}
		}catch(IOException ie) {
			ie.printStackTrace();
		}
	}
}




