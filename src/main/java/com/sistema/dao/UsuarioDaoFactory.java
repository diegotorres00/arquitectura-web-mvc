package com.sistema.dao;

public class UsuarioDaoFactory {
    public static UsuarioDao getUsuarioDao(String motor) {
        switch (motor.toLowerCase()) {
            case "mysql":
                return new UsuarioDaoMysql();
            // case "postgres": return new UsuarioDaoPostgres(); // Listo para el futuro
            default:
                return new UsuarioDaoMysql();
        }
    }
}