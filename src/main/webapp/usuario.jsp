<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Formulario Usuario</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        </head>

        <body>
            <nav class="navbar navbar-dark bg-dark">
                <a class="navbar-brand" href="list">Sistema Usuarios MVC</a>
            </nav>
            <div class="container mt-4">
                <div class="card w-50 mx-auto">
                    <div class="card-header">
                        <c:if test="${usuario != null}">Editar Usuario</c:if>
                        <c:if test="${usuario == null}">Agregar Nuevo Usuario</c:if>
                    </div>
                    <div class="card-body">
                        <c:if test="${usuario != null}">
                            <form action="update" method="post">
                                <input type="hidden" name="id" value="<c:out value='${usuario.id}' />" />
                        </c:if>
                        <c:if test="${usuario == null}">
                            <form action="insert" method="post">
                        </c:if>
                        <div class="form-group">
                            <label>Nombre</label>
                            <input type="text" value="<c:out value='${usuario.nombre}' />" class="form-control"
                                name="nombre" required>
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" value="<c:out value='${usuario.email}' />" class="form-control"
                                name="email" required>
                        </div>
                        <div class="form-group">
                            <label>País</label>
                            <input type="text" value="<c:out value='${usuario.pais}' />" class="form-control"
                                name="pais" required>
                        </div>
                        <button type="submit" class="btn btn-success">Guardar</button>
                        <a href="list" class="btn btn-secondary">Cancelar</a>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>