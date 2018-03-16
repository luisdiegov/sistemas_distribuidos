/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiserver;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectAalpha.Credencial;
import proyectAalpha.RmiService;

/**
 *
 * @author LBRENESV
 */
public class rmiServiceServer implements RmiService {

    public static void main(String[] args) {

        try {
            System.setProperty("java.security.policy", "file:/C:\\Users\\sdist\\sistemas_distribuidos\\proyecto_alpha\\ProyectoAlpha\\src\\rmiserver\\rmiserver.policy");
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            LocateRegistry.createRegistry(1099);

            String name = "RmiService";
            rmiServiceServer engine = new rmiServiceServer();
            RmiService stub = (RmiService) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("Servidor Desplegado");
        } catch (RemoteException ex) {
            Logger.getLogger(rmiServiceServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public rmiServiceServer() throws RemoteException {
        super();
    }

    @Override
    public String ip(Boolean bol) throws RemoteException {
        String ipe="";
        if(bol)
        {
            ipe="228.5.6.7";
        }
        return ipe;
    }

    @Override
    public String puertos(Boolean bol) throws RemoteException {
        String pto="";
        if(bol)
        {
            pto="7896";
        }
        return pto;
    }

}
