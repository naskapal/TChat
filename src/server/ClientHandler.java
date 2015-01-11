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
  	private int clientNum;
  	private String received;
  	private chatRoom room;
  	
  	public ClientHandler(Socket socket, int clientNum, chatRoom room)
  	{
  		//Set up reference to associated socket…
  		client = socket;
  		this.room = room;
		this.clientNum = clientNum;
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

 	 public void run()
 	 {
 		try
  		{
 	 	do
 	 	{
 	 		received = input.nextLine();
 	 		if (received.equals("1"))
 	 		{
 	 			update("please input the user number to chat");
 	 			int client = input.nextInt();
 	 			room.privateMessage(clientNum,client);
 	 		}
 	 		else
 	 		{
 	 			System.out.println("user " + clientNum +" : " + received);
 	 	 		room.notifyUpdate("user " + clientNum +" : " + received);
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
 	 public int getUserNum()
 	 {
 		 return clientNum;
 	 }
}