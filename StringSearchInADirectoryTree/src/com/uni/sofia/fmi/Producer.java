package com.uni.sofia.fmi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

	private File file;
	private BlockingQueue<FileLineCount> queue;

	public Producer(File file, BlockingQueue<FileLineCount> queue) {
		this.file = file;
		this.queue = queue;
	}

	@Override
	public void run() {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			int counter = 1;

			while ((line = br.readLine()) != null) {
				FileLineCount flc = new FileLineCount(file.getName(), line, counter);
				queue.put(flc);
				counter++;
			}
			queue.put(new FileLineCount("", "", -1));
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
