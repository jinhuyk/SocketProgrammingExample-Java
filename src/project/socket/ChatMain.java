package project.socket;

import java.awt.Container;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ChatMain extends JFrame {
	Container frame = this.getContentPane();
	
	public ChatMain() {
		this.setSize(500,800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		init();
		this.setVisible(true);
			
	}
	
	private void init() {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
