package communication;

import java.io.BufferedReader;
import java.io.IOException;

public class Receptor extends Thread {

	private BufferedReader bReader;
	private OnMessageListener listener;

	// Referencia
	private Session session;

	public Receptor(Session session, BufferedReader bReader) {
		this.session = session;
		this.bReader = bReader;
	}

	public String readMessage() {
		String msg = "";
		try {
			while (true) {
				msg = bReader.readLine();
				if (msg == null) {
					break;
				}
				listener.onMessage(session, msg);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public void run() {
		readMessage();
	}

	// Metodo suscripcion
	public void setListener(OnMessageListener listener) {
		this.listener = listener;
	}

	public interface OnMessageListener {
		void onMessage(Session session, String msg);
	}

}
