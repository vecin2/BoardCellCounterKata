package src.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoardExplorer {
	Board board;
	int[][] visitedCells;
	private Iterator<Cell> pointer;

	public BoardExplorer(Board readBoard) {
		this.board = readBoard;
		this.visitedCells = new int[board.getNumberOfRows()][board.getNumberOfColumns()];
		this.pointer = board.getAllCells().iterator();
	}

	private Cell avancePointer() {
		while (pointer.hasNext()) {
			Cell currentCell = pointer.next();
			if (!hasVisit(currentCell)) {
				visit(currentCell);
				return currentCell;
			}
		}
		return Cell.getNull();
	}

	public ArrayList<Cell> exploreLongestBusySection() {
		ArrayList<Cell> longestSection = new ArrayList<Cell>();
		while (pointer.hasNext()) {
			ArrayList<Cell> currentSection = findNextBusySector();
			if(currentSection.size() > longestSection.size())
				longestSection = currentSection;
		}
		return longestSection;
	}

	private ArrayList<Cell> exploreBusyNeighbours(Cell cell) {
		return getBusy(exploreNeighbours(cell));
	}

	private ArrayList<Cell> exploreBusySectorFrom(Cell cell) {
		visit(cell);
		ArrayList<Cell> section = new ArrayList<Cell>();
		section.add(cell);
		for (int i=0; i < section.size(); i++) {
			section.addAll(exploreBusyNeighbours(section.get(i)));
			
		}
		return section;
	}

	private Boolean exploreCell(Cell cell) {
		if (!hasVisit(cell)) {
			visit(cell);
			return true;			
		}
		return false;
	}

	private ArrayList<Cell> exploreNeighbours(Cell cell) {
		ArrayList<Cell> cellList = new ArrayList<Cell>();
		Cell exploredCell = getTopCell(cell);
		if(exploreCell(exploredCell)){
			cellList.add(getTopCell(cell));
		}
		exploredCell = getRightCell(cell);
		if(exploreCell(exploredCell)){
			cellList.add(exploredCell);
		}
		exploredCell = getBottomCell(cell);
		if(exploreCell(exploredCell)){
			cellList.add(exploredCell);
		}
		exploredCell = getLeftCell(cell);
		if(exploreCell(exploredCell)){
			cellList.add(exploredCell);
		}
		
		return cellList;
	}

	public ArrayList<Cell> findNextBusySector() {
		while (pointer.hasNext()) {
			Cell cell = avancePointer();
			if (cell.isBusy()) {
				return exploreBusySectorFrom(cell);
			}
		}
		return new ArrayList<Cell>();
	}

	private Cell getBottomCell(Cell cell) {
		return board.getCell(cell.getRow() + 1, cell.getColumn());
	}

	private ArrayList<Cell> getBusy(ArrayList<Cell> cells) {
		Iterator<Cell> cellsIterator = cells.iterator();
		while (cellsIterator.hasNext()) {
			Cell cell = cellsIterator.next();
			if (!cell.isBusy()) {
				cellsIterator.remove();
			}
		}
		return cells;
	}

	private Cell getLeftCell(Cell cell) {
		return board.getCell(cell.getRow(), cell.getColumn() - 1);
	}

	private Cell getRightCell(Cell cell) {
		return board.getCell(cell.getRow(), cell.getColumn() + 1);
	}

	private Cell getTopCell(Cell cell) {
		return board.getCell(cell.getRow() - 1, cell.getColumn());
	}

	private boolean hasVisit(Cell cell) {
		if (cell.isNull())
			return true;
		else
			return visitedCells[cell.getRow()][cell.getColumn()] == 1;
	}

	private void visit(Cell cell) {
		visitedCells[cell.getRow()][cell.getColumn()] = 1;

	}

}
