package src.core;

import java.util.List;

public class MainBoardCellsCounter {

	public MainBoardCellsCounter() {

	}

	static public void main(String[] args) {

		BoardReader boardReader = new BoardReader();
		List<Board> boardList = boardReader.readMultipleBoards();
		for (Board board : boardList) {
			BoardExplorer boardExplorer = new BoardExplorer(board);
			System.out.println(boardExplorer.exploreLongestBusySection().size());
		}
	}

}
