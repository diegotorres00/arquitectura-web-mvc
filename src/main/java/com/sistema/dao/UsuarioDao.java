package com.sistema.dao;

import java.util.List;
import com.sistema.modelo.Usuario;

public interface UsuarioDao {
    void insertar(Usuario usuario);

    Usuario seleccionar(int id);

    List<Usuario> seleccionarTodos();

    void actualizar(Usuario usuario);

    void eliminar(int id);
}