package game;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Square extends JButton {

	private Piece value;

	public Square() {
		super();
		setFocusable(false);
	}

	public Piece getValue() {
		return value;
	}

	public void setValue(Piece value) {
		this.value = value;
	}

	public boolean isEmpty() {
		return value == null;
	}
}
