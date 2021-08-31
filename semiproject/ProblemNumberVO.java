package semiproject;

public class ProblemNumberVO {
	private String numone;
	private String numtwo;
	private String numthree;
	private String numfour;
	private int answerNo;
	
	ProblemNumberVO() {
		
	}
	
	
	public ProblemNumberVO(String numone, String numtwo, String numthree, String numfour, int answerNo) {
		this.numone = numone;
		this.numtwo = numtwo;
		this.numthree = numthree;
		this.numfour = numfour;
		this.answerNo = answerNo;
	}
	
	
	
	public String getNumone() {
		return numone;
	}


	public void setNumone(String numone) {
		this.numone = numone;
	}


	public String getNumtwo() {
		return numtwo;
	}


	public void setNumtwo(String numtwo) {
		this.numtwo = numtwo;
	}


	public String getNumthree() {
		return numthree;
	}


	public void setNumthree(String numthree) {
		this.numthree = numthree;
	}


	public String getNumfour() {
		return numfour;
	}


	public void setNumfour(String numfour) {
		this.numfour = numfour;
	}


	public int getAnswerNo() {
		return answerNo;
	}


	public void setAnswerNo(int answerNo) {
		this.answerNo = answerNo;
	}


	
	

}
