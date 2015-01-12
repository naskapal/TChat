import java.io.*;
import java.net.*;
import java.util.*;


public class MultiEchoClient{
	private static byte[] addr ={(byte)127,(byte)0,(byte)0,(byte)1};
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
			String message /*, responds */;
			
			while (networkInput.hasNextLine())
				System.out.println("\nServer > "+ networkInput.nextLine());
			//{
			System.out.println();	
				//message= userEntry.nextLine();
				//networkOutput.println(message);
			//}
				//System.out.print("Enter message('QUIT' to Exit): ");
			do
			{
				message= userEntry.nextLine();
				networkOutput.println(message);

			}
			while(!message.equals("QUIT"));
		}
		catch (IOException ioEx)
		{
			ioEx.printStackTrace();
		}
		catch (NoSuchElementException noEx)
		{
			noEx.printStackTrace();
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