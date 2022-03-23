package model;

import java.util.Random;

public class Game {

    public char letter;
    public User[] users;
    User win;

    public Game() {
        letter = randomLetter();
        users = new User[2];
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

    public static char randomLetter() {
        Random random = new Random();

        String setOfCharacters = "abcdefghijklmnopqrstuvwxyz";

        int randomInt = random.nextInt(setOfCharacters.length());
        char randomChar = setOfCharacters.charAt(randomInt);

        return randomChar;

    }

}
