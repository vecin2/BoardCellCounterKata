package src.core;

public class Cell {
	int value;
	Position position;

	public Cell(Position position, int value) {
		this.position = position;
		this.value = value;
	}

	public boolean isBusy() {
		return getValue() == 1;
	}

	public int getValue() {
		return value;
	}

	public static Cell getNull() {
		return new Cell(Position.getNull(), -1);
	}

	public Boolean isNull() {
		return this.position.isNull();
	}

	public int getRow() {
		return getPosition().getRow();
	}

	public int getColumn() {
		return getPosition().getColumn();
	}

	private Position getPosition() {
		return this.position;
	}

	public Boolean equals(Cell toCompare) {
		return (this.position.equals(toCompare.getPosition())) && 
				(this.getValue() == toCompare.getValue());
	}
	public String toString(){
		return this.getRow()+", " +
                this.getColumn()+", "+
				  this.getValue();
	}

	public static Cell buildFrom(int row, int column, int value) {
		return new Cell(new Position(row, column), value);
	}
}
