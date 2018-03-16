/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectAalpha;

import tcpsockets.*;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClientThread extends Thread{
    public String message = " ";
    public Won won;
    public FileWriter fileWriter;
    public BufferedWriter bufferedWriter;
    
    TCPClientThread(Won w, BufferedWriter bufferedWriter) throws IOException{
        fileWriter = new FileWriter("pruebas.txt");
        this.bufferedWriter = bufferedWriter;
        won = w;
    }

    public void setMessage(String message) {
        this.message = message;
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
                String tiempo="";
                long startTime = System.currentTimeMillis();
		out.writeUTF(message);        	// UTF is a string encoding 
                //System.out.println("Sale mensaje TCP");
		String data = in.readUTF();
                long spentTime = System.currentTimeMillis() - startTime;
                tiempo = tiempo+""+spentTime;
                //System.out.println(tiempo+" Pruebas") ;
                bufferedWriter.newLine();
                bufferedWriter.write(tiempo);
                //System.out.println("Llega mensaje TCP");
                if(Boolean.valueOf(data)){
                    won.setWon(true);
                }
                //System.out.println("Received: "+ data) ;
                if(data.equals("END"))
                    bufferedWriter.close();
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
