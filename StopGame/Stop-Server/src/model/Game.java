package model;

import java.util.Random;
import com.google.gson.Gson;
import communication.Session;

public class Game {

    private User[] users;
    private User win;
    public String type = "Game";
    public Session sesionUno;
    public Session sesionDos;

    public Game(Session sesionUno, Session sesionDos) {

        users = new User[2];
        this.sesionUno = sesionUno;
        this.sesionDos = sesionDos;
        initGame();
    }

    public void initGame() {

        char c = randomLetter();
        Message msg = new Message(Character.toString(c));
        Gson gson = new Gson();
        String j = gson.toJson(msg);

        sesionUno.getEmisor().sendMessage(j);

        sesionDos.getEmisor().sendMessage(j);

        new Thread(() -> {

            String a = sesionUno.getReceptor().readMessage();

            sesionDos.getEmisor().sendMessage(a);
            String b = sesionDos.getReceptor().readMessage();
            sesionUno.getEmisor().sendMessage(b);
            sesionDos.getEmisor().sendMessage("");

            sesionUno.getReceptor().readMessage();
            sesionDos.getReceptor().readMessage();

        }).start();

        new Thread(() -> {

            String b = sesionDos.getReceptor().readMessage();
            sesionUno.getEmisor().sendMessage(b);
            String a = sesionUno.getReceptor().readMessage();
            sesionDos.getEmisor().sendMessage(a);
            sesionDos.getReceptor().readMessage();

        }).start();
    }

    public User[] getUsers() {
        return this.users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public User getWin() {
        return this.win;
    }

    public void setWin(User win) {
        this.win = win;
    }

    public boolean isFull() {
        if (users[0] != null && users[1] != null) {
            return true;
        } else {
            return false;
        }
    }

    public char randomLetter() {
        Random random = new Random();

        String setOfCharacters = "abcdefghijklmnopqrstuvwxyz";

        int randomInt = random.nextInt(setOfCharacters.length());
        char randomChar = setOfCharacters.charAt(randomInt);

        return randomChar;

    }

}
