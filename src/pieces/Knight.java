package pieces;

import java.util.ArrayList;

import game.Piece;
import game.Position;

public class Knight extends Piece {

	public Knight(int team) {
		super(team);
		super.setType("knight");
	}

	@Override
	public void initMovePattern() {
		ArrayList<Position> movePattern = new ArrayList<>();

		movePattern.add(new Position(-1, -2));
		movePattern.add(new Position(-2, -1));
		movePattern.add(new Position(-2, 1));
		movePattern.add(new Position(-1, 2));
		movePattern.add(new Position(1, -2));
		movePattern.add(new Position(2, -1));
		movePattern.add(new Position(2, 1));
		movePattern.add(new Position(1, 2));
		
		super.setMovePattern(movePattern);
	}

}
