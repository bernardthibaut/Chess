package game;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import pieces.*;

@SuppressWarnings("serial")
public class Board extends JPanel {

	private Square[][] grid;

	private HashMap<String, ImageIcon> piecesImages;

	private Position squareSelected;

	public Board() {
		setLayout(new GridLayout(8, 8));

		initGrid();
		loadImages();
		updateGrid();
		addListener();
	}

	public void addListener() {
		for (int line = 0; line < 8; line++) {
			for (int column = 0; column < 8; column++) {
				final int l = line;
				final int c = column;

				grid[l][c].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						colorGrid();

						if (squareSelected != null) {
							Piece pieceSelected = grid[squareSelected.getLine()][squareSelected.getColumn()].getValue();
							for (Position p : pieceSelected.getPossibleMoves()) {
								if (l == p.getLine() && c == p.getColumn()) {
									grid[l][c].setValue(pieceSelected);

									Square tmp = new Square();
									grid[squareSelected.getLine()][squareSelected.getColumn()] = tmp;
									updateGrid();
									addButtons();
								}
							}
							squareSelected = null;
						} else {
							squareSelected = null;
							if (!grid[l][c].isEmpty()) {
								squareSelected = new Position(l, c);
								grid[l][c].getValue().updatePossibleMoves(l, c, grid);
								grid[l][c].setBackground(new Color(63, 132, 7));
								for (Position p : grid[l][c].getValue().getPossibleMoves()) {
									if (isDarkSquare(p.getLine(), p.getColumn()))
										grid[p.getLine()][p.getColumn()].setBackground(new Color(143, 212, 87));
									else
										grid[p.getLine()][p.getColumn()].setBackground(new Color(153, 222, 97));
								}
							}
						}
					}
				});
			}
		}
	}

	public void updateGrid() {
		for (int line = 0; line < 8; line++) {
			for (int column = 0; column < 8; column++) {
				if (grid[line][column].getValue() != null) {
					if (grid[line][column].getValue().getTeam() == 0)
						grid[line][column].setIcon(piecesImages.get("0" + grid[line][column].getValue().getType()));
					else
						grid[line][column].setIcon(piecesImages.get("1" + grid[line][column].getValue().getType()));
				} else {
					grid[line][column].setIcon(null);
				}
			}
		}
	}

	private boolean isDarkSquare(int line, int column) {
		return (line % 2 != column % 2);
	}

	private void colorGrid() {
		for (int line = 0; line < 8; line++) {
			for (int column = 0; column < 8; column++) {
				if (isDarkSquare(line, column))
					grid[line][column].setBackground(new Color(141, 85, 36));
				else
					grid[line][column].setBackground(new Color(255, 219, 172));
			}
		}
	}

	public void addButtons() {
		removeAll();

		for (int line = 0; line < 8; line++) {
			for (int column = 0; column < 8; column++) {
				add(grid[line][column]);
			}
		}

		colorGrid();
	}

	public void initGrid() {
		grid = new Square[8][8];

		for (int line = 0; line < 8; line++) {
			for (int column = 0; column < 8; column++) {
				Square tmp = new Square();
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

		addButtons();
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
