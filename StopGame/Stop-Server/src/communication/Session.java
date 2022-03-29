package communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Session {

	private Receptor receptor;
	private Emisor emisor;
	private boolean onGame;
	public Socket socket;

	public Session(Socket socket) {

		this.socket = socket;

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			receptor = new Receptor(this, reader);
			receptor.setListener(TPCServer.getInstance());
			receptor.start();

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			emisor = new Emisor(writer);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public boolean isOnGame() {
		return this.onGame;
	}

	public boolean getOnGame() {
		return this.onGame;
	}

	public void setOnGame(boolean onGame) {
		this.onGame = onGame;
	}

	public Emisor getEmisor() {
		return this.emisor;
	}

	public Receptor getReceptor() {
		return this.receptor;
	}

}