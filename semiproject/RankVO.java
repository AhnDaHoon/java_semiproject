package semiproject;

public class RankVO {
	String nickname;
	int score;
	
	RankVO(){
		
	}

	public RankVO(String nickname, int score) {
		super();
		this.nickname = nickname;
		this.score = score;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}
