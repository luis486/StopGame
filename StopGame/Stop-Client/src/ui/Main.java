package ui;

import java.io.IOException;

import com.google.gson.Gson;

import communication.Session;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import model.Generic;
import javafx.scene.*;

public class Main extends Application {

    public static Stage stage;

    public static Session session;

    public Main() {
        session = Session.getInstance();
    }

    public static void main(String[] args) {
        new Main();
        session.start();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        onConnection();
    }

    public void readMessage() {
        String s = session.readMessage();
        Gson gson = new Gson();

        Generic g = gson.fromJson(s, Generic.class);

        switch (g.getType()) {
            case "letra":
                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Ventana1.fxml"));
                Parent root;
                try {
                    root = fxmlloader.load();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                    readMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }

    public void onConnection() {
        // Stage primaryStage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("mainPane.fxml"));
        Parent root;
        try {
            root = fxmlloader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            readMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}