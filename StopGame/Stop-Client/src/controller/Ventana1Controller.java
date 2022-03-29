package controller;

import java.io.IOException;

import com.google.gson.Gson;

import events.OnSendMessage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Response;

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
    public Label letraLabel;

    public OnSendMessage osm;

    public OnSendMessage getOsm() {
        return this.osm;
    }

    public void setOsm(OnSendMessage osm) {
        this.osm = osm;
    }

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

    @FXML
    public void stopGame(MouseEvent event) {
        boolean validate = gameValidation(nameAnswer.getText(), animalAnswer.getText(), locationAnswer.getText(),
                objectAnswer.getText());
        if (validate) {
            Response response = new Response(nameAnswer.getText(), animalAnswer.getText(), locationAnswer.getText(),
                    objectAnswer.getText());
            Gson gson = new Gson();
            String respon = gson.toJson(response);
            osm.onSendMessage(respon);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("STOP");
            alert.setHeaderText("ERROR");
            alert.setContentText("Por favor completa todos los campos!");
            alert.showAndWait();

        }

    }

}
