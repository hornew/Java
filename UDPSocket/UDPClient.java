 /*****************************************************************************
 * File Name       : UDPClient.java
 * Purpose         : Opens a socket with the UDPServer and allows query of the
 *					 server.  Gets queries from the client and sends to the
 *					 server through the UDP socket until Quit command is 
 *					 entered.  Prints the response send back from the server.
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
 
 class UDPClient
 {
	public static void main(String args[]) throws Exception
	{
		String sentence;				//user query
		byte[] sendData;				//data to send to the server
		byte[] receiveData;				//data received from the server
		InetAddress IPAddress;			//IP address of the server
		DatagramSocket clientSocket;	//UDP socket opened with the server
		BufferedReader inFromUser;		//keyboard input stream
		DatagramPacket sendPacket;		//packet to send to server
		DatagramPacket receivePacket;	//packet received from server
		String modifiedSentence;		//sentence received from server
		
		//Create input stream
		inFromUser = new BufferedReader(new InputStreamReader(System.in));
					
		clientSocket = new DatagramSocket(); //create client socket
		
		//Translate hostname to IP address using DNS
		IPAddress = InetAddress.getByName("localhost");
			
		//prompt for command from user
		System.out.println("Enter command: ");
		sentence = inFromUser.readLine(); //get line from keyboard
				
		while(!sentence.equalsIgnoreCase("quit"))
		{
			//allocate bytes for send and receive data
			sendData = new byte[1024];
			receiveData = new byte[1024];
			
			sendData = sentence.getBytes(); //return input as sequence of bytes
		
			//create datagram with data to send, length, IP address, and port
			sendPacket 
				= new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
				
			clientSocket.send(sendPacket); //send datagram to server
		
			//create datagram for data to receive from server
			receivePacket = new DatagramPacket(receiveData, receiveData.length);
				
			clientSocket.receive(receivePacket); //read datagram from server
		
			//convert datagram to a String
			modifiedSentence = new String(receivePacket.getData());
		
			//print the information returned from the server. Trimmed for readability.
			System.out.println("FROM SERVER: " + modifiedSentence.trim());
			
			//prompt for command from user
			sentence = null;
			System.out.println("\nEnter command: ");
			sentence = inFromUser.readLine(); //get line from keyboard
		}//while
		clientSocket.close(); //close the UDP socket with the server
		
	}//main
}//class