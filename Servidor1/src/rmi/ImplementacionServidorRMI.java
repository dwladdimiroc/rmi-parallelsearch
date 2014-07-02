/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import interfacermi.InterfaceRMI;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class ImplementacionServidorRMI extends UnicastRemoteObject implements InterfaceRMI {

    Logger logueador; //Este objeto funciona como println
    static int i;

    public ImplementacionServidorRMI() throws RemoteException {

        logueador = Logger.getLogger(getClass().getName()); // Indico que quiero utilizar este logger para la clase
        logueador.log(Level.INFO, "Instanciando la clase que implementa las funciones remotas");
    }

    @Override
    public int busquedaArchivo() throws RemoteException {
        i++;
        return i;
    }
}
