/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package others;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import proyectAalpha.MoleGrid;

/**
 *
 * @author JGUTIERRGARC
 */
public class MulticastReceivingPeer {
    	public static void main(String args[]){ 
  	 
	MulticastSocket s =null;
   	 try {
                InetAddress group = InetAddress.getByName("228.5.6.7"); // destination multicast group 
	    	s = new MulticastSocket(6789);
	   	s.joinGroup(group); 
                MoleGrid mg = new MoleGrid();
                mg.setVisible(true);
                String message;
                int cell;

	    	byte[] buffer = new byte[1000];
 	   	for(int i=0; i< 10; i++) {
                    System.out.println("Waiting for messages");
                    DatagramPacket messageIn = 
			new DatagramPacket(buffer, buffer.length);
 		    s.receive(messageIn);
                    mg.reset();
                    message = new String(messageIn.getData());
                    cell = Integer.valueOf(message.trim());
                    mg.setCell(cell);
 		    System.out.println(message);
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
                 // get messages from others in group
}
