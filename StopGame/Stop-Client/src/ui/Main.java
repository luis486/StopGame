package ui;

import java.io.IOException;

import communication.TCPConnection;
import communication.TCPConnection.OnConnectionListener;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;

public class Main extends Application implements OnConnectionListener {

    public static Stage stage;

    public TCPConnection tcpConnection;

    public Main() {
        tcpConnection = TCPConnection.getInstance();
        tcpConnection.setConnectionListener(this);
    }

    public static void main(String[] args) {
        launch(args);
        Main main = new Main();
        main.tcpConnection.start();
    }

    @Override
    public void onConnection(boolean connected) {
        Stage primaryStage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("mainPane.fxml"));
        Parent root;
        try {
            root = fxmlloader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
    }

}