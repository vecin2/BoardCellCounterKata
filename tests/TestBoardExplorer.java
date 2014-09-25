package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import src.core.Board;
import src.core.BoardExplorer;
import src.core.Cell;

public class TestBoardExplorer {

	private Board board;
	private BoardExplorer boardExplorer;

	@Test
	public void testFindNextBusySector() {
		String stringBoard = "1011\n1010";
		initBoardExplorer(stringBoard);
		ArrayList<Cell> expectedSector = new ArrayList<Cell>();
		expectedSector.add(board.getCell(0, 0));
		expectedSector.add(board.getCell(1, 0));
		
		ArrayList<Cell> actualSector = boardExplorer.findNextBusySector();
		assertEqualSector(expectedSector, actualSector); 
		
		expectedSector.clear();
		expectedSector.add(board.getCell(0, 2));
		expectedSector.add(board.getCell(0, 3));
		expectedSector.add(board.getCell(1, 2));
		actualSector = boardExplorer.findNextBusySector();
		assertEqualSector(expectedSector, actualSector); 
	}
	@Test
	public void testExploreLongestBusySection() {
		String stringBoard = "1011\n1010";
		initBoardExplorer(stringBoard);
		
		ArrayList<Cell> actualSector = boardExplorer.exploreLongestBusySection();
		
		assertEquals(3, actualSector.size()); 
	}

	private void assertEqualSector(ArrayList<Cell> expectedSector, ArrayList<Cell> actualSector) {
		assertEquals(expectedSector.size(), actualSector.size());
		assertTrue("Some cells were visited but not expected",
				expectedSector.get(0).equals(actualSector.get(0)) &&
				expectedSector.get(1).equals(actualSector.get(1)));
	}
	
	private void initBoardExplorer(String stringBoard) {
		board = Board.buildFrom(stringBoard);
		boardExplorer = new BoardExplorer(board);
	}

}
