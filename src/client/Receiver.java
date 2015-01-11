package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Receiver extends Thread {

	private static Scanner networkInput;
	private static String response = "";
	
	private Socket socket;
	
	BufferedReader rd;
	
	public Receiver(Socket socket)
	{
		this.socket = socket;
	}
	public void run()
	{
		try {
			socket.setSoTimeout(100);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			networkInput = new Scanner(socket.getInputStream());
			while (true)
			{
				if (reader.ready())
				{
					response = networkInput.nextLine();
					if (response.isEmpty() == false)
						System.out.println(response);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (NoSuchElementException noElement){}
	}
}
