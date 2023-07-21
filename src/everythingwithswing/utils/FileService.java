package everythingwithswing.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileService {
	
	List<String> fileList = new ArrayList<String>(); // 모든 파일의 절대경로 리스트
	List<String> fileNameList = new ArrayList<String>(); // 모든 파일 이름 리스트
	List<CustomedFile> singleFileList = new ArrayList<CustomedFile>(); // 모든 파일 객체? 리스트
	List<String[]> filePathList = new ArrayList<String[]>();     

	public String[][] getList(String address) {
		
		/*
		 * 1. 검색할 디렉토리 명을 받아온다.
		 * 2. 디렉토리 명이 입력되지 않았으면 루트 디렉토리부터 전체 검색 한다. 
		 * 3. listFile()로 해당 디렉토리 내 모든 파일의 절대경로를 알아낸다.     -> fileList
		 * 4. 알아낸 절대경로를 통해 파일명만 따로 뽑아낸다.
		 * 5. new File(filePath) ->  file.length(); 로 파일 크기 구한다.
		 * 6. 파일 생성일자를 구한다.
		 * 7. 3,4,5,6 을 통해 알아낸 이차원 배열에 넣어준다(Swing 테이블에 값 입력해주기)
		 */
		
		
		String basic_address = "C:\\";

		if (address.isBlank()) {
			address = basic_address;
		}

		scanDir(address, fileList); // 절대경로 리스트
		// getFileName(fileList, fileNameList); // 이름 리스트
		getFileList(fileList, singleFileList);  // 파일 객체로 만들기

		String[][] data = new String[fileList.size()][4];


		for (int i = 0; i < singleFileList.size(); i++) {
			data[i][0] = singleFileList.get(i).getName();    
			data[i][1] = singleFileList.get(i).getAbsolutePath();
			data[i][2] = Long.toString(singleFileList.get(i).getFileSize()/1024) + "KB";
			data[i][3] =  String.valueOf(singleFileList.get(i).getLastModifiedDate());
		}

		return data;

	}

	
	private void getFileList(List<String> fileList, List<CustomedFile> singleFileList2) {
		for (String filePath : fileList) {
			CustomedFile s_file = new CustomedFile(filePath);
			singleFileList2.add(s_file);
		}

	}

	private void getFileName(List<String> fileList, List<String> fileNameList) {

	
		 /*
		 * 1. 디렉토리 내 모든 파일들의 전체 경로를 추출해서 filePathList에 담는다 
		 * 2. filePathList 값들을 \ 기준으로 나눠서 배열에 담는다. 
		 * 3. 배열을 list 로 변환한다. 
		 * 4. 변환한 list 를 reverse() 해서 파일명이 0번째 인덱스가 되게한다. 
		 * 5. 0번째 인덱스만 뽑아서 fileNameList에 담는다.
		 */
		System.out.println("=======================================");
		
		for(int i=0; i<fileList.size(); i++) {
			String[] arr = fileList.get(i).split("\\/");   // 구분자로 나누기 
			List<String> arrToList = Arrays.asList(arr);
			Collections.reverse(arrToList);  // list 역순으로 변환
			fileNameList.add(arrToList.get(i));
		System.out.println(fileNameList.get(i));
		}
		
						
	}
	

	/**
	 * 재귀 호출을 이용하여 하위 폴더를 탐색한다
	 * 
	 * @param folderPath
	 */
	public static void scanDir(String folderPath, List<String> fileList) {
		File[] files = new File(folderPath).listFiles();

		if (files == null) {
			// Null~Exception발생 -> 파일이 너무 많아서? 권한?
		} else {

			for (File f : files) {
				if (f.isDirectory()) {
					scanDir(f.getAbsolutePath(), fileList);
				} else {
					fileList.add(f.getAbsolutePath());
				}
			}
		}

	}

}
