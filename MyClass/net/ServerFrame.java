package net;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class ServerFrame extends Frame implements ActionListener {
	Panel northPanel, southPanel;
	Button startButton, sayButton;
	TextField sayTextField, portTextField;
	TextArea msgTextArea;
	ServerSocket server;
	Socket socket;

	public ServerFrame(String title) {
		super(title);
	}

	public static void main(String[] args) {
		ServerFrame server = new ServerFrame("服务端");
		server.drawGUI();
	}

	private void drawGUI() {
		northPanel = new Panel();
		southPanel = new Panel();
		portTextField = new TextField();
		sayTextField = new TextField();
		startButton = new Button("Start");
		sayButton = new Button("Sent");
		msgTextArea = new TextArea(10, 60);
		add(northPanel, "North");
		add(msgTextArea, "Center");
		add(southPanel, "South");
		northPanel.add(new Label("Port:"));
		northPanel.add(portTextField);
		northPanel.add(startButton);
		southPanel.add(sayTextField);
		southPanel.add(sayButton);
		setSize(1280, 800);
		portTextField.setColumns(100);
		sayTextField.setColumns(100);
		startButton.addActionListener(this);
		sayButton.addActionListener(this);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Object eventSource = e.getSource();
			if (eventSource == startButton) {
				Thread serverThread = new ServerListenerTherad(this);
				serverThread.start();
			} else if (eventSource == sayButton && socket != null) {
				PrintWriter pw;
				pw = new PrintWriter(socket.getOutputStream());
				pw.println(sayTextField.getText());
				pw.flush();
				msgTextArea.append("I Say: " + sayTextField.getText() + "\n");
				sayTextField.setText(null);
			}
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
	}
}

class ServerCommThread extends Thread {
	ServerFrame frame;
	Socket socket;

	public ServerCommThread(ServerFrame serverFrame) {
		this.frame = serverFrame;
		this.socket = frame.socket;
	}

	@Override
	public void run() {
		BufferedReader hearReader;
		try {
			hearReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while (true) {
				String hear = hearReader.readLine();
				frame.msgTextArea.append("Other say: " + hear + "\n");
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}

class ServerListenerTherad extends Thread {
	ServerFrame frame;

	public ServerListenerTherad(ServerFrame frame) {
		this.frame = frame;
	}

	@Override
	public void run() {
		try {
			int port = Integer.parseInt(frame.portTextField.getText());
			frame.msgTextArea.append("Start server on port:" + port + "\n");
			frame.server = new ServerSocket(port);
			frame.socket = frame.server.accept();
			frame.msgTextArea.append("socket accepted on port:" + port + "\n");
			ServerCommThread serverCommThread = new ServerCommThread(frame);
serverCommThread.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}