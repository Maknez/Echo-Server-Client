import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerEcho implements Runnable {

	private Socket clientSocket;
	private int ID;
	public ServerEcho(Socket clientSocket, int personalID) {
		this.clientSocket = clientSocket;
		this.ID = personalID;
	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));

			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), 
                    true); 

			out.println("Welcome to server echo!");
			String s;
			while ((s = in.readLine()) != null) {
				System.out.println("Client " + ID + ". wrote: " + s);
				out.println(s);
				
			}

			System.out.println("Closing connection with " + ID + ". client. ");
			out.close();
			in.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

