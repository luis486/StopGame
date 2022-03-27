package communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.google.gson.Gson;

import model.*;

public class TPCServer extends Thread implements Receptor.OnMessageListener {

	// SINGLETON
	private static TPCServer instance = null;
	public static final int DISPATCHER_PORT = 5000;

	private TPCServer() {
		sessions = new ArrayList<>();
		gson = new Gson();
		sessionQueue = new LinkedList<>();
		game = new Game();

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
	private Queue<Session> sessionQueue;
	private Gson gson;
	private Game game;

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
				User user = gson.fromJson(msg, User.class);
				session.setUser(user);
				sessionQueue.add(session);
				matchPlayers();
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

	}

	public Session findSession(User user) {
		boolean validation = false;
		Session temp = null;

		for (int i = 0; i < sessions.size() && !validation; i++) {
			if (sessions.get(i).getUser().getId().equals(user.getId())) {
				temp = sessions.get(i);
				validation = true;
			}
		}

		return temp;
	}

}
