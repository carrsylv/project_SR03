<%@ page import="beans.ProfesseurEntity" %>
<%--
  Created by IntelliJ IDEA.
  User: Sylvain
  Date: 21/04/2017
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.List"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>professeur</title>
</head>
<body>

    <p>Ceci est une page générée depuis une JSP.</p>

    <table border="1">
        <tr>
            <th>Id</th>
            <th>Nom</th>
            <th>Prenom</th>
            <th>Mail</th>
        </tr>
        <%
            Object obj = request.getAttribute("listeProf");
            if(obj!=null){
                List<ProfesseurEntity> lu = (List<ProfesseurEntity>)obj;
                for(ProfesseurEntity u : lu){
        %>
        <tr>
            <td><%=u.getId()%></td>
            <td><%=u.getNom()%></td>
            <td><%=u.getPrenom()%></td>
            <td><%=u.getMail()%></td>
        </tr>
        <%
                }
            }
        %>
    </table>

    <%
        obj = request.getAttribute("prof");
        if(obj != null){
            ProfesseurEntity prof = (ProfesseurEntity) obj;
            %> <p>Résultat de la recherche par adresse mail :
            <% out.println(prof.getId() + " " + prof.getNom() + " " +  prof.getPrenom() + " " + prof.getMail());%>
            </p> <%
        }
    %>

</body>
</html>
