package communication;

import java.io.*;
import java.net.Socket;

import events.OnMessageReceived;
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

    private OnMessageReceived messageReceived;

    public String msg = "";

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
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

    public void readMessage() {
        new Thread(() -> {

            try {
                while (msg == null || msg.isEmpty()) {
                    msg = br.readLine();
                    System.out.println(msg);
                }
                messageReceived.onMessageReceived(msg);
                msg = "";
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

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public OnMessageReceived getMessageReceived() {
        return this.messageReceived;
    }

    public void setMessageReceived(OnMessageReceived messageReceived) {
        this.messageReceived = messageReceived;
    }

}
