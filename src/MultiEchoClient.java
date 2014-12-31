import java.io.*;
import java.net.*;
import java.util.*;


public class MultiEchoClient{
	private static byte[] addr ={(byte)192,(byte)168,(byte)155,(byte)1};
	private static InetAddress host;
	private static final int PORT=1234;
	
	private static Scanner networkInput;
	private static Scanner userEntry;

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
		Socket socket = null;
		try{
			socket = new Socket(host,PORT);
			networkInput = new Scanner(socket.getInputStream());
			PrintWriter networkOutput= new PrintWriter(socket.getOutputStream(),true);
			userEntry = new Scanner(System.in);
			String message,response;
			do{
				System.out.print("Enter message('QUIT' to Exit): ");
				message= userEntry.nextLine();
				networkOutput.println(message);
				response= networkInput.nextLine();
				System.out.println("\nServer>"+response);

			}
			while(!message.equals("QUIT"));
		}
		catch (IOException ioEx)
		{
			ioEx.printStackTrace();
		}
		finally
		{
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

}