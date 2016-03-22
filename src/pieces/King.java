package pieces;

import java.util.ArrayList;

import game.Piece;
import game.Position;

public class King extends Piece {

	public King(int team) {
		super(team);
		super.setType("king");
	}

	@Override
	public void initMovePattern() {
		ArrayList<Position> movePattern = new ArrayList<>();

		movePattern.add(new Position(-1, -1));
		movePattern.add(new Position(-1, 0));
		movePattern.add(new Position(-1, 1));
		movePattern.add(new Position(0, -1));
		movePattern.add(new Position(0, 1));
		movePattern.add(new Position(1, -1));
		movePattern.add(new Position(1, 0));
		movePattern.add(new Position(1, 1));

		super.setMovePattern(movePattern);
	}

}
