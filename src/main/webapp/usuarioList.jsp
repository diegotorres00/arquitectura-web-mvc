<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Gestión de Usuarios</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        </head>

        <body>
            <nav class="navbar navbar-dark bg-dark">
                <a class="navbar-brand" href="list">Sistema Usuarios MVC</a>
            </nav>
            <div class="container mt-4">
                <div class="row">
                    <div class="col-md-12">
                        <h3>Lista de Usuarios <a href="<%=request.getContextPath()%>/new"
                                class="btn btn-success float-right">Agregar Nuevo</a></h3>
                        <table class="table table-bordered table-striped mt-3">
                            <thead class="thead-dark">
                                <tr>
                                    <th>ID</th>
                                    <th>Nombre</th>
                                    <th>Email</th>
                                    <th>País</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="user" items="${listUsuarios}">
                                    <tr>
                                        <td>
                                            <c:out value="${user.id}" />
                                        </td>
                                        <td>
                                            <c:out value="${user.nombre}" />
                                        </td>
                                        <td>
                                            <c:out value="${user.email}" />
                                        </td>
                                        <td>
                                            <c:out value="${user.pais}" />
                                        </td>
                                        <td>
                                            <a href="edit?id=<c:out value='${user.id}' />"
                                                class="btn btn-sm btn-primary">Editar</a>
                                            <a href="delete?id=<c:out value='${user.id}' />"
                                                class="btn btn-sm btn-danger">Eliminar</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </body>

        </html>