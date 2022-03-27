package model;

import java.util.Random;

import communication.Session;

public class Game {

    private char letter;
    private User[] users;
    private User win;
    public String type = "Game";
    public Session sesionUno;
    public Session sesionDos;

    public Game(Session sesionUno, Session sesiondos) {
        letter = randomLetter();
        users = new User[2];
        this.sesionUno = sesionUno;

    }

    public char getLetter() {
        return this.letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
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
