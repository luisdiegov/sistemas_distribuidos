/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectAalpha;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ldv
 */
public class ClientThread extends Thread{

    private int clientId;
    
    public ClientThread(int i){
        clientId = i;
    }
    
    public void run(){
     
  	 
	MulticastSocket s =null;
   	 try {
                InetAddress group = InetAddress.getByName("228.5.6.7"); // destination multicast group 
	    	s = new MulticastSocket(6789);
	   	s.joinGroup(group); 
                stressMoleGrid mg = new stressMoleGrid(clientId);//new MoleGrid(clientId);
                //mg.setVisible(true);
                String message;
                String tokenized[];
                int cell, roundNumber, winner;
                boolean hasWon;

	    	byte[] buffer = new byte[1000];
                //sends n answers
                int n = 100;
 	   	for(int i=0; i< n; i++) {
                    System.out.println("Waiting for messages");
                    DatagramPacket messageIn = 
			new DatagramPacket(buffer, buffer.length);
 		    s.receive(messageIn);
                    //mg.reset();
                    //mg.enableAll();
                    message = new String(messageIn.getData()); //cell, roundNumber, hasWon, winner
                    tokenized = message.split("\\s+");
//                    System.out.println("0: " + tokenized[0] + " 1: " + tokenized[1]
//                    + " 2: " + tokenized[2] + " 3: " + tokenized[3]);
//                    System.out.println("length " + tokenized.length);

                    cell = Integer.valueOf(tokenized[0]);
                    roundNumber = Integer.valueOf(tokenized[1].trim());
                    hasWon = Boolean.valueOf(tokenized[2].trim());
                    winner = Integer.valueOf(tokenized[3].trim());                    
                    
                    mg.setAnswer(cell);
                    mg.setRoundNo(roundNumber);
                    Random rand = new Random();
                    mg.selectCell(rand.nextInt(9-1) + 1);
//                    System.out.println(cell);
// 		    System.out.println(message);

                    if(hasWon){
                        //Thread.sleep(3000);
                    }
                    
  	     	}
	    	s.leaveGroup(group);		
 	    }
         catch (SocketException e){ 
             System.out.println("Socket: " + e.getMessage());
	 }
         catch (IOException e){
             System.out.println("IO: " + e.getMessage());
         }
	 finally {
            if(s != null) s.close();
        }
    }

    public int getClientId() {
        return clientId;
    }
    
    
    
}
