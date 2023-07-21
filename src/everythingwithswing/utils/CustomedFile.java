package everythingwithswing.utils;

import java.io.File;


public class CustomedFile extends File {
	
	String fileName;
	String filePath;
	long fileSize;
	long lastModifiedDate;
	
	public CustomedFile(String filePath2) {
		super(filePath2);
		this.fileName = super.getName();
		this.filePath = super.getAbsolutePath();
		this.fileSize = super.length();
		this.lastModifiedDate = super.lastModified();
	}

	public String getFileName() {
		return fileName;
	}

	public String getFilePath() {
		return filePath;
	}
	public long getFileSize() {
		return fileSize;
	}

	public long getLastModifiedDate() {
		return lastModifiedDate;
	}
}
