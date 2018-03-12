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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ldv
 */


public class TCPServerThread extends Thread{
    
////    public int[] counter = new int[10];
    private Counter counter;// Class that mainains registry  
   
    public TCPServerThread(Counter counter){
        this.counter = counter;
    }
    
    public void run () {
	try{
		int serverPort = 7896; 
		ServerSocket listenSocket = new ServerSocket(serverPort);
//                Counter counter = new Counter(); //Score register
                
                
		while(true) {
			System.out.println("Waiting for messages...TCP"); 
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
        boolean hasWon;
//        Counter counter = new Counter();
        
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
                    counter.verifyWinner(Integer.valueOf(msg[0].trim()), Integer.valueOf(msg[1].trim()));
//                    System.out.println("ROUND " + msg[1]);
                    hasWon = counter.hasWon(Integer.valueOf(msg[0]));
                    data = String.valueOf(hasWon);
//                      data = String.valueOf(true);
                }
                
                System.out.println("Message received from: " + clientSocket.getRemoteSocketAddress());
                
                
		out.writeUTF(data);
                
                //Do something here to initialize new round
                if(hasWon){
                    Thread.sleep(5000);
                    System.out.println("NUEVA RONDA");
                }
	    } 
            catch(EOFException e) {
                System.out.println("EOF:"+e.getMessage());
	    } 
            catch(IOException e) {
                System.out.println("IO:"+e.getMessage());
	    } catch (InterruptedException ex) {
                Logger.getLogger(TCPServerThread.class.getName()).log(Level.SEVERE, null, ex);
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