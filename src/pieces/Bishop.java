package pieces;

import java.util.ArrayList;

import game.Piece;
import game.Position;

public class Bishop extends Piece {

	public Bishop(int team) {
		super(team);
		super.setType("bishop");
	}

	@Override
	public void initMovePattern() {
		ArrayList<Position> movePattern = new ArrayList<>();

		for (int i = 1; i < 8; i++) {
			movePattern.add(new Position(-i, -i));
			movePattern.add(new Position(i, -i));
			movePattern.add(new Position(-i, i));
			movePattern.add(new Position(i, i));
		}

		super.setMovePattern(movePattern);
	}

}
