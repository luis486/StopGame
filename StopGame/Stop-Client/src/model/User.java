package model;

public class User {

    public String type = "User";

    int amountPoint;
    boolean finish;

    public User(int amountPoint, boolean finish) {
        this.amountPoint = amountPoint;
        this.finish = finish;
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
