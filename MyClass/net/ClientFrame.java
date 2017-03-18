package net;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

import com.sun.xml.internal.ws.wsdl.writer.document.PortType;

public class ClientFrame extends Frame implements ActionListener {
	Panel northPanel, southPanel;
	Button connectButton, sayButton;
	TextField sayTextField, portTextField, ipTextField;
	TextArea msgTextArea;
	Socket socket;

	public ClientFrame(String title) {
		super(title);
	}

	public static void main(String[] args) {
		MultiClientFrame server = new MultiClientFrame("客户� ");
		server.drawGUI();
	}

	private void drawGUI() {
		northPanel = new Panel();
		southPanel = new Panel();
		portTextField = new TextField();
		sayTextField = new TextField();
		ipTextField = new TextField();
		connectButton = new Button("Start");
		sayButton = new Button("Sent");
		msgTextArea = new TextArea(10, 60);
		add(northPanel, "North");
		add(msgTextArea, "Center");
		add(southPanel, "South");
		northPanel.add(new Label("IP:"));
		northPanel.add(ipTextField);
		northPanel.add(new Label("Port:"));
		northPanel.add(portTextField);
		northPanel.add(connectButton);
		southPanel.add(sayTextField);
		southPanel.add(sayButton);
		setSize(1280, 800);
		portTextField.setColumns(50);
		ipTextField.setColumns(50);
		sayTextField.setColumns(100);
		connectButton.addActionListener(this);
		sayButton.addActionListener(this);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object eventSource = e.getSource();
		if (eventSource == connectButton) {
			try {
				socket = new Socket(ipTextField.getText(), Integer.parseInt(portTextField.getText()));
				Thread commTherad = new MultiClientCommThread(this);
				commTherad.start();
			} catch (NumberFormatException | IOException e1) {
				e1.printStackTrace();
			}
		} else if (eventSource == sayButton && socket != null) {
			PrintWriter pw;
			try {
				pw = new PrintWriter(socket.getOutputStream());
				pw.println(sayTextField.getText());
				pw.flush();
				msgTextArea.append("I Say: " + sayTextField.getText() + "\n");
				sayTextField.setText(null);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}

class ClientCommThread extends Thread {
	MultiClientFrame frame;
	Socket socket;

	public ClientCommThread(MultiClientFrame clientFrame) {
		this.frame = clientFrame;
		this.socket = frame.socket;
	}

	@Override
	public void run() {
		frame.msgTextArea
				.append("connected to" + frame.ipTextField.getText() + ":" + frame.portTextField.getText() + "\n");
		BufferedReader hearReader;
		try {
			hearReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while (true) {
				String hear = hearReader.readLine();
				frame.msgTextArea.append("Other say: " + hear + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}