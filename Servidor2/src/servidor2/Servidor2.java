/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor2;

import interfacermi.InterfaceRMI;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import rmi.ImplementacionServidorRMI;
import rmi.ServidorRMI;

/**
 *
 * @author daniel
 */
public class Servidor2 extends JFrame implements ActionListener {

    private Timer timer;
    JButton botonSwitchEncender;
    JButton botonSwitchApagar;

    public Servidor2() {
        setLayout(null);
        botonSwitchEncender = new JButton("Encender");
        botonSwitchEncender.setBounds(20, 20, 180, 60);
        add(botonSwitchEncender);
        botonSwitchEncender.addActionListener(this);

        setLayout(null);
        botonSwitchApagar = new JButton("Apagar");
        botonSwitchApagar.setBounds(20, 100, 180, 60);
        add(botonSwitchApagar);
        botonSwitchApagar.addActionListener(this);

        this.setTitle("Servidor 2");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonSwitchEncender) {
            activarServidor();
        }
        if (e.getSource() == botonSwitchApagar) {
            desconectarServidor();
        }
    }

    public static void main(String[] args) {
        Servidor2 panel = new Servidor2();
        panel.setBounds(650, 350, 250, 250);
        panel.setVisible(true);
    }

    public static ServidorRMI server;
    public static int puertoEscucha = 4001;
    public static ImplementacionServidorRMI objetoLocal;
    public static String nombreReferenciaRemota = "Implementacion";

    /**
     * **** Función principal *****
     */
    public void activarServidor() {

        //Instancio la clase que puede ser llamada remotamente
        try {
            objetoLocal = new ImplementacionServidorRMI();
        } catch (RemoteException re) {
            System.out.println(re.getMessage()); //Muestro el error
        }

        /*
         * Se deja disponible hacia la red la utilización del objeto remoto
         */
        System.out.println("Se va a conectar...");
        server = new ServidorRMI();

        boolean resultadoConexion = server.iniciarConexion(objetoLocal, nombreReferenciaRemota, puertoEscucha);
        if (resultadoConexion) {
            System.out.println("Se ha establecido la conexión correctamente");
        } else {
            System.out.println("Ha ocurrido un error al conectarse");
        }

        System.out.println("El servidor esta con conexion: " + server.isConectado());

        timer = new Timer();
        timer.schedule(new actualizarArbol(), 0, 15 * 60 * 1000);
    }

    public void desconectarServidor() {
        try {
            //bd.desconectar();
            server.detener(nombreReferenciaRemota);
        } catch (RemoteException re) {
            System.out.println(re.getMessage()); //Muestro el error
        }
        System.out.println("El servidor esta con conexion: " + server.isConectado());
        
        timer.cancel(); 
    }

    class actualizarArbol extends TimerTask {
        @Override
        public void run() {
            ImplementacionServidorRMI interfacermi;
            try {
                interfacermi = new ImplementacionServidorRMI();
                interfacermi.actualizarArbol();
                System.out.println("Actualización del árbol");
            } catch (RemoteException ex) {
                Logger.getLogger(Servidor2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
