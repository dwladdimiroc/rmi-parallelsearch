/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import interfacermi.InterfaceRMI;
import java.rmi.RemoteException;
import rmi.ConexionRMI;

/**
 *
 * @author daniel
 */
public class Cliente {

    public static int puerto1 = 4000; // Buscar Puertos abiertos
    public static int puerto2 = 4001; // Buscar Puertos abiertos    
    public static String direccionServer1 = "localhost"; //Por ahora es localhost se tendria que cambiar a la IP del servidor RMI
    public static String direccionServer2 = "localhost"; //Por ahora es localhost se tendria que cambiar a la IP del servidor RMI
    public static String nombreReferenciaRemota = "Implementacion"; // Nombre de el objeto subido
    
    private int serverSearch = 0;

    public String busquedaParalela(String busqueda) {

        InterfaceRMI objetoRemoto;
        String dirArchivo1, dirArchivo2;
        long timeStart, timeEnd;

        timeStart = System.currentTimeMillis();
        //Se instancia el objeto que conecta con el servidor
        ConexionRMI conexion1 = new ConexionRMI();
        try {
            //Se conecta con el servidor
            conexion1.iniciarRegistry(direccionServer1, puerto1, nombreReferenciaRemota);

            //Se obtiene la referencia al objeto remoto
            objetoRemoto = conexion1.getServidor();

            //Este método se ejecuta en el servidor
            dirArchivo1 = objetoRemoto.busquedaArchivo(busqueda);

        } catch (RemoteException e) {
            System.out.println("Ha ocurrido un error");
            return null;
        }
        timeEnd = System.currentTimeMillis();
        long timeServer1 = timeEnd - timeStart;

        timeStart = System.currentTimeMillis();
        //Se instancia el objeto que conecta con el servidor
        ConexionRMI conexion2 = new ConexionRMI();
        try {
            //Se conecta con el servidor
            conexion2.iniciarRegistry(direccionServer2, puerto2, nombreReferenciaRemota);

            //Se obtiene la referencia al objeto remoto
            objetoRemoto = conexion2.getServidor();

            //Este método se ejecuta en el servidor
            dirArchivo2 = objetoRemoto.busquedaArchivo(busqueda);

        } catch (RemoteException e) {
            System.out.println("Ha ocurrido un error");
            return null;
        }
        timeEnd = System.currentTimeMillis();
        long timeServer2 = timeEnd - timeStart;

        if (timeServer1 < timeServer2) {
            serverSearch = 1;
            return dirArchivo1;
        } else {
            serverSearch = 2;
            return dirArchivo2;
        }
    }

    public String linkDescarga(String directorio) {
        InterfaceRMI objetoRemoto;
        String urlArchivo;

        //Se instancia el objeto que conecta con el servidor
        ConexionRMI conexion = new ConexionRMI();
        try {
            //Se conecta con el servidor
            if(serverSearch==1){
                conexion.iniciarRegistry(direccionServer1, puerto1, nombreReferenciaRemota);
            }
            else{
                conexion.iniciarRegistry(direccionServer2, puerto2, nombreReferenciaRemota);
            }

            //Se obtiene la referencia al objeto remoto
            objetoRemoto = conexion.getServidor();

            //Este método se ejecuta en el servidor
            urlArchivo = objetoRemoto.linkearArchivo(directorio);

        } catch (RemoteException e) {
            System.out.println("Ha ocurrido un error");
            return null;
        }

        return urlArchivo;
    }

}
