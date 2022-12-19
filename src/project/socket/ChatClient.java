package project.socket;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ChatClient extends JDialog implements ActionListener{
	
	Container frame = this.getContentPane();
	Socket socket =null;
	PrintWriter writer = null;
	BufferedReader reader = null;
	
	JTextField inputField;
	JTextArea Chat;
	JLabel infoServer;
	JButton discBtn;
	String userid =null;
	boolean flag = true;
	MyClientThread th =null;
	
	public ChatClient() {
		

		this.setSize(500,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		init();
		initWindowListener();
		this.setVisible(true);
		
	}
	private void init() {
		// TODO Auto-generated method stub
		JPanel btnPanel = new JPanel();
		infoServer = new JLabel();
		Chat = new JTextArea();
		inputField = new JTextField();
		discBtn = new JButton("DISCONNECT");
		btnPanel.add(discBtn);
		frame.add(new JScrollPane(Chat),BorderLayout.CENTER);
		frame.add(inputField, BorderLayout.SOUTH);
		frame.add(btnPanel,BorderLayout.NORTH);
		inputField.addActionListener(this);
		discBtn.addActionListener(e->{
			if(th.isAlive()) {
				th.writer.println("!@#$%^&*()");
				flag = false;
			}
		});
	}
	
	public void initWindowListener() {
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowOpened(e);
				userid = JOptionPane.showInputDialog("Your Id? ");
				ChatClient.this.setTitle(userid);
				connectServer();
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				th.writer.println("!@#$%^&*()");
				flag = false;
				
			}
			
		});
	}
	
	public void connectServer() {
		try {
			this.socket = new Socket("localhost", 6000);
			th = new MyClientThread();
			th.start();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	class MyClientThread extends Thread{

		private PrintWriter writer;
		public MyClientThread() {
			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream(),true);
				writer.println(userid);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			while(flag) {
				try {
					String msg = reader.readLine();
					if(msg != null && !msg.equals("")) {
						Chat.append(msg+"\n");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChatClient client = new ChatClient();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		th.writer.println(inputField.getText());
		inputField.setText("");
	}

}
