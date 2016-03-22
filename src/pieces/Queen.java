package pieces;

import java.util.ArrayList;

import game.Piece;
import game.Position;

public class Queen extends Piece {

	public Queen(int team) {
		super(team);
		super.setType("queen");
	}

	@Override
	public void initMovePattern() {
		ArrayList<Position> movePattern = new ArrayList<>();

		for (int i = 1; i < 8; i++) {
			movePattern.add(new Position(-i, 0));
			movePattern.add(new Position(i, 0));
			movePattern.add(new Position(0, i));
			movePattern.add(new Position(0, -i));
			movePattern.add(new Position(-i, -i));
			movePattern.add(new Position(i, -i));
			movePattern.add(new Position(-i, i));
			movePattern.add(new Position(i, i));
		}

		super.setMovePattern(movePattern);
	}

}
