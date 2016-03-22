package pieces;

import java.util.ArrayList;

import game.Piece;
import game.Position;

public class Pawn extends Piece {

	public Pawn(int team) {
		super(team);
		super.setType("pawn");
	}

	@Override
	public void initMovePattern() {
		ArrayList<Position> movePattern = new ArrayList<>();
		if (super.getTeam() == 0) {
			movePattern.add(new Position(1, 0));
		} else {
			movePattern.add(new Position(-1, 0));
		}

		super.setMovePattern(movePattern);
	}

}
