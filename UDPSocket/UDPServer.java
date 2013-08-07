/*****************************************************************************
 * File Name       : UDPServer.java
 * Purpose         : Establishes a UDP datagram socket to be used as a UDP
 *					 server.  Continually processes queries from clients and
 *					 sends back appropriate responses.
 *                          
 * Author          : Austin Horne     E-mail: hornew@goldmail.etsu.edu       
 * Course          : CSCI 3400 - Networking Fundamentals
 * Create Date     : April 2, 2013
 * 
 * Modified Date   : April 2, 2013
 * Modified by     : Austin Horne
******************************************************************************
*/
import java.io.*;
import java.net.*;
 
 class UDPServer
 {
	public static void main(String args[]) throws Exception
	{
		DatagramSocket serverSocket;	//socket
		DatagramPacket receivePacket;	//packet to get data from clients
		DatagramPacket sendPacket;		//packet for sending data back to clients
		byte[] receiveData;				//receives data from receivePacket
		byte[] sendData;				//data to be encapsulated into sendPacket
		String sentence;				//sentence sent from client
		String capitalizedSentence;		//sentence from client in all caps
		String myName = "Austin";		//my name
		String unknown = "Unknown command.";	//used in default switch case
		InetAddress clientIP;			//IP address of client
		InetAddress serverIP;			//IP address of server
		int clientPort;					//client port number
		int serverPort;					//server port number
		
		//create datagram socket at port 9876
		serverSocket = new DatagramSocket(9876);
		
		System.out.println("Server is now operational"); //notify server started
		
		//continually loop, receiving datragrams from clients
		while(true)
		{
			sendData = new byte[1024];
			receiveData = new byte[1024];
			//created space for received datagram
			receivePacket = new DatagramPacket(receiveData, receiveData.length);
			
			//Receive datagram
			serverSocket.receive(receivePacket);
			
			//string to receive from client. Whitespace trimmed for comparison
			sentence = new String(receivePacket.getData());
			sentence = sentence.trim();
		
			//get IP address of client
			clientIP = receivePacket.getAddress();
			
			//port # of client
			clientPort = receivePacket.getPort();
			
			//IP address of server
			serverIP = serverSocket.getLocalAddress();
			
			//port # of server
			serverPort = serverSocket.getLocalPort();
			
			//convert client sentence to uppercase characters
			capitalizedSentence = sentence.toUpperCase();
			
			//return the requested information or "Unknown command" if query
			//was not recognized
			switch(capitalizedSentence)
			{
				case "NAME":
					capitalizedSentence = "Programmers name is: " + myName;
					break;
				case "SOCKET":
					capitalizedSentence = "Server IP: " + serverIP 
					+ ". Server port: " + serverPort + ".\nClient IP: " 
					+ clientIP + ". Client port: " + clientPort;
					break;
				case "SERVER IP":
					capitalizedSentence = "Server IP is: " + serverIP;
					break;
				case "SERVER PORT":
					capitalizedSentence = "Server port # is: " + serverPort;
					break;
				case "CLIENT IP":
					capitalizedSentence = "Client IP is: " + clientIP;
					break;
				case "CLIENT PORT":
					capitalizedSentence = "Client port # is: " + clientPort;
					break;
				default:
					capitalizedSentence = unknown;
					break;
			}//switch
								
			//set the bytes to the sentence to send as a packet
			sendData = capitalizedSentence.getBytes();
			
			//create datagram to send to client
			sendPacket 
				= new DatagramPacket(sendData, sendData.length,clientIP, clientPort);
				
			//write datagram to socket
			serverSocket.send(sendPacket);
		}//while
	
	}//main
}//class