package model;

public class User {

	private int amountPoint;
	private boolean finish;
	private String id;
	public String type = "User";

	public User(int amountPoint, boolean finish) {
		this.amountPoint = amountPoint;
		this.finish = finish;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAmountPoint() {
		return this.amountPoint;
	}

	public void setAmountPoint(int amountPoint) {
		this.amountPoint = amountPoint;
	}

	public boolean isFinish() {
		return this.finish;
	}

	public boolean getFinish() {
		return this.finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

}