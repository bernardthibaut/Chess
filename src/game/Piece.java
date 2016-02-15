package game;

public abstract class Piece {

	private int team;
	private String type;

	public Piece(int team) {
		this.team = team;
	}

	public int getTeam() {
		return team;
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
