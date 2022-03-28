package communication;

import java.io.*;
import java.net.Socket;

import javafx.application.Platform;
import model.User;

public class Session extends Thread {

    private User user;
    private static Session instance;
    private String ip = "127.0.0.1";
    private int port = 6000;
    private Socket socket;
    private BufferedWriter bw;
    private BufferedReader br;

    public String msg;

    public static synchronized Session getInstance(User user) {

        if (instance == null) {

            instance = new Session(user);
        }
        return instance;

    }

    private Session(User user) {

        this.user = user;

        msg = null;
    }

    @Override
    public void run() {

        try {

            socket = new Socket(ip, port);
            
            OutputStream os = socket.getOutputStream();
            bw = new BufferedWriter(new OutputStreamWriter(os));

            InputStream is = socket.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));

            Platform.runLater(() -> {

            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(String line) {

        new Thread(() -> {

            try {
                bw.write(line + "\n");
                bw.flush();

            } catch (IOException e) {

                e.printStackTrace();
            }
        }).start();
        ;

    }

    public void waitingMessage() {

        new Thread(() -> {

            try {
                while (msg == null || msg.isEmpty()) {

                    msg = br.readLine();

                    System.out.println(msg);

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public User getUser() {
        return user;
    }

    public void resetSesion() {

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void setInstance() {
        instance = null;
    }

}
