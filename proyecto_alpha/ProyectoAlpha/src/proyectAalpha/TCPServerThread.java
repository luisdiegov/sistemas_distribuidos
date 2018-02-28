/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectAalpha;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ldv
 */


public class TCPServerThread extends Thread{
    
    public int[] counter = new int[10];
    
    public TCPServerThread(){
        
    }
    
    public void run () {
	try{
		int serverPort = 7896; 
		ServerSocket listenSocket = new ServerSocket(serverPort);
                Counter counter = new Counter(); //Score register
                
                
		while(true) {
			System.out.println("Waiting for messages..."); 
                        Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made. 
			Connection c = new Connection(clientSocket);
                        c.start();
		}
	} catch(IOException e) {System.out.println("Listen :"+ e.getMessage());}
    }
   


class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
        Counter counter = new Counter();
        
	public Connection (Socket aClientSocket) {
	    try {
		clientSocket = aClientSocket;
		in = new DataInputStream(clientSocket.getInputStream());
		out =new DataOutputStream(clientSocket.getOutputStream());
                
	     } catch(IOException e)  {System.out.println("Connection:"+e.getMessage());}
	}
        
        @Override
	public void run(){
	    try {			                 // an echo server
		String data = in.readUTF();
                String[] msg = data.split("\\s+"); //client_id, round, answer
                data = "false";
                
                //if answer
                //if counter.getRound()[round] i.e. first to answer that round
                    //out a boolean hasWon
                //counter[client_id]++
                
                if(Boolean.valueOf(msg[2])){
                    counter.verifyWinner(Integer.valueOf(msg[0]), Integer.valueOf(msg[1]));
                    data = String.valueOf(counter.hasWon(Integer.valueOf(msg[0])));
                }
                
                System.out.println("Message received from: " + clientSocket.getRemoteSocketAddress());
                
		out.writeUTF(data);
	    } 
            catch(EOFException e) {
                System.out.println("EOF:"+e.getMessage());
	    } 
            catch(IOException e) {
                System.out.println("IO:"+e.getMessage());
	    } finally {
                try {
                    clientSocket.close();
                } catch (IOException e){
                    System.out.println(e);
                }
                }
            }
}
}