package controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;

public class MainController implements Initializable {

    private String hours, minutes, seconds;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private Circle btnCloseLogin;

    @FXML
    private Circle btnMinimizeLogin;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Label lblHour;

    @FXML
    private Label lblDate;

    @FXML
    public void handleMouseClick(MouseEvent event) {
        if (event.getSource() == btnCloseLogin) {
            System.exit(0);
        }
    }

    public void hour() {
        Calendar calendar = new GregorianCalendar();
        Date currentTime = new Date();
        calendar.setTime(currentTime);
        hours = calendar.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendar.get(Calendar.HOUR_OF_DAY)
                : "0" + calendar.get(Calendar.HOUR_OF_DAY);
        minutes = calendar.get(Calendar.MINUTE) > 9 ? "" + calendar.get(Calendar.MINUTE)
                : "0" + calendar.get(Calendar.MINUTE);
        seconds = calendar.get(Calendar.SECOND) > 9 ? "" + calendar.get(Calendar.SECOND)
                : "0" + calendar.get(Calendar.SECOND);
    }

    public String date() {
        Date date = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY");
        return formatDate.format(date);
    }

    public String getHour() {
        return lblHour.getText();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lblDate.setText(date());
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    hour();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            lblHour.setText(hours + ":" + minutes + ":" + seconds);
                        }
                    });
                }
            }
        }).start();
    }

    public void welcomeToGame() throws IOException, ClassNotFoundException {

    }

}
