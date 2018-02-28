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
    
    public MoleLauncherThread(){
        
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
                
                while(true){
                    myMessage = String.valueOf(rand.nextInt(9-1) + 1); //Generates int between 1-9
                    byte [] m = myMessage.getBytes();
                    DatagramPacket messageOut = 
                            new DatagramPacket(m, m.length, group, 6789);
                    s.send(messageOut);
                    Thread.sleep(3000);
                }
                

//	    	s.leaveGroup(group);		
 	    }
         catch (SocketException e){
             System.out.println("Socket: " + e.getMessage());
	 }
         catch (IOException e){
             System.out.println("IO: " + e.getMessage());
         } catch (InterruptedException ex) {
            Logger.getLogger(MoleLauncherThread.class.getName()).log(Level.SEVERE, null, ex);
        }
	 finally {
            if(s != null) s.close();
        }
    }
    
}