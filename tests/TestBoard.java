package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import src.core.Board;
import src.core.Cell;

public class TestBoard {

	@Test
	public void testBuildFromCreatesACorrectBoard() {
		String stringBoard = "1011\n1010";
		Board board = Board.buildFrom(stringBoard);
		
		assertEquals("Position 0,0: ",Cell.buildFrom(0,0,1).toString(), board.getCell(0,0).toString());
		assertEquals("Position 0,1: ",Cell.buildFrom(0,1,0).toString(), board.getCell(0,1).toString());
		assertEquals("Position 0,2: ",Cell.buildFrom(0,2,1).toString(), board.getCell(0,2).toString());
		assertEquals("Position 0,3: ",Cell.buildFrom(0,3,1).toString(), board.getCell(0,3).toString());
		assertEquals("Position 1,0: ",Cell.buildFrom(1,0,1).toString(), board.getCell(1,0).toString());
		assertEquals("Position 1,1: ",Cell.buildFrom(1,1,0).toString(), board.getCell(1,1).toString());
		assertEquals("Position 1,2: ",Cell.buildFrom(1,2,1).toString(), board.getCell(1,2).toString());
		assertEquals("Position 1,3: ",Cell.buildFrom(1,3,0).toString(), board.getCell(1,3).toString());
	}

}
