import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class StartServer {
	public static void main(String[] args) throws IOException {
        int port = 4444;
		int count = 0;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Started server on port " + port);

        while (true) {

            Socket clientSocket = serverSocket.accept();
            System.out.println("User connected, new thread started.");
			count++;
            Thread serverThread = new Thread(new ServerEcho(clientSocket, count));
            serverThread.start();
        }
	}
}

