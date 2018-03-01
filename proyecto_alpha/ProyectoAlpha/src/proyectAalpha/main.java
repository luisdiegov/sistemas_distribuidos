/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectAalpha;

/**
 *
 * @author ldv
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Mole launcher, runs forever
        MoleLauncherThread moleThread = new MoleLauncherThread();
        moleThread.start(); //Multicast UDP socket sender
        
        //TCPServer
        TCPServerThread tcpsr = new TCPServerThread();
        tcpsr.start();
        
        //Client(s)
        int clientNum = 3;
        for(int i=0; i<clientNum; i++){
            ClientThread ct = new ClientThread();
            ct.start(); //Multicast UDP socket receiver
        }
        
    }
    
}
