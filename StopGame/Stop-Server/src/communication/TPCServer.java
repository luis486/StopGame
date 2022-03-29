package communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import com.google.gson.Gson;

import model.*;

public class TPCServer extends Thread implements Receptor.OnMessageListener {

	// SINGLETON
	private static TPCServer instance = null;
	public static final int DISPATCHER_PORT = 6000;
	String id;

	private TPCServer() {
		sessions = new ArrayList<>();
		gson = new Gson();
		sessionQueue = new LinkedList<>();
		id = UUID.randomUUID().toString();

	}

	public static synchronized TPCServer getInstance() {
		if (instance == null) {
			instance = new TPCServer();
		}
		return instance;
	}

	// GLOBAL
	private ServerSocket server;
	private ArrayList<Session> sessions;
	public Queue<Session> sessionQueue;
	private Gson gson;

	@Override
	public void run() {
		try {
			server = new ServerSocket(DISPATCHER_PORT);

			while (true) {
				System.out.println("Esperando en el puerto " + DISPATCHER_PORT);
				Socket socket = server.accept();
				System.out.println("Nuevo cliente conectado");

				Session session = new Session(socket);
				sessions.add(session);

				if (sessions.size() % 2 == 0) {
					matchPlayers();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Server Actions
	@Override
	public void onMessage(Session session, String msg) {
		Generic obj = gson.fromJson(msg, Generic.class);

		switch (obj.getType()) {
			case "User":

				break;

			case "Game":
				Game gameIn = gson.fromJson(msg, Game.class);
				break;
			case "Message":
				Message message = gson.fromJson(msg, Message.class);
				switch (message.getMessage()) {

				}
				break;

			default:
				break;
		}

	}

	public void matchPlayers() {

		new Thread(() -> {
			Session sess = sessions.get(sessions.size() - 1);
			Session sess1 = sessions.get(sessions.size() - 2);

			Game game = new Game(sess, sess1);

		}).start();

	}

	/*
	 * public Session findSession(User user) {
	 * boolean validation = false;
	 * Session temp = null;
	 * 
	 * for (int i = 0; i < sessions.size() && !validation; i++) {
	 * if (sessions.get(i).getUser().getId().equals(user.getId())) {
	 * temp = sessions.get(i);
	 * validation = true;
	 * }
	 * }
	 * 
	 * return temp;
	 * }
	 **/

}
