package controller;

import com.google.gson.Gson;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.Game;
import model.Message;

public class Ventana2Controller {

    @FXML
    private Label ownNameResult;

    @FXML
    private Label opponentNameResult;

    @FXML
    private Label myAnimalResult;

    @FXML
    private Label opponentAnimalResult;

    @FXML
    private Label ownLocationResult;

    @FXML
    private Label opponentLocationResult;

    @FXML
    private Label ownObjectResult;

    @FXML
    private Label opponentObjectResult;

    @FXML
    private Button finishBtn;

    public MainController mc;
    public Game game;

    @FXML
    public void finishGame(MouseEvent event) {

        Message m = new Message("leave");

        Gson gson = new Gson();

        String msg = gson.toJson(m);

    }

}
