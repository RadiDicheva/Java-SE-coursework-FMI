package com.uni.sofia.fmi;

public class FileLineCount {
	private String file;
	private String line;
	private int lineCount;

	public FileLineCount(String file, String line, int lineCount) {
		this.file = file;
		this.line = line;
		this.lineCount = lineCount;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public int getLineCount() {
		return lineCount;
	}

	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
	}

	@Override
	public String toString() {
		return "File: " + getFile() + ", Line: " + getLine() + ", Line count: " + getLineCount();
	}
}
