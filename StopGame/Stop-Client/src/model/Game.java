package model;

import java.util.Random;

public class Game {

    public String type = "Game";

    public char letter;

    private Response myResponse;

    private Response yourResponse;

    private int myPoints;

    private int yourPoints;

    private int myNamePoints;

    private int myAnimalPoints;

    private int myLocationPoints;

    private int myObjectPoint;

    private int yourNamePoints;

    private int yourAnimalPoints;

    private int yourLocationPoints;

    private int yourObjectPoints;

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

    public int getMyNamePoints() {
        return this.myNamePoints;
    }

    public void setMyNamePoints(int myNamePoints) {
        this.myNamePoints = myNamePoints;
    }

    public int getMyAnimalPoints() {
        return this.myAnimalPoints;
    }

    public void setMyAnimalPoints(int myAnimalPoints) {
        this.myAnimalPoints = myAnimalPoints;
    }

    public int getMyLocationPoints() {
        return this.myLocationPoints;
    }

    public void setMyLocationPoints(int myLocationPoints) {
        this.myLocationPoints = myLocationPoints;
    }

    public int getMyObjectPoint() {
        return this.myObjectPoint;
    }

    public void setMyObjectPoint(int myObjectPoint) {
        this.myObjectPoint = myObjectPoint;
    }

    public int getYourNamePoints() {
        return this.yourNamePoints;
    }

    public void setYourNamePoints(int yourNamePoints) {
        this.yourNamePoints = yourNamePoints;
    }

    public int getYourAnimalPoints() {
        return this.yourAnimalPoints;
    }

    public void setYourAnimalPoints(int yourAnimalPoints) {
        this.yourAnimalPoints = yourAnimalPoints;
    }

    public int getYourLocationPoints() {
        return this.yourLocationPoints;
    }

    public void setYourLocationPoints(int yourLocationPoints) {
        this.yourLocationPoints = yourLocationPoints;
    }

    public int getYourObjectPoints() {
        return this.yourObjectPoints;
    }

    public void setYourObjectPoints(int yourObjectPoints) {
        this.yourObjectPoints = yourObjectPoints;
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
