package com.suremoon.gametest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
   public static String getIp(Socket asserversocket)
   {
	 String serverIp;
	 serverIp=asserversocket.getInetAddress().getHostAddress();
	 return serverIp;
   }
   public static void main(String[]args) throws IOException
   {
	   ServerSocket serverSocket = null;
       Socket asserversocket;
       Socket asclientsocket;
	   serverSocket=new ServerSocket(30000);
	   while(true)
	   {
		   System.out.println("fffffffffffffffffffffffff");
		   asserversocket=serverSocket.accept();
		   System.out.println(getIp(asserversocket));
		   System.out.println("fffffffffffffffffffffffff");
		   asclientsocket=serverSocket.accept();
		   System.out.println("fffffffffffffffffffffffff");
		   BufferedWriter bff1=new BufferedWriter(new OutputStreamWriter(
                   asclientsocket.getOutputStream()));
		   bff1.write(getIp(asserversocket));
           bff1.newLine();
           bff1.flush();
		   BufferedWriter bff2=new BufferedWriter(new OutputStreamWriter(
				   asserversocket.getOutputStream()));
		   bff2.write(getIp(asclientsocket));
		   bff2.newLine();
		   bff2.flush();
		  // asserversocket.close();
		  // asclientsocket.close();
	   }
   }
}
