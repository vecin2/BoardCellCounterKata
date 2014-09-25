package src.core;

public class Position {
	int row;
	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
	int column;

	public Position(int x, int y) {
		this.row = x;
		this.column = y;
	}

	public static Position getNull() {
		return new Position(-1,-1);
	}
	public Boolean isNull(){
		return this.equals(getNull());
	}
	public Boolean equals(Position toCompare){
		return (this.getColumn()== toCompare.getColumn()) &&
				(this.getRow()== toCompare.getRow());
	}
}
