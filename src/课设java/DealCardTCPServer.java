package 课设java;

import java.io.*;
import java.net.*;
class DealCardTCPServer
{
	public DealCardTCPServer(int port,String name)throws IOException
	{
		ServerSocket server=new ServerSocket(port);
		Socket client=server.accept();
		ReceiveCardJFrame RCJ=new ReceiveCardJFrame(new CardBuffer(1),"a",client);
		server.close();
	}
public static void main(String args[]) throws IOException
{
	new DealCardTCPServer(2008,"a");
}
}