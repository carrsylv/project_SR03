<%@ page import="beans.UvEntity" %>

<%--
  Created by IntelliJ IDEA.
  User: Sylvain
  Date: 21/04/2017
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.List"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Uv</title>
</head>
<body>

    <p>Ceci est une page générée depuis une JSP.</p>

    <table border="1">
        <tr>
            <th>Id</th>
            <th>Identifiant</th>
            <th>Description</th>
        </tr>
        <%
            Object obj = request.getAttribute("listeUv");
            if(obj!=null){
                List<UvEntity> lu = (List<UvEntity>)obj;
                for(UvEntity u : lu){
        %>
        <tr>
            <td><%=u.getId()%></td>
            <td><%=u.getIdentifiant()%></td>
            <td><%=u.getDescription()%></td>
        </tr>
        <%
                }
            }
        %>
    </table>

    <%
        obj = request.getAttribute("uv");
        if(obj != null){
            UvEntity prof = (UvEntity) obj;
    %> <p>Résultat de la recherche par adresse mail :
        <% out.println(prof.getId() + " " + prof.getIdentifiant());%>
    </p> <%
        }
    %>

</body>
</html>
