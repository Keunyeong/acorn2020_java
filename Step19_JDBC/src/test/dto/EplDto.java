package test.dto;

public class EplDto {
	private int rank;
	private String team;
	private int point;
	
	public EplDto() {}

	public EplDto(int rank, String team, int point) {
		super();
		this.rank = rank;
		this.team = team;
		this.point = point;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	};
	
	
	
}
