import java.io.*; 
import java.net.*; 

class TCPServer 
{ 

  public static void main(String argv[]) throws Exception 
    { 
      String clientSentence; 		//sentence received from client
      String capitalizedSentence;   //sentence sent back to client

      ServerSocket welcomeSocket = new ServerSocket(6789); 
  
      while(true) 
	  { 
  
            Socket connectionSocket = welcomeSocket.accept();
			InetAddress server = connectionSocket.getLocalAddress();
			InetAddress client = connectionSocket.getInetAddress();
			int clientPort = connectionSocket.getPort();
			int serverPort = connectionSocket.getLocalPort();
			String name = "programmer";
			String strClientPort = "client port";
			String strServerPort = "server port";
			String strClientIP = "client ip";
			String strServerIP = "server ip";
			
			//set to upper case for String comparison
			name = name.toUpperCase();
			strClientPort = strClientPort.toUpperCase();
			strServerPort = strServerPort.toUpperCase();
			strClientIP = strClientIP.toUpperCase();
			strServerIP = strServerIP.toUpperCase();

			//read in from client
            BufferedReader inFromClient = 
              new BufferedReader(new
              InputStreamReader(connectionSocket.getInputStream()));
			  
			  //output to client
			  DataOutputStream  outToClient = 
             new DataOutputStream(connectionSocket.getOutputStream()); 

			 //read in client String and convert to upper case for comparison
           clientSentence = inFromClient.readLine(); 
		   clientSentence = clientSentence.toUpperCase();
		   
		   
		   if(clientSentence.equals(strClientPort)) 
		   {
				capitalizedSentence = "Client port number is: " 
				+ clientPort  + '\n';
		   }
		   else if (clientSentence.equals(strServerPort))
		   {
				capitalizedSentence = "Sever port number is: "
				+ serverPort + '\n';
		   }
		   else if (clientSentence.equals(strClientIP))
		   {
				capitalizedSentence = "Client IP is: " 
				+ client.toString() + '\n'; //returns client ip in a string
		   }
		   else if (clientSentence.equals(strServerIP))
		   {
				capitalizedSentence = "Server IP is: " 
				+ server.toString() + '\n';	//returns server ip in a string
		   }
		   else if(clientSentence.equals(name))
		   {
				capitalizedSentence = "Programmer name: " 
				+ "Austin Horne"  + '\n';
		   }
		   else
		   {
				capitalizedSentence = clientSentence  + '\n';
		   }
		   //send message back to client
           outToClient.writeBytes(capitalizedSentence); 
        }//while 
    }//main
}//class
