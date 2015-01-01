import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

 class ClientHandler extends Thread
  {
  	private Socket client;
  	private Scanner input;
  	private PrintWriter output;
  	private Connector koneksi;
  	private String userName,password;
  	
  	public ClientHandler(Socket socket)
  	{
  		//Set up reference to associated socket…
  		client = socket;
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
 	 	String received;
 	 	output.println("Please Enter Your Login Credentials");
  			do
  			{
  			output.println("username : ");
  			while (input.hasNext());
  			userName = input.nextLine();
  			output.println("password : ");
  			password = input.nextLine();
  			if (!koneksi.userLogin(userName, password))
  				output.println("username and password mismatch");
  			}while(koneksi.userLogin(userName, password) == false);
  	  		
  			do
  	  		{
  			//Accept message from client on the socket's input stream…
  			received = input.nextLine();
			System.out.println("Message received");

			System.out.println(received);

  			//Echo message back to client on the socket's output stream…
  			output.println("ECHO: " + received);
  			//Repeat above until 'QUIT' sent by client…
  		}while (!received.equals("QUIT"));

  		try
  		{
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
  	}
}