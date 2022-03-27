package model;

public class Message {

    public String type = "Message";
    public String message;

    public Message() {
        message = "";
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
