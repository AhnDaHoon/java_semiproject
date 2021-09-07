package semiproject;

public class SmemberVO {
	String id;
	String pw;
	String email;
	String name;
	String no;
	String nickname;

	SmemberVO(){
		
	}

	public SmemberVO(String id, String pw, String email, String name, String no, String nickname) {
		super();
		this.id = id;
		this.pw = pw;
		this.email = email;
		this.name = name;
		this.no = no;
		this.nickname = nickname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	
	
	
}