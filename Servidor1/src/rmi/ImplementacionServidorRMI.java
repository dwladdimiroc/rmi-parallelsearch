/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import hash.SHA1;
import interfacermi.InterfaceRMI;
import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class ImplementacionServidorRMI extends UnicastRemoteObject implements InterfaceRMI {

    Logger logueador; //Este objeto funciona como println
    static TreeMap<String, String> files = new TreeMap<String, String>();
    static int numero = 0;

    public ImplementacionServidorRMI() throws RemoteException {
        logueador = Logger.getLogger(getClass().getName()); // Indico que quiero utilizar este logger para la clase
        logueador.log(Level.INFO, "Instanciando la clase que implementa las funciones remotas");
    }

    @Override
    public String busquedaArchivo(String nombre) throws RemoteException {
        String urlFile;

        SHA1 hash = new SHA1();

        String key = null;
        try {
            key = hash.transformSHA1(nombre);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ImplementacionServidorRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        urlFile = files.get(key);
        
        return urlFile;

    }

    @Override
    public void actualizarArbol() throws RemoteException {
        ArrayList<String> dirFiles;
        String dir = new String("/home/daniel/Descargas/");
        dirFiles = analisisRecursivo(dir);
        SHA1 hash = new SHA1();
        for (String dirFile : dirFiles) {
            try {
                files.put(hash.transformSHA1(dirFile), dirFile);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ImplementacionServidorRMI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<String> analisisRecursivo(String directory) {
        File f;
        File[] paths = null;
        ArrayList<String> files = new ArrayList();

        try {
            // create new file
            f = new File(directory);

            // returns pathnames for files and directory
            paths = f.listFiles();

            // for each pathname in pathname array
            for (File path : paths) {
                if (path.isDirectory()) {
                    files.addAll(analisisRecursivo(path.toString()));
                } else {
                    files.add(path.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return files;
    }
}
