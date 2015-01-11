package client;
import java.io.*;
import java.net.*;
import java.util.*;


public class MultiEchoClient{
	private static byte[] addr ={(byte)127,(byte)0,(byte)0,(byte)1};
	private static InetAddress host;
	private static final int PORT=1234;
	
	private static String message;
	private static Socket socket = null;

	private static Scanner userEntry;
	
	private static PrintWriter networkOutput;

	public static void main(String [] args){
		try
		{
			host=InetAddress.getByAddress(addr);
		}
		catch(UnknownHostException uhEx)
		{
			System.out.println("\nHost ID not found!\n");
			System.exit(1);
		}
		sendMessage();
	}
	private static void sendMessage()
	{
		try {
				socket = new Socket(host,PORT);
				networkOutput = new PrintWriter(socket.getOutputStream(),true);
				Receiver acceptor = new Receiver(socket);
				acceptor.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			userEntry= new Scanner(System.in);
			System.out.print("Enter message('QUIT' to Exit): ");
			do{
				message= userEntry.nextLine();
				networkOutput.println(message);
			}
			while(!message.equals("QUIT"));
			try
			{
				System.out.println("\nClosing connection...");
				socket.close();
			}
			catch(IOException ioEx)
			{
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
	}

}