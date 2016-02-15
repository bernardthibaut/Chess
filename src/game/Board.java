package game;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import pieces.*;

@SuppressWarnings("serial")
public class Board extends JPanel {

	private Square[][] grid;

	private HashMap<String, ImageIcon> piecesImages;

	public Board() {
		setLayout(new GridLayout(8, 8));

		initGrid();
		loadImages();
		updateGrid();
	}

	public void updateGrid() {
		for (int line = 0; line < 8; line++) {
			for (int column = 0; column < 8; column++) {
				if (grid[line][column].getValue() != null) {
					if (grid[line][column].getValue().getTeam() == 0)
						grid[line][column].setIcon(piecesImages.get("0" + grid[line][column].getValue().getType()));
					else
						grid[line][column].setIcon(piecesImages.get("1" + grid[line][column].getValue().getType()));
				}
			}
		}
	}

	private boolean isDarkSquare(int line, int column) {
		return (line % 2 != column % 2);
	}

	public void initGrid() {
		grid = new Square[8][8];

		for (int line = 0; line < 8; line++) {
			for (int column = 0; column < 8; column++) {
				Square tmp = new Square();
				tmp.setFocusable(false);
				if (isDarkSquare(line, column))
					tmp.setBackground(new Color(141, 85, 36));
				else
					tmp.setBackground(new Color(255, 219, 172));
				grid[line][column] = tmp;
			}
		}

		for (int line = 0; line < 8; line++) {
			for (int column = 0; column < 8; column++) {
				if (line == 0) {
					if (column == 0 || column == 7)
						grid[line][column].setValue(new Rook(0));
					else if (column == 1 || column == 6)
						grid[line][column].setValue(new Knight(0));
					else if (column == 2 || column == 5)
						grid[line][column].setValue(new Bishop(0));
					else if (column == 3)
						grid[line][column].setValue(new Queen(0));
					else if (column == 4)
						grid[line][column].setValue(new King(0));
				} else if (line == 1)
					grid[line][column].setValue(new Pawn(0));
				else if (line == 6)
					grid[line][column].setValue(new Pawn(1));
				else if (line == 7) {
					if (column == 0 || column == 7)
						grid[line][column].setValue(new Rook(1));
					else if (column == 1 || column == 6)
						grid[line][column].setValue(new Knight(1));
					else if (column == 2 || column == 5)
						grid[line][column].setValue(new Bishop(1));
					else if (column == 3)
						grid[line][column].setValue(new Queen(1));
					else if (column == 4)
						grid[line][column].setValue(new King(1));
				}
			}
		}

		removeAll();

		for (int line = 0; line < 8; line++) {
			for (int column = 0; column < 8; column++) {
				add(grid[line][column]);
			}
		}
	}

	public void loadImages() {
		piecesImages = new HashMap<String, ImageIcon>();

		File[] files = new File("res/black/").listFiles();

		for (File file : files) {
			if (file.isFile()) {
				piecesImages.put("0" + nameWithoutType(file.getName()), new ImageIcon("res/black/" + file.getName()));
			}
		}

		files = new File("res/white/").listFiles();

		for (File file : files) {
			if (file.isFile()) {
				piecesImages.put("1" + nameWithoutType(file.getName()), new ImageIcon("res/white/" + file.getName()));
			}
		}
	}

	private String nameWithoutType(String name) {
		String res = "";
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) == '.')
				return res;
			res += name.charAt(i);
		}
		return res;
	}

	public HashMap<String, ImageIcon> getPiecesImages() {
		return piecesImages;
	}

	public void setPiecesImages(HashMap<String, ImageIcon> piecesImages) {
		this.piecesImages = piecesImages;
	}

}
