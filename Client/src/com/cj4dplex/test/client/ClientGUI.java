package com.cj4dplex.test.client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class ClientGUI extends JFrame implements ActionListener {

	JPanel contentPane;
	JTextField textField;
	JTextArea textArea;
	JButton sendButton = new JButton("Send");
	JButton exitButton = new JButton("Exit");
	static Client client;

	public static void main(String[] args) {
		ClientGUI clientgui = new ClientGUI();
		clientgui.setVisible(true);
		client = new Client(clientgui);
	}

	public ClientGUI() {

		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 367, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(14, 217, 251, 24);
		contentPane.add(textField);
		textField.setColumns(10);

		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		sendButton.setBounds(269, 216, 75, 25);
		contentPane.add(sendButton);

		exitButton.setBounds(269, 184, 75, 27);
		contentPane.add(exitButton);

		textArea = new JTextArea();
		textArea.setBounds(14, 12, 251, 199);
		JScrollPane scrollPane = new JScrollPane(textArea);
		contentPane.add(textArea);

		textField.addActionListener(this);
		sendButton.addActionListener(this);
		exitButton.addActionListener(this);

	}

	public void appendmsg(String appendmsg) {
		textArea.append(appendmsg + "\n");
	}

	public void sendmsg(String smsg) throws IOException {
		client.sendMsg(smsg);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == sendButton) {
			if (textField.getText().equals("")) {
				System.out.println("Empty");
				return;
			} else {
				try {
					sendmsg(textField.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			textField.setText("");
		} else if (e.getSource() == exitButton) {
			try {
				client.endMsg();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

}