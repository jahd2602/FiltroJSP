<%-- 
    Document   : index
    Created on : 25/06/2013, 11:49:30 AM
    Author     : Sistemas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="datos" scope="application" class="paw.honorio.BeanDatos"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consejero Electronico</title>
    </head>
    <body>
    <center>
        <h1>Consejero Electronico</h1>

        <form action="/honorio/servlet/Filtrar" style="border-style: ridge; width: 500px">

            <strong>Filtar por</strong>
            <table><tr>
                    <td><input type="checkbox" name="chkCategoria" value="true" checked/>
                        Categoria:</td><td>
                        <select name="categoria">
                            <c:forEach var="categoria" items="${datos.categorias}">
                                <option value="${categoria.id}">${categoria.nombre}</option>
                            </c:forEach>
                        </select>
                    </td></tr><tr>
                    <td><input type="checkbox" name="chkMarca" value="true"/>
                        Marca:</td><td>
                        <select name="marca">
                            <c:forEach var="marca" items="${datos.marcas}">
                                <option value="${marca.id}">${marca.nombre}</option>
                            </c:forEach>
                        </select>
                    </td></tr></table>
            <input type="submit" value="Filtrar"/>
        </form>
        <c:if test="${not empty requestScope.Lista}">
            <div style="border-style: ridge; width: 480px;margin-top: 20px; padding: 10px">
            <table border="1">
                <thead>
                    <tr>
                        <th>
                            Categoria 
                        </th>
                        <th>
                            Marca
                        </th>
                        <th>
                            Modelo
                        </th>
                        <th>
                            Precio
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="producto" items="${requestScope.Lista}">
                        <tr>
                            <td>
                                ${producto.categoria}
                            </td>
                            <td>
                                ${producto.marca}
                            </td>
                            <td>
                                ${producto.modelo}
                            </td>
                            <td>
                                S/. ${producto.precio}
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
                </div>
        </c:if>
    </center>
</body>
</html>
