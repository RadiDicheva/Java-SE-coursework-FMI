package com.uni.sofia.fmi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StringSearch {
	static List<File> fileList = new ArrayList<File>();
		
	public static List<File> getTextFiles(File directory) {

		File[] listOfFiles = directory.listFiles();

		for (File file : listOfFiles) {
			if (file.isDirectory()) {
				getTextFiles(file);
			} else if (file.isFile() && file.getName().endsWith(".txt")) {
				fileList.add(file);
			}
		}
		
		return fileList;
	}

	public static void printFileContent(String input, List<File> txtFileList) {

		for (File file : txtFileList) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line;
				int counter = 1;

				while ((line = br.readLine()) != null) {
					if (line.contains(input)) {
						System.out.println("File: " + file.getName() + ", Line: " + line + ", Line count: " + counter);
					}
					counter++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		
		File directory = new File("E:/TestBooks");
		getTextFiles(directory);
		//printFileContent("666", getTextFiles(directory));
		
		System.out.println("Enter the string you'd like me to search for: ");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		
		BlockingQueue<FileLineCount> queue = new ArrayBlockingQueue<FileLineCount>(1000);
		ExecutorService prExecutor = Executors.newFixedThreadPool(3);
		ExecutorService conExecutor = Executors.newFixedThreadPool(3);
		
		for (File file : fileList) {
			prExecutor.execute(new Producer(file, queue));
			conExecutor.execute(new Consumer(input, queue));			
		}
		
		try {
			prExecutor.shutdown();
			prExecutor.awaitTermination(12, TimeUnit.HOURS);
			
			conExecutor.shutdown();
			conExecutor.awaitTermination(12, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		scanner.close();
	}
}
