package ui;

import java.io.IOException;

import communication.Session;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}