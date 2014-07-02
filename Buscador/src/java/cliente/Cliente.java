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

    public static int puerto = 4000; // Buscar Puertos abiertos
    public static String direccionServer1 = "localhost"; //Por ahora es localhost se tendria que cambiar a la IP del servidor RMI
    public static String nombreReferenciaRemota = "Implementacion"; // Nombre de el objeto subido
    public int datosConexion=0;

    public int busquedaParalela() {

        InterfaceRMI objetoRemoto;

        //Se instancia el objeto que conecta con el servidor
        ConexionRMI conexion = new ConexionRMI();
        try {
            //Se conecta con el servidor
            conexion.iniciarRegistry(direccionServer1, puerto, nombreReferenciaRemota);

            //Se obtiene la referencia al objeto remoto
            objetoRemoto = conexion.getServidor();

            //Este método se ejecuta en el servidor
            datosConexion = objetoRemoto.busquedaArchivo();

        } catch (RemoteException e) {
            System.out.println("Ha ocurrido un error");
            return 0;
        }

        return datosConexion;
    }
}
