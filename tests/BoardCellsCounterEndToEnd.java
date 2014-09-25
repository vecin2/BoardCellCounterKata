package tests;

import java.io.IOException;

import org.junit.Test;

import tests.utils.ApplicationRunner;

public class BoardCellsCounterEndToEnd {

	@Test
	public void test() throws IOException, InterruptedException {
		ApplicationRunner appRunner = new ApplicationRunner();
		appRunner.userEnters("1");
		appRunner.userEnters("1011");
		appRunner.userEnters("1010");
		appRunner.endPopulatingBoard();
		appRunner.hasPrintedARow("3");
	}

}
