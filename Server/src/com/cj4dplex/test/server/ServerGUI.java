package com.cj4dplex.test.server;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class ServerGUI extends JFrame {

	JPanel contentPane;
	JTextArea chatarea = new JTextArea();
	JTextArea userarea = new JTextArea();

	public ServerGUI() {

		setTitle("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 306, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		chatarea.setBounds(14, 31, 172, 218);
		contentPane.add(chatarea);

		userarea.setBounds(197, 31, 77, 218);
		contentPane.add(userarea);

		JLabel lblLoginUser = new JLabel("Login User");
		lblLoginUser.setBounds(202, 12, 77, 18);
		contentPane.add(lblLoginUser);

		JLabel lblChatting = new JLabel("Chatting");
		lblChatting.setBounds(68, 12, 62, 18);
		contentPane.add(lblChatting);
	
	}


}
