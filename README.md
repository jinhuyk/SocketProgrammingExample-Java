# Socket Chatting Project in JAVA

## π₯ νλ‘μ νΈ λͺ

Socket Chatting Project

## π‘ κΈ°ν λ° κ°λ°

λ¬Έμ§ν

## β μ μ κΈ°κ°

2022.12.18 - 

## β μ¬μ© κΈ°μ 

- **Language**: JAVA

## μΈλΆμ¬ν­

 JavaProgramming μ μκ°ν ν, 

 μμΌμ μ΄μ©ν server-client ν΅μ  νλ‘κ·Έλ¨μ μ μνλ κ²μ΄ μμ΄ μ§μ  λ§λ€μ΄λ³΄μλ€.

μμΌνλ‘κ·Έλλ°μ μμλ λ€μκ³Ό κ°λ€.

![Untitled](./images/Untitled.png)

### κ΅¬ν

- ChatServer.Java
    
    ```java
    HashMap<String, User> clients = new HashMap<>();
    ```
    
    ```java
    Socket socket = server.accept();
    MyThread th = new MyThread(socket);
    th.start();
    ```
    
    μ΄ μ½λμμλ κ³μν΄μ μλ²μ μλ‘μ΄ ν΄λΌμ΄μΈνΈκ° μ μν  κ²½μ° μ€λ λκ° μ€νμ΄ λ  κ²μ΄λ€. 
    
    ```java
    reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
    writer = new PrintWriter(client.getOutputStream(),true);
    ```
    
    ```java
    clients.put(userid,  new User(userid, reader, writer));
    Set<String> clientId = clients.keySet();
    for(String id : clientId) {
    		User user = clients.get(id);
    		user.writer.println("[" +userid + "] is connected");
    }
    ```
    
    ```java
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
    ```
    
    **μ€μ λ¬Έμ **
    
    ```java
    Set<String> clientId = clients.keySet();
    ```
    
    ```java
    Set<String> clientUid = clients.keySet();
    ```
    
    μμ μμλ clientsκ°μ clientIdμ λ΄μλμμ§λ§, whileλ¬Έ μμμλ λ μλ‘μ΄ clientIdλΌλ Setμ μλ‘ λ΄μλ€.
    
    β> whileλ¬Έμ΄ λλ©΄μ μ¬λ¬ ν΄λΌμ΄μΈνΈλ€μ disconnectλ₯Ό νμ¬ ν΄λΉ clientsμμ removeλλ€. λλ μΆκ°κ° λλ€. λ§μ½ clientId λ₯Ό μ¬μ©νλ€λ©΄ κ³μν΄μ μλ°μ΄νΈ λλ clientμ λ³΄κ° λ΄κ²¨μμ§ μκΈ° λλ¬Έμ μ€λ₯κ° μΌμ΄λλ€. (μλ clientsμκ² λ©μμ§λ₯Ό λ³΄λΈλ€λμ§ λ±β¦) 
    

### μ€ν¬λ¦°μ·

![Untitled](./images//Untitled%201.png)

### μΆν κ°μ μ¬ν­

- 1:N ν΅μ 
    
    νμ¬ κ΅¬ννκ²μ 1:Nν΅μ μΌλ‘ ν΄λΉ μλ²μ λ€μ΄μ€λ©΄ λ¬΄μ‘°κ±΄ κ·Έ μλ²μ μ¬λλ€κ³Ό 1:N ν΅μ μ ν  μ μλ€. 
    
    **λ§μ½ μ¬κΈ°μμ μΉ΄μΉ΄μ€ν‘μ 1:1 μ±ν κΈ°λ₯μ κ΅¬ννκ³  μΆλ€λ©΄?** 
    
    μλ²λ‘ λΆν° Clients μ λ³΄λ₯Ό ν΄λΌμ΄μΈνΈκ° λ°μμ¨ ν, ν΄λΉ Client λͺ©λ‘μ λ½μλΈλ€.
    
     μ΄ν μνλ Clientλ₯Ό μ ννλ€λ©΄, μλ²λ μμ κ³Ό μ νλ Clientμ λν΄ λ©μμ§λ₯Ό μ£Όκ³ λ°μ μ μλ μ€λ λλ₯Ό μ€νμν€λ©΄ λλ€. 
    

## μ€λ₯

- Serializable λ¬Έμ 
    
    μ¬μ€ λ€μ λ¬Έμ λ μμμ μ€νν νλ‘μ νΈμμλ String λ°μ΄ν°λ₯Ό λ³΄λ΄λ κ²μ΄λΌ λμ€μ§ μμμ§λ§, μΆν κ°μ μν©μμ Clients κ°μ²΄λ₯Ό μ£Όκ³  λ°κΈ° μν΄ μ½λλ₯Ό μ§λ μ€ λ°μνμλ€. 
    
    μΌλ¨ μ§λ ¬νλ μλ°μμ κ°μ²΄ λ±μ μΈλΆμ μλ°μμ€νμμ μ¬μ©ν  μ μλλ‘ νκΈ° μν΄ byte ννλ‘ λ³ννλ κΈ°μ μ λ§νλ κ²μΌλ‘, μ§κΈκΉμ§ μκ°ν javaProgrammingμμλ μΈλΆμ κ°μ²΄λ₯Ό μ£Όκ³ λ°μ§ μμλ€. νμ§λ§ μ μΆν κ°μ μ¬ν­μμ clientsλ κ°μ²΄μ΄κΈ° λλ¬Έμ μ΄λ₯Ό μ£Όκ³ λ°μΌλ¬λ©΄ μ§λ ¬νλ₯Ό ν΄μΌνλ μν©μ΄ μλ€. μ΄λ₯Ό ν΄κ²°νκΈ° μν΄ 
    
    ```java
    impliments serializable
    ```
    
    μ μΆκ°μμΌμ κ°μ²΄λ₯Ό μ§λ ¬ν ν μ μλλ‘ νμλ€. νμ§λ§ λ¬Έμ λ μ¬κΈ°λΏμ΄ μλμλ€. κ°μ²΄ μμ HashMapκ΅¬μ‘°λ μ§λ ¬νκ° μλλμ§ κ³μ serializable μ€λ₯κ° λ°μνμλ€.  μ΄λ₯Ό ν΄κ²°νκΈ° μν΄ ν΄λ²μ μ°Ύμλ³Έκ²°κ³Ό HashMap κ΅¬μ‘°λ₯Ό Fileλ‘ λ΄λ³΄λΈ νμ λ°λ λ°©λ²μ μ°Ύμ μ μμλ€.