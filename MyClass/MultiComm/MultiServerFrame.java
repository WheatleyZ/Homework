package MultiComm;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;

public class MultiServerFrame extends Frame implements ActionListener {
	Panel northPanel, southPanel;
	Button startButton, sayButton;
	TextField sayTextField, portTextField;
	TextArea msgTextArea;
	ArrayList<Socket> commGroup;

	public MultiServerFrame(String title) {
		super(title);
	}

	public static void main(String[] args) {
		MultiServerFrame server = new MultiServerFrame("服务端");
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
		Object eventSource = e.getSource();
		if (eventSource == startButton) {
			Thread serverThread = new MultiServerListenerTherad(this);
			serverThread.start();
		} else if (eventSource == sayButton) {
			msgTextArea.append("I Say: " + sayTextField.getText() + "\n");
			send(sayTextField.getText());
			sayTextField.setText(null);
		}
	}

	public void send(String msg) {
		PrintWriter writer;
		for (Socket socket : commGroup) {
			try {
				writer = new PrintWriter(socket.getOutputStream());
				writer.println(msg);
				writer.flush();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}

// class MultiServerCommThread extends Thread {
// MultiServerFrame frame;
// Socket socket;
//
// public MultiServerCommThread(Socket socket, MultiServerFrame serverFrame) {
// this.frame = serverFrame;
// this.socket = socket;
// }
//
// @Override
// public void run() {
// try {
// frame.commGroup.add(new PrintWriter(socket.getOutputStream()));
// } catch (IOException e1) {
// // TODO 自动生成的 catch 块
// e1.printStackTrace();
// }
// BufferedReader hearReader;
// try {
// hearReader = new BufferedReader(new
// InputStreamReader(socket.getInputStream()));
// while (true) {
// String hear = hearReader.readLine();
// frame.msgTextArea.append(hear + "\n");
// for (PrintWriter pw : frame.commGroup) {
// pw.println(hear);
// pw.flush();
// }
// }
// } catch (IOException e) {
// e.printStackTrace();
// }
// }
// }

class MultiServerListenerTherad extends Thread {
	MultiServerFrame frame;
	Socket socket;
	MultiCommThread mCThread;

	public MultiServerListenerTherad(MultiServerFrame frame) {
		this.frame = frame;
	}

	@Override
	public void run() {
		int port = Integer.parseInt(frame.portTextField.getText());
		frame.msgTextArea.append("start listening from port " + port + "\n");
		try {
			while (true) {
				ServerSocket server = new ServerSocket(port);
				this.socket = server.accept();
				frame.commGroup.add(socket);
				mCThread = new MultiCommThread(socket, frame);
				mCThread.start();
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}

class MultiCommThread extends Thread {
	Socket socket;
	MultiServerFrame frame;

	public MultiCommThread(Socket socket, MultiServerFrame frame) {
		this.socket = socket;
		this.frame = frame;
	}

	@Override
	public void run() {
		while (true) {
			try {
				BufferedReader hearReader;
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
}