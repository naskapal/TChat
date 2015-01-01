import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

  public class MultiEchoServer
  {
  	private static ServerSocket serverSocket;
  	private static final int PORT = 1234;

  	public static void main(String[] args) throws IOException
  	{
  		try
  		{
  			serverSocket = new ServerSocket(PORT);
  		}
  		catch (IOException ioEx)
  		{
  			System.out.println("\nUnable to set up port!");
  			System.exit(1);
  		}

  		do
  		{
  			//Wait for client…
  			Socket client = serverSocket.accept();

  			//Create a thread to handle communication with
  			//this client and pass the constructor for this
  			//thread a reference to the relevant socket…

  			ClientHandler handler = new ClientHandler(client);
  			handler.start();
  			//As usual, method calls run  .
  		}while (true);
  	}
  }