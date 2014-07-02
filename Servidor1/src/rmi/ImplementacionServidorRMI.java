/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import conexionmysql.ConexionBD;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import interfacermi.InterfaceRMI;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.InputSource;
import paginaweb.PaginaWeb;

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
