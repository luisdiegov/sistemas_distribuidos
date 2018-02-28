/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectAalpha;

import tcpsockets.*;
import java.net.*;
import java.io.*;

public class TCPClientThread extends Thread{
    public String message;
    public Won won;
    
    TCPClientThread(String msg, Won w){
        message = msg;
        won = w;
    }
        

    public  void run () {

	Socket s = null;
	    try {
	    	int serverPort = 7896;
	   	
                s = new Socket("localhost", serverPort);    
             //   s = new Socket("127.0.0.1", serverPort);    
		DataInputStream in = new DataInputStream( s.getInputStream());
		DataOutputStream out =
			new DataOutputStream( s.getOutputStream());
		out.writeUTF(message);        	// UTF is a string encoding 
                
		String data = in.readUTF();
                if(Boolean.valueOf(data)){
                    won.setWon(true);
                }
                System.out.println("Received: "+ data) ;      
       	    } 
            catch (UnknownHostException e) {
		System.out.println("Sock:"+e.getMessage()); 
	    }
            catch (EOFException e) {
                System.out.println("EOF:"+e.getMessage());
    	    } 
            catch (IOException e) {
                System.out.println("IO:"+e.getMessage());
            } finally {
                if(s!=null) 
                    try {
                        s.close();
                    } catch (IOException e){
                    System.out.println("close:"+e.getMessage());}
                    }
            }
}
