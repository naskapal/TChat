package server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class chatRoom extends Thread {
	
	private static ServerSocket serverSocket;
  	private static final int PORT = 1234;
  	public static ClientHandler[] users = new ClientHandler[50];
  	private static int i = 0;
  	private static Socket client;

  	public chatRoom() {acceptConnection();}
  	public chatRoom(ClientHandler user1, ClientHandler user2)
  	{
  		users = new ClientHandler[2];
  		users[0] = user1;
  		users[1] = user2;
  		i = 0;
  		notifyUpdate(/*"this is a private chat between user " + user1.getUserNum() + " and user " + user2.getUserNum()*/"");
  	}
  	private void acceptConnection()
  	{
  		try
  		{
  			serverSocket = new ServerSocket(PORT);
  			serverSocket.setSoTimeout(100);
  			System.out.println("Server is up, awaiting user to connect..");
  		}
  		catch (IOException ioEx)
  		{
  			System.out.println("\nUnable to set up port!");
  			System.exit(1);
  		}

  		do
  		{
  			if (i != users.length)
  			{
  			//Wait for client
  				try
  				{
  					client = serverSocket.accept();
  				}
  				catch (SocketTimeoutException a){}
  				catch (IOException b){}
  			if (client != null)
  			{

  			//Create a thread to handle communication with
  			//this client and pass the constructor for this
  			//thread a reference to the relevant socket

  			users[i] = new ClientHandler(client,this);
  			users[i].start();
  			i++;
  			client = null;
  			}
  			}
  			//As usual, method calls run
  		}while (true);
  	}
  	public void run()
  	{
  		acceptConnection();
  	}
  	public void update(String msg)
  	{
  		/* *
  		 * accepts a message that invokes clientHandler.update(String msg)
  		 */
  		try
  		{
  			if (msg.equals("") == false)
  			{
  				for (i = 0; users[i] != null; i++)
  				{
  					users[i].update(msg);
  				}
  			}
  		}
  		catch (ArrayIndexOutOfBoundsException asd){}
  	}
  	public void notifyUpdate(String msg)
  	{
  		String message = msg;
  		update(message);
  	}
  	public void privateMessage(int requester,int victim)
  	{
  		if (users[victim-1] != null)
  		{
  			chatRoom PM = new chatRoom(users[requester-1],users[victim-1]);
  			removeUser((requester-1));
  			removeUser((victim-1));
  	  		PM.start();
  		}
  		else
  			users[requester-1].update("User not found");
  	}
  	private void removeUser(int userNum)
  	{
  		users[userNum] = null;
  		for (int i = userNum; i < users.length-1; i++)
  		{
  			users[i] = users[i+1];
  		}
  	}
}
