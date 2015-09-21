package cs319;

// GOALS
//
// 1. to show sample Server code
// Note that the server is running on LOCALHOST (which is THIS computer) and the 
// port number associated with this server program is 4444.
// The accept() method just WAITS until some client program tries to access this server
//
// 2. to show how a thread is created to handle client request
//

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Lab3Server {

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = null;
		int clientNum = 0; // keeps track of how many clients were created

		// 1. CREATE A NEW SERVERSOCKET
		try {
			serverSocket = new ServerSocket(4444); // provide MYSERVICE at port
													// 4444
			System.out.println(serverSocket);
		} catch (IOException e) {
			System.out.println("Could not listen on port: 4444");
			System.exit(-1);
		}

		// 2. LOOP FOREVER - SERVER IS ALWAYS WAITING TO PROVIDE SERVICE!
		while (true) {
			Socket clientSocket = null;
			try {

				// 2.1 WAIT FOR CLIENT TO TRY TO CONNECT TO SERVER
				System.out.println("Waiting for client " + (clientNum + 1) + " to connect!");
				clientSocket = serverSocket.accept();

				// 2.2 SPAWN A THREAD TO HANDLE CLIENT REQUEST
				System.out.println("Server got connected to a client" + ++clientNum);
				Thread t = new Thread(new ClientHandler1(clientSocket, clientNum));
				t.start();

			} catch (IOException e) {
				System.out.println("Accept failed: 4444");
				System.exit(-1);
			}

			// 2.3 GO BACK TO WAITING FOR OTHER CLIENTS
			// (While the thread that was created handles the connected client's
			// request)

		} // end while loop

	} // end of main method

} // end of class MyServer

class ClientHandler1 implements Runnable {
	Socket s; // this is socket on the server side that connects to the CLIENT
	int num; // keeps track of its number just for identifying purposes

	ClientHandler1(Socket s, int n) {
		this.s = s;
		num = n;
	}

	// This is the client handling code
	public void run() {
		Scanner in;
		
		try {
			// 1. USE THE SOCKET TO READ WHAT THE CLIENT IS SENDING
			in = new Scanner(s.getInputStream()); 
			String clientMessage = in.nextLine();
			
			try {
				//if we got the remove flag
				if(clientMessage.contains("r3m0ve ")){
					
					//remove the r3m0ve flag
					StringBuilder message = new StringBuilder(clientMessage);
					message.delete(0, 7);
					
					System.out.println("Removing " + message.toString());
					
					//remove it from the txt file using DataModel
					DataModel data = new DataModel();
					data.removeComp(message.toString());
					data = null;
					
				}
				else{
					//write the message that was sent to the end of the file
					PrintWriter out = new PrintWriter(new FileWriter("companies.txt",true));
					out.println();
					out.append(clientMessage);
					out.close();
				}
			} 
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} 
			catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// This handling code dies after doing all the printing
	} // end of method run()

} // end of class ClientHandler
