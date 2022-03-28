package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Ventana1Controller {

    @FXML
    private Label title;

    @FXML
    private Button stopBtn;

    @FXML
    private TextField nameAnswer;

    @FXML
    private TextField animalAnswer;

    @FXML
    private TextField locationAnswer;

    @FXML
    private TextField objectAnswer;

    @FXML
    private Label letraLabel;

    public boolean gameValidation(String name, String animal, String location, String object) {
        boolean complete = true;
        if (name.equals("") || animal.equals("") || location.equals("") || object.equals("")) {
            complete = false;
        }
        return complete;
    }

    public void trimGame() {
        nameAnswer.setText("");
        animalAnswer.setText("");
        locationAnswer.setText("");
        objectAnswer.setText("");
    }

    public void showResults() throws IOException, ClassNotFoundException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Ventana2.fxml"));
        Parent root = fxmlloader.load();
        Scene scene = new Scene(root);

    }

}
