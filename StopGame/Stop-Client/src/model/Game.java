package model;

import java.util.Random;

public class Game {

    public String type = "Game";

    public char letter;

    private Response myResponse;

    private Response yourResponse;

    private int myPoints;

    private int yourPoints;

    public Game(char letter) {
        this.letter = randomLetter();

    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public char getLetter() {
        return this.letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public Response getMyResponse() {
        return this.myResponse;
    }

    public void setMyResponse(Response myResponse) {
        this.myResponse = myResponse;
    }

    public Response getYourResponse() {
        return this.yourResponse;
    }

    public void setYourResponse(Response yourResponse) {
        this.yourResponse = yourResponse;
    }

    public int getMyPoints() {
        return this.myPoints;
    }

    public void setMyPoints(int myPoints) {
        this.myPoints = myPoints;
    }

    public int getYourPoints() {
        return this.yourPoints;
    }

    public void setYourPoints(int yourPoints) {
        this.yourPoints = yourPoints;
    }

    public void calculatePoints() {

        String myName = myResponse.getName();
        String yourName = yourResponse.getName();

        if (myName.isEmpty() || yourName.isEmpty()) {
            if (myName.isEmpty() && yourName.isEmpty() == false) {
                yourPoints += 100;
            } else if (myName.isEmpty() == false && yourName.isEmpty()) {
                myPoints += 100;
            } else {
            }

        } else if (myName.equals(yourName)) {
            yourPoints += 50;
            myPoints += 50;
        } else {
            yourPoints += 100;
            myPoints += 100;

        }
        String myAnimal = myResponse.getAnimal();
        String yourAnimal = yourResponse.getAnimal();

        if (myAnimal.isEmpty() || yourAnimal.isEmpty()) {
            if (myAnimal.isEmpty() && yourAnimal.isEmpty() == false) {
                yourPoints += 100;
            } else if (myAnimal.isEmpty() == false && yourAnimal.isEmpty()) {
                myPoints += 100;
            } else {
            }

        } else if (myAnimal.equals(yourAnimal)) {
            yourPoints += 50;
            myPoints += 50;
        } else {
            yourPoints += 100;
            myPoints += 100;
        }
        String myCity = myResponse.getLocation();
        String yourCity = yourResponse.getLocation();

        if (myCity.isEmpty() || yourCity.isEmpty()) {
            if (myCity.isEmpty() && yourCity.isEmpty() == false) {
                yourPoints += 100;
            } else if (myCity.isEmpty() == false && yourCity.isEmpty()) {
                myPoints += 100;
            } else {
            }
        } else if (myCity.equals(yourCity)) {

            yourPoints += 50;
            myPoints += 50;
        } else {
            yourPoints += 100;
            myPoints += 100;

        }
        String myObject = myResponse.getObject();
        String yourObject = yourResponse.getObject();

        if (myObject.isEmpty() || yourObject.isEmpty()) {
            if (myObject.isEmpty() && yourObject.isEmpty() == false) {
                yourPoints += 100;
            } else if (myObject.isEmpty() == false && yourObject.isEmpty()) {
                myPoints += 100;
            } else {
            }

        } else if (myObject.equals(yourObject)) {
            yourPoints += 50;
            myPoints += 50;
        } else {
            yourPoints += 100;
            myPoints += 100;

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
