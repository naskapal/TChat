package server;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;


class ClientHandler extends Thread
  {
	private Socket client;
  	private Scanner input;
  	private PrintWriter output;
  	private String received,nickName; //add number behind nickname if similar nick appears
  	private chatRoom room;
  	
  	public ClientHandler(Socket socket, chatRoom room)
  	{
  		//Set up reference to associated socket…
  		client = socket;
  		this.room = room;
  		try
  		{
  			input = new Scanner(client.getInputStream());
  			output = new PrintWriter(client.getOutputStream(),true);
  		}
  		catch(IOException ioEx)
  		{
  			ioEx.printStackTrace();
  		}
 	 }
  	
  	private void setNick()
  	{
  		nickName = input.nextLine();
  	}

 	 public void run()
 	 {
 		 setNick();
 		try
  		{
 	 	do
 	 	{
 	 		received = input.nextLine();
 	 		if (received.equals("1"))
 	 		{
 	 			update("please input the user number to chat");
 	 			//int client = input.nextInt();
 	 			//room.privateMessage(clientNum,client);
 	 		}
 	 		else
 	 		{
 	 			//System.out.println("user " + clientNum +" : " + received);
 	 	 		//room.notifyUpdate("user " + clientNum +" : " + received);
 	 		}
 	 	}while (!received.equals("QUIT")); 
 	 	
 /* 		do
  		{
  			//Accept message from client on the socket's input stream…
  			received = input.nextLine();
			System.out.println("Message received");

			System.out.println(received);

  			//Echo message back to client on the socket's output stream…
  			output.println("ECHO: " + received);
  			//Repeat above until 'QUIT' sent by client…
  		}while (!received.equals("QUIT")); 
  */

  		
  			if (client != null)
  			{
  				System.out.println("Closing down connection…");
  				client.close();
  			}
  		}
  		catch(IOException ioEx)
  		{
  			System.out.println("Unable to disconnect!");
  		}
 		catch(NoSuchElementException noElement)
 		{
 		}
  	}
 	 public void update(String msg)
 	 {
 		output.println(msg);
 	 }
 	 public void setNick(String nickname)
 	 {
 		 this.nickName = nickname;
 	 }
}