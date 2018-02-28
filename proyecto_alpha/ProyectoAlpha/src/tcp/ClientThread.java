/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

/**
 *
 * @author sdist
 */
public class ClientThread extends Thread{
    
    public ClientThread(){
        
    }
    
    public void run(){

	Socket s = null;
	    try {
	    	int serverPort = 7896;
	   	
                s = new Socket("localhost", serverPort);    
             //   s = new Socket("127.0.0.1", serverPort);    
		DataInputStream in = new DataInputStream( s.getInputStream());
		DataOutputStream out =
			new DataOutputStream( s.getOutputStream());
                
                for(int i=1; i<50; i++){
                    long startTime = System.currentTimeMillis();
                    Random r = new Random();
                    int Low = 0;
                    int High = 5;
                    int Result = r.nextInt(High-Low) + Low;
                    System.out.println(Result);
                    out.write(Result);
//                    out.writeUTF("Hello");        	// UTF is a string encoding 

                    String data = in.readUTF();	      
                    System.out.println("Received: "+ data) ;
                    long spentTime = System.currentTimeMillis()-startTime;
//                    System.out.print(spentTime + ",");
                }
                
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
