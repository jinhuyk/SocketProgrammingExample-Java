package project.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;


class User{
	String userid;
	BufferedReader reader;
	PrintWriter writer;
	public User(String userid, BufferedReader reader, PrintWriter writer) {
		super();
		this.userid = userid;
		this.reader = reader;
		this.writer = writer;
	}

}
public class ChatServer {
	HashMap<String, User> clients = new HashMap<>();
	// client save
	ServerSocket server = null;

	public ChatServer(int port) {
		
		try {
			server = new ServerSocket(port);
			System.out.println("waiting connect...");
			
			while(true) {
				try {
					Socket socket = server.accept();
					MyThread th = new MyThread(socket);
					th.start();
				}
				catch(IOException e) {
					e.printStackTrace();
				}

			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(server != null) server.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	class MyThread extends Thread{
		Socket client = null;
		public MyThread(Socket socket) {
			// TODO Auto-generated constructor stub
			client = socket;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			PrintWriter writer = null;
			BufferedReader reader = null;
			
			try {
				reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				writer = new PrintWriter(client.getOutputStream(),true);
				String userid = reader.readLine();
				System.out.println("[" +userid + "] is connected");
				
				clients.put(userid,  new User(userid, reader, writer));
				Set<String> clientId = clients.keySet();
				for(String id : clientId) {
					User user = clients.get(id);
					user.writer.println("[" +userid + "] is connected");
					
				}
				
				while(true) {
					String msg = reader.readLine();
					if(msg ==null || msg.equals("")) continue;
					
					if(msg.equals("!@#$%^&*()")) {
						Set<String> clientUid = clients.keySet();
						for(String id : clientUid) {
							User user = clients.get(id);
							user.writer.println("[" +userid + "] is disconnected");
							
						}
						System.out.println("[" +userid + "] is disconnected");
						clients.remove(userid);
						break;
						// disconnect
					}else {
						Set<String> clientUid = clients.keySet();
						for(String id : clientUid) {
							User user = clients.get(id);
							user.writer.println("[" +userid + "] : " + msg);
							
						}
					}
				}
	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if(writer!= null) writer.close();
					if(reader!= null) reader.close();
					if(client!= null) client.close();
					
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChatServer chatserver = new ChatServer(6000);
	}

}
