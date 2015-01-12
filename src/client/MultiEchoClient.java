package client;
import java.io.*;
import java.net.*;
import java.util.*;


public class MultiEchoClient{
	private static byte[] addr = new byte[4];
	private static InetAddress host;
	private static final int PORT=1234;
	
	private static String message;
	private String nickName;
	private static Socket socket = null;

	private static Scanner userEntry;
	
	private static PrintWriter networkOutput;

	public boolean initiate(String serverIP,String nickName){
		try
		{
			String[] address = new String[4];
			address = serverIP.split("\\.");
			for (int i = 0; i < addr.length; i++)
				addr[i] = Byte.parseByte(address[i]);
			host=InetAddress.getByAddress(addr);
			this.nickName = nickName;
			sendMessage();
			return true;
		}
		catch(UnknownHostException uhEx)
		{
			//System.out.println("\nHost ID not found!\n");
			return false;
		}
	}
	private void sendMessage()
	{
		try {
				socket = new Socket(host,PORT);
				networkOutput = new PrintWriter(socket.getOutputStream(),true);
				networkOutput.println(nickName);
				Receiver acceptor = new Receiver(socket);
				acceptor.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		/*	userEntry= new Scanner(System.in);
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
			}*/
	} 
}