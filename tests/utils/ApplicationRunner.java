package tests.utils;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import src.core.MainBoardCellsCounter;

public class ApplicationRunner {
	ByteArrayOutputStream outputStream;
	PrintStream outprintStream;
	PipedInputStream inputStream;
	PipedOutputStream pipedOutPutSteam;

	MainBoardCellsCounter main;

	public ApplicationRunner() throws IOException {
		outputStream = new ByteArrayOutputStream();
		outprintStream = new PrintStream(outputStream);
		inputStream = new PipedInputStream();
		pipedOutPutSteam = new PipedOutputStream(inputStream);

		System.setOut(outprintStream);
		System.setIn(inputStream);
		Thread thread = new Thread("Test Application") {
			@Override
			public void run() {
				try {
					MainBoardCellsCounter.main(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		thread.setDaemon(true);
		thread.start();
	}

	public void enterNumberOfCasesFollowing(String numberOfCases) throws IOException {
		userEnters(numberOfCases);
	}

	public void startPopulatingBoard() throws IOException {
		userEnters("");
	}

	public void userEnters(String string) throws IOException {
		pipedOutPutSteam.write((string+"\n").getBytes());
	}

	public void enterBoardRow(String row) throws IOException {
		userEnters(row);
	}

	public void endPopulatingBoard() throws IOException {
		userEnters("");
	}

	public void hasPrintedRow(int row, String textPrinted ) throws InterruptedException {

		Boolean condition = false;
		int trial = 0;
		while (trial <= 20 && !condition) {
			Thread.sleep(100);
			String[] outputByRows = outputStream.toString().split("\n");		
			condition = textPrinted==outputByRows[row];
			trial++;
		}
		if (!condition) {
			throw new AssertionError("Text '" + textPrinted
					+ "' has not been displayed.");
		}
		
	}
	public void hasPrintedARow(String textPrinted) throws InterruptedException {
		Boolean condition = false;
		int trial = 0;
		while (trial <= 20 && !condition) {
			Thread.sleep(100);
			String[] outputByRows = outputStream.toString().split("\n");		
			condition = Arrays.asList(outputByRows).contains(textPrinted);
			trial++;
		}
		if (!condition) {
			throw new AssertionError("'"+outputStream.toString()+"' does not contain '"+ textPrinted + "'");
		}
	}
	
	
}
