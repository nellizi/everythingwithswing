package everythingwithswing.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AppService {

	public String[][] getList(String address) {
		// TODO Auto-generated method stub
		
		/* 재귀호출로 하위 디렉토리에 있는 파일까지 다 구하기 */

		List<String> fileList = new ArrayList<String>();

		scanDir(address, fileList);

		String[][] data = new String[fileList.size()][4];

		for (int i = 0; i < fileList.size(); i++) {
			for (int j = 0; j < 4; j++) {
				data[i][j] = fileList.get(i);
			}
		}

		return data;

	}

	/**
	 * 재귀 호출을 이용하여 하위 폴더를 탐색한다
	 * 
	 * @param folderPath
	 */
	public static void scanDir(String folderPath, List<String> fileLst) {
		File[] files = new File(folderPath).listFiles();

		if (files == null) {
			// Null~Exception발생 -> 파일이 너무 많아서? 권한? 
		} else {

			for (File f : files) {
				if (f.isDirectory()) {
					scanDir(f.getAbsolutePath(), fileLst);
				} else {
					fileLst.add(f.getAbsolutePath());
				}
			}
		}

	}

	

}
