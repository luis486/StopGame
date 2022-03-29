package ui;

import java.io.IOException;

import com.google.gson.Gson;

import communication.Session;
import controller.Ventana1Controller;
import events.OnMessageReceived;
import events.OnSendMessage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import model.*;
import javafx.scene.*;

public class Main extends Application implements OnMessageReceived, OnSendMessage {

    public static Stage stage;
    public static Session session;

    public Main() {
        session = Session.getInstance();
        session.setMessageReceived(this);

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
        session.readMessage();

    }

    public void onConnection() {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("mainPane.fxml"));
        Parent root;
        try {
            root = fxmlloader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            readMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMessageReceived(String msg) {
        msg = session.getMsg();
        System.out.println(msg);
        Gson gson = new Gson();

        Generic g = gson.fromJson(msg, Generic.class);

        if (g != null) {
            switch (g.getType()) {
                case "Message":
                    Message m = gson.fromJson(msg, Message.class);
                    String j = m.getMessage();
                    Platform.runLater(() -> {
                        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Ventana1.fxml"));
                        Parent root;
                        try {
                            Ventana1Controller ven = new Ventana1Controller();
                            ven.setOsm(this);
                            fxmlloader.setController(ven);
                            root = (Parent) fxmlloader.load();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            ven.letraLabel.setText(j);
                            stage.show();
                            readMessage();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            }

        } else {
            System.out.println("nulo");
        }
    }

    @Override
    public void onSendMessage(String msg) {
        session.sendMessage(msg);
    }

}