package gameCode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		String hostName = args[0];
		int port = Integer.parseInt(args[1]);
		try {
			Socket clientSkt = new Socket(hostName, port);
			PrintWriter out = 
				new PrintWriter(clientSkt.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
				new InputStreamReader(clientSkt.getInputStream()));
			BufferedReader stdIn = new BufferedReader(
	            new InputStreamReader(System.in));
			String fromServer, fromUser;

			System.out.println(
				"## Successfully connected to host "
				+ hostName
				+ " on port "
				+ port
				+ ".\n"
			);
			while ((fromServer = in.readLine()) != null) {
				// Response expected if message begins with "QQ"
				if (
					fromServer.length() >= 3
					&& fromServer.substring(0,3).equals("QQ:")
				) {
					System.out.println(fromServer.substring(3));
					fromUser = stdIn.readLine();
					if (fromUser != null) {
						out.println(fromUser);
					}
				} else {
					System.out.println(fromServer);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
