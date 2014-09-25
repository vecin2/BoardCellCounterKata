package src.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardReader {
	Scanner scanner = new Scanner(System.in);
	String currentStringBoard = "";
	
	public List<Board> readMultipleBoards(){
		ArrayList<Board> boardList = new ArrayList<Board>();
		System.out.println("Enter number of games: ");
		int numberOfGames = Integer.parseInt(scanner.nextLine());
		for(int i=0; i < numberOfGames; i++){
			boardList.add(readBoard());
		}
		return boardList;
	}

	private Board readBoard() {
		System.out.println("Enter Board:");

		String boardRow = scanner.nextLine();		
		while (!boardRow.isEmpty()) {
			currentStringBoard += boardRow;
			currentStringBoard += "\n";
			boardRow = scanner.nextLine();
		}
		System.out.println(currentStringBoard);
		return Board.buildFrom(currentStringBoard);
	}

}
