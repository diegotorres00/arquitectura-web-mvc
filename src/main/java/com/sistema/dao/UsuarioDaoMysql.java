package com.sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.sistema.modelo.Usuario;
import com.sistema.util.ConexionMysql;

public class UsuarioDaoMysql implements UsuarioDao {

    private static final String INSERT_SQL = "INSERT INTO usuario (nombre, email, pais) VALUES (?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM usuario WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM usuario";
    private static final String DELETE_SQL = "DELETE FROM usuario WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE usuario SET nombre = ?, email = ?, pais = ? WHERE id = ?";

    @Override
    public void insertar(Usuario usuario) {
        try (Connection con = ConexionMysql.getConexion().getCon();
                PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPais());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario seleccionar(int id) {
        Usuario usuario = null;
        try (Connection con = ConexionMysql.getConexion().getCon();
                PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"),
                        rs.getString("pais"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public List<Usuario> seleccionarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection con = ConexionMysql.getConexion().getCon();
                PreparedStatement ps = con.prepareStatement(SELECT_ALL);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"),
                        rs.getString("pais")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public void actualizar(Usuario usuario) {
        try (Connection con = ConexionMysql.getConexion().getCon();
                PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPais());
            ps.setInt(4, usuario.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        try (Connection con = ConexionMysql.getConexion().getCon();
                PreparedStatement ps = con.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}