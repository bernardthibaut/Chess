package pieces;

import java.util.ArrayList;

import game.Piece;
import game.Position;

public class Rook extends Piece {

	public Rook(int team) {
		super(team);
		super.setType("rook");
	}

	@Override
	public void initMovePattern() {
		ArrayList<Position> movePattern = new ArrayList<>();

		for (int i = 1; i < 8; i++) {
			movePattern.add(new Position(-i, 0));
			movePattern.add(new Position(i, 0));
			movePattern.add(new Position(0, i));
			movePattern.add(new Position(0, -i));
		}

		super.setMovePattern(movePattern);
	}

}
