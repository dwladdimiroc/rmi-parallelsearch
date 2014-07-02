/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfacermi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author daniel
 */
public interface InterfaceRMI extends Remote {
    public String busquedaArchivo(String nombre) throws RemoteException;
    public void actualizarArbol() throws RemoteException;
}
