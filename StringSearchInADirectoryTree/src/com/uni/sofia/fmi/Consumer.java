package com.uni.sofia.fmi;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

	private String input;
	private BlockingQueue<FileLineCount> queue;

	public Consumer(String input, BlockingQueue<FileLineCount> queue) {
		this.input = input;
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {

			FileLineCount flc;
			try {
				flc = queue.take();

				if (flc.getLineCount() == -1) {
					break;
				}

				if (flc.getLine().contains(input)) {
					System.out.println(flc.toString());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
