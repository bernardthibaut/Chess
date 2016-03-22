package tests;

import game.*;
import pieces.*;

public class Test {

	public Test() {
		Square[][] grid = new Square[8][8];
		Pawn p = new Pawn(1);

		p.updatePossibleMoves(3, 6, grid);

		for (Position pos : p.getPossibleMoves()) {
			System.out.println(pos);
		}
	}

	public static void main(String[] args) {
		new Test();
	}

}
