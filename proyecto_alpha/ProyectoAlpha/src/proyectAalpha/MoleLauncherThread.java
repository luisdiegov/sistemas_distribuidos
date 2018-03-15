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
public class MoleLauncherThread extends Thread {
    
    private int roundNumber = 0;
    private Counter counter;
    
    public MoleLauncherThread(Counter counter){
        this.counter = counter;
    }
    
    public void run(){
        	MulticastSocket s =null;
   	 try {
                
                InetAddress group = InetAddress.getByName("228.5.6.7"); // destination multicast group 
	    	s = new MulticastSocket(6789);
	   	s.joinGroup(group); 
                //s.setTimeToLive(10);
                System.out.println("Messages' TTL (Time-To-Live): "+ s.getTimeToLive());
                
                Random rand = new Random();
                int target;
                String myMessage;
                int numOfRounds = 100;
                int i =0;
                while(i<numOfRounds){
                    //Generates int between 1-9, roundNumber, hasWon, winner
                    myMessage = String.valueOf(rand.nextInt(9-1) + 1) + " " + String.valueOf(roundNumber) + 
                            " " + counter.getWon() + " " + counter.getWinner() + " control";
                    System.out.println("Message "+myMessage);
                    byte [] m = myMessage.getBytes();
                    DatagramPacket messageOut = 
                            new DatagramPacket(m, m.length, group, 6789);
                    s.send(messageOut);
                    roundNumber++;
                    
                    if(counter.getWon()){
                        counter.reset();
                    }else{                    
                    //Thread.sleep(2000);
                    }
                    i++;
                }
                
                System.out.println("Terminan de enviarse mensajes");
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
    
}
