/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Antonio Jose Herrera Tabaco
 */
public class Aparcamiento {

    private static final String controlador = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost/aparcamiento";
    private static final String usuario = "root";
    private static final String clave = "";

    static {
        try {
            Class.forName(controlador);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading JDBC driver"
                    + " class. Cause: " + e);
        }

    }

    public static List<Coche> listadoCoches() throws SQLException {
        List<Coche> listaCoches = new ArrayList<Coche>();
        
        Connection conexion = DriverManager.getConnection(url,usuario,clave);
        
        Statement st = (Statement) conexion.createStatement();
        
        ResultSet resultado = st.executeQuery("select * from coche");

        while(resultado.next()){
        Coche coche = new Coche(resultado.getString("matricula"), resultado.getString("modelo"), resultado.getTime("horaEntrada"), resultado.getTime("horaSalida"), resultado.getInt("tiempoPermitido"));
        listaCoches.add(coche);
        }
        
        resultado.close();
        st.close();
        conexion.close();
        return listaCoches;
    }
}
