package org.utl.dsm.examendos;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionMySQL {
    Connection conn;

    //url (localizador de recursos uniforme
    //TCP-IP
    public Connection open() {
        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://127.0.0.1:3306/db_bus?" +//protocolo:subprotocolo//servidor:puerto/subnombre o base de datos?Parametro
                "useSSL=false&" +//no se usan...
                "allowPublicKeyRetrieval=true&" +
                "useUnicode=true&" +
                "characterEncoding=utf-8";
        try {
            //registrar chofer
            Class.forName("com.mysql.cj.jdbc.Driver");//cargador de clase
            conn = DriverManager.getConnection(url, user, password);//Instanciando la clase que implementa el interfaz Driver
            //-ligne de code |v| Definir la propiedad jdbc.drivers
            //jdbc.drivers = gjt.mm.mysql.Driver:oracle.jdbc.driver.OracleDriver;
            System.out.println("Conectado\n");
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Cerrado");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Exception controlada...");
            }
        }
    }

}
