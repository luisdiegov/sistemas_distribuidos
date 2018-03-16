/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectAalpha;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ldv
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NotBoundException {
        try {
            // TODO code application logic here
            // Getting LOKO
            
            //System.setProperty("java.security.policy","file:/C:/Users/sdist/Documents/NetBeansProjects/proyectAalpha/src/proyectAalpha/proyectAalpha.policy");
            


            System.setProperty("java.security.policy", "file:/C:\\Users\\LBRENESV\\sistemas_distribuidos\\proyecto_alpha\\ProyectoAlpha\\src\\proyectAalpha\\proyectAalpha.policy");

            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            String name = "RmiService";

            Registry registry;
            registry = LocateRegistry.getRegistry("localhost");

            RmiService rmi = (RmiService) registry.lookup(name);
            registry.rebind(name, rmi);
            Credencial credencial = new Credencial("lespam", "123");

            System.out.println("resultado:"+rmi.login(credencial));
            /*
            //Class that is going to be passed as a pointer to maintain comunication
            //between threads
            Counter counter = new Counter();

            //Mole launcher, runs forever
            MoleLauncherThread moleThread = new MoleLauncherThread(counter);
            moleThread.start(); //Multicast UDP socket sender

            //TCPServer
            TCPServerThread tcpsr = new TCPServerThread(counter);
            tcpsr.start();

            //Client(s)
            int clientNum = 1;
            for (int i = 0; i < clientNum; i++) {
                ClientThread ct = new ClientThread(i);
                ct.start(); //Multicast UDP socket receiver
            }
            */
        } catch (RemoteException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
