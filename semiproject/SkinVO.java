package semiproject;

public class SkinVO {
	String ID;
	String skin;

	SkinVO() {
		
	}
	
	public SkinVO(String iD, String skin) {
		this.ID = iD;
		this.skin = skin;
	}

	
	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
	}
	
	public String getSkin() {
		return skin;
	}
	
	public void setSkin(String skin) {
		this.skin = skin;
	}
}
