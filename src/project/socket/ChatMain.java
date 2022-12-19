package project.socket;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ChatMain extends JFrame {
	Container frame = this.getContentPane();
	
	public ChatMain() {
		this.setSize(500,200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		init();
		this.setVisible(true);
			
	}
	
	private void init() {
		JPanel panel = new JPanel();
		// TODO Auto-generated method stub
		JLabel label1 = new JLabel("input your name");
		JTextField userid = new JTextField(10);
		JButton loginBtn = new JButton("JOIN!");
		panel.add(label1);
		panel.add(userid);
		panel.add(loginBtn);
		
		JLabel label2 = new JLabel("Socket Programming    :::::    Made by Mun Jin Huyk");
		
		frame.add(label2,BorderLayout.NORTH);
		frame.add(panel,BorderLayout.SOUTH);
		
		loginBtn.addActionListener(e->{
			new ChatClient(this, userid.getText().trim());
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChatMain();
		
	}

}
