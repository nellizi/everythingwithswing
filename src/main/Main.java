package main;

import everythingwithswing.Frame.BasicFrame;
import everythingwithswing.Frame.Frame;

public class Main {

	/*
	 * 1. Main.java
	 * 2. Frame.java
	 * 3. FileUtil.java
	 * 
	 * 
	 * 1. 경로 입력하면 그 경로 하위 파일들 불러와서 띄우시기
	 * 2. 띄워진 파일들 중에서 검색어에 입력된 단어가 포함되어 있는 파일만 찾아서 띄우기 -> Jtable tablesolter 사용(금지,,)
	 * 
	 * 	====> main()에서 호출해서 프로그램이 실행되도록 -> 객체화!
	 * 		  -> jar로 묶어서 배포하면 다른 사람이 라이브러리를 추가해서 사용 가능 (api)	
	 * 
	 * */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Frame("c:/xtorm");
	}

}
