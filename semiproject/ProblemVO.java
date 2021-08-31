package semiproject;

public class ProblemVO {
	private String problem;
	private String answer;
	private String explanations;
	
	ProblemVO() {
		
	}
	
	
	public ProblemVO(String problem, String answer, String explanations) {
		this.problem = problem;
		this.answer = answer;
		this.explanations = explanations;
	}
	
	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getExplanations() {
		return explanations;
	}

	public void setExplanations(String explanations) {
		this.explanations = explanations;
	}



}
