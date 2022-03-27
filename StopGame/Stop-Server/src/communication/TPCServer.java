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
	public static final int DISPATCHER_PORT = 5000;
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

		new Thread(() -> {

			Session sesionUno = null;
			Session sesionDos = null;

			boolean out = false;
			for (int i = 0; i < sessions.size() && out == false; i++) {
				Session tmp = sessions.get(i);
				if (tmp.isOnGame() == false) {
					sesionUno = tmp;
					sesionUno.setOnGame(true);
					System.out.println(sesionUno.isOnGame());
					sessions.get(i).setOnGame(true);
					out = true;
				}
			}
			out = false;
			for (int i = 0; i < sessions.size() && out == false; i++) {
				Session tmp = sessions.get(i);
				if (tmp.isOnGame() == false) {

					sesionDos = tmp;
					sesionDos.setOnGame(true);
					sessions.get(i).setOnGame(true);
					out = true;
				}
			}
			if (sesionUno == null || sesionDos == null) {
				System.out.println("Alguna sesión es nula");
			}
			if (sesionUno != null && sesionDos != null) {
				Game game = new Game(sesionUno, sesionDos);
			}

		}).start();

	}

	public void startGame(Session sesionA, Session sesionB) {
		Message msg = new Message("sendPlayer");
		Gson gson = new Gson();
		String message = gson.toJson(msg);
		sesionA.getEmisor().sendMessage(message);
		String playerA = sesionA.getReceptor().readMessage();
		sesionB.getEmisor().sendMessage(message);
		String playerB = sesionB.getReceptor().readMessage();

		char c = game.randomLetter();

		sesionA.getEmisor().sendMessage(playerB + "//" + c);

		sesionB.getEmisor().sendMessage(playerA + "//" + c);

		new Thread(() -> {

			String a = sesionA.getReceptor().readMessage();

			sesionB.getEmisor().sendMessage(a);
			String b = sesionB.getReceptor().readMessage();
			sesionA.getEmisor().sendMessage(b);
			sesionB.getEmisor().sendMessage("");

			sesionA.getReceptor().readMessage();
			sesionB.getReceptor().readMessage();

		}).start();

		new Thread(() -> {

			String b = sesionB.getReceptor().readMessage();
			sesionA.getEmisor().sendMessage(b);
			String a = sesionA.getReceptor().readMessage();
			sesionB.getEmisor().sendMessage(a);
			sesionB.getReceptor().readMessage();

		}).start();

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
