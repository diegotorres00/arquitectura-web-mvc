package com.sistema.controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sistema.dao.UsuarioDao;
import com.sistema.dao.UsuarioDaoFactory;
import com.sistema.modelo.Usuario;

@WebServlet("/")
public class UsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioDao usuarioDao;

    @Override
    public void init() throws ServletException {
        // Obtenemos el motor configurado en web.xml
        String motor = getServletContext().getInitParameter("motor_db");
        if (motor == null)
            motor = "mysql"; // Respaldo por defecto
        usuarioDao = UsuarioDaoFactory.getUsuarioDao(motor);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertUsuario(request, response);
                break;
            case "/delete":
                deleteUsuario(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateUsuario(request, response);
                break;
            default:
                listUsuarios(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void listUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario> listUsuarios = usuarioDao.seleccionarTodos();
        request.setAttribute("listUsuarios", listUsuarios);
        request.getRequestDispatcher("usuarioList.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("usuario.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuarioExistente = usuarioDao.seleccionar(id);
        request.setAttribute("usuario", usuarioExistente);
        request.getRequestDispatcher("usuario.jsp").forward(request, response);
    }

    private void insertUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String pais = request.getParameter("pais");
        usuarioDao.insertar(new Usuario(nombre, email, pais));
        response.sendRedirect("list");
    }

    private void updateUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String pais = request.getParameter("pais");
        usuarioDao.actualizar(new Usuario(id, nombre, email, pais));
        response.sendRedirect("list");
    }

    private void deleteUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        usuarioDao.eliminar(id);
        response.sendRedirect("list");
    }
}