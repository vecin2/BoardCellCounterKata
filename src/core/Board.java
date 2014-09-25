package src.core;

import java.util.ArrayList;
import java.util.List;

public class Board {

	public static Board buildFrom(String currentStringBoard) {
		Board board = new Board(parseStringBoard(currentStringBoard));
		return board;
	}

	private static int[] parseRow(String boardRow) {
		char[] charArrayRow = boardRow.toCharArray();
		int[] results = new int[charArrayRow.length];
		for (int i = 0; i < charArrayRow.length; i++) {
			try {
				results[i] = Integer.parseInt(String.valueOf(charArrayRow[i]));
			} catch (NumberFormatException nfe) {
			}
			;
		}
		return results;
	}

	private static int[][] parseStringBoard(String currentStringBoard) {
		String[] boardRows = currentStringBoard.split("\n");
		int[][] result = new int[boardRows.length][];
		for (int i = 0; i < boardRows.length; i++) {
			result[i] = parseRow(boardRows[i]);
		}
		return result;
	}

	private int[][] arrayBoard;

	public Board(int[][] arrayBoard) {
		this.arrayBoard = arrayBoard;
	}

	public List<Cell> getAllCells() {
		ArrayList<Cell> cellList = new ArrayList<Cell>();
		for (int rowIndex = 0; rowIndex < this.getNumberOfRows(); rowIndex++) {
			cellList.addAll(getRowCells(rowIndex));
		}
		return cellList;
	}

	public Cell getCell(int row, int column) {
		if (isValid(new Position(row, column))) {
			return new Cell(new Position(row, column), getValue(row, column));
		} else {
			return Cell.getNull();
		}
	}
	public int getNumberOfColumns() {
		return arrayBoard[0].length;
	}

	public int getNumberOfRows() {
		return arrayBoard.length;
	}

	private List<Cell> getRowCells(int rowIndex) {
		ArrayList<Cell> cellList = new ArrayList<Cell>();
		int[] row = arrayBoard[rowIndex];
		for (int i = 0; i < row.length; i++) {
			cellList.add(new Cell(new Position(rowIndex, i), row[i]));
		}
		return cellList;
	}

	private int getValue(int i, int j) {
		return arrayBoard[i][j];
	}

	private boolean isValid(Position position) {
		return (position.getRow() >=0 && position.getRow() < getNumberOfRows()) &&
				(position.getColumn()>=0 && position.getColumn() < getNumberOfColumns());
	}
}