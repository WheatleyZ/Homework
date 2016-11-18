package homework;

import java.io.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {

	public static void main(String[] args) {
		try{
			System.out.println("Server starting...");
			ServerSocket server = new ServerSocket(4000);
			Socket s = server.accept();
			System.out.println("Socket connected:"+s);
			InputStream is = s.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			PrintWriter pw = new PrintWriter(s.getOutputStream());
		}
	}

}
