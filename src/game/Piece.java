package game;

import java.util.ArrayList;

import pieces.*;

public abstract class Piece {

	private int team;
	private String type;

	private ArrayList<Position> movePattern;
	private ArrayList<Position> possibleMoves;

	public Piece(int team) {
		this.team = team;

		initMovePattern();
	}

	public void updatePossibleMoves(int currentLine, int currentCol, Square[][] grid) {
		possibleMoves = new ArrayList<>();
		for (Position p : movePattern) {
			int line = currentLine + p.getLine();
			int column = currentCol + p.getColumn();
			if (isOnBoard(line, column) && grid[line][column].isEmpty())
				possibleMoves.add(new Position(line, column));
		}
	}

	private boolean isOnBoard(int line, int column) {
		return (line >= 0 && column >= 0 && line <= 7 && column <= 7);
	}

	public int getTeam() {
		return team;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<Position> getPossibleMoves() {
		return possibleMoves;
	}

	public ArrayList<Position> getMovePattern() {
		return movePattern;
	}

	public void setMovePattern(ArrayList<Position> movePattern) {
		this.movePattern = movePattern;
	}

	public abstract void initMovePattern();

}
