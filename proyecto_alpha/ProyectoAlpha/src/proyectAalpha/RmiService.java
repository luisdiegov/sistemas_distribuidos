/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectAalpha;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author LBRENESV
 */
public interface RmiService extends Remote{
    // starts GUI
    public boolean login ( Credencial credencial ) throws RemoteException;
}
