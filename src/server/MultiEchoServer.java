package server;
 
  public class MultiEchoServer
  {
	 private static chatRoom publicRoom = new chatRoom();
	 
  	public static void main(String[] args)
  	{
  		publicRoom.start();
  	}
  }