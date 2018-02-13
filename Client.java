import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String serverHostname;
		System.out.println("Enter an IP value (localhost): ");
		serverHostname = s.next();
		
		Socket echoSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try {
			echoSocket = new Socket(serverHostname, 4444);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					echoSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + serverHostname);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for " + "the connection to: "
					+ serverHostname);
			System.exit(1);
		}

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(
				System.in));
		String userInput;

		System.out.println("Welcome: " + in.readLine());
		System.out.print("Input message: ");
		while ((userInput = stdIn.readLine()) != null) {
			out.println(userInput);
			System.out.println("Echo server message: " + in.readLine());
			System.out.print("Input message: ");
		}

		out.close();
		in.close();
		stdIn.close();
		echoSocket.close();
	}

}

