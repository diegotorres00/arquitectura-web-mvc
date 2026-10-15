package com.sistema.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMysql {
    private static ConexionMysql db;
    private Connection conexion;

    private ConexionMysql() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/sistema?useSSL=false&serverTimezone=UTC", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConexionMysql getConexion() {
        if (db == null) {
            db = new ConexionMysql();
        }
        return db;
    }

    public Connection getCon() {
        return conexion;
    }

    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}