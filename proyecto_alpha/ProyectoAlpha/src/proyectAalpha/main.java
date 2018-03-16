/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectAalpha;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author ldv
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        // TODO code application logic here
        System.setProperty("java.net.preferIPv4Stack","true");
        //Class that is going to be passed as a pointer to maintain comunication
        //between threads
        Counter counter = new Counter();
        
        //Mole launcher, runs forever
        MoleLauncherThread moleThread = new MoleLauncherThread(counter);
        moleThread.start(); //Multicast UDP socket sender
        
        //TCPServer
        TCPServerThread tcpsr = new TCPServerThread(counter);
        tcpsr.start();
        
        // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter("prueba150_3.txt");

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);
        //Client(s)
        int clientNum = 150;
        for(int i=0; i<clientNum; i++){
            ClientThread ct = new ClientThread(i,bufferedWriter);
            ct.start(); //Multicast UDP socket receiver
        }
        
    }
    
}