<%-- 
    Document   : listeDossiers
    Created on : 4 avr. 2021, 00 h 34 min 10 s
    Author     : booska
--%>

<%@page import="com.ghilas.entites.Dossier"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ListIterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="resources/style/bootstrap413/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
        <link href='resources/style/style.css' rel="stylesheet" type="text/css" />
    </head>
    <body>
        <jsp:include page="entete.jsp" />
        
        <div class="container">
            <div class="row"> 
                <c:if test="${empty data}">
                    <h3>Aucuns Dossiers</h3>
                </c:if>                   
                <c:if test="${data.size()>0}">
                <div class="soustitre2" >
                    <h1>Liste de tous les dossiers</h1>  
                </div>
                <table class="table table-striped table-bordered">
                    <tr>
                        <th>Titre</th>
                        <th>Description</th>
                        <th>Date</th>
                        <th>
                            <c:if test="${fn:contains(statutMembre, 'Chef')}">
                                <a class="nav-link" href="./ajoutDossier">
                                    <span class=" btn btn-primary active"  >Ajouter Dossier</span>
                                </a>
                            </c:if>
                        </th>
                    </tr>
                    <c:forEach items="${data}" var="dossier" >
                        <tr>
                            <td>${dossier.titre}</td>
                            <td>${dossier.description}</td>
                            <td>${dossier.date}</td>
                            <td style="width: 140px">
                                <a href="./detailsDossier?idDossier=${dossier.idDossier}">
                                    <span  class="nav-link btn btn-primary active" >Voir Dossier</span>
                                </a>
                             </td>
                        </tr>
                    </c:forEach>
                </table>
                </c:if>
          </div>
        </div>
        <jsp:include page="pieddepage.jsp" />
    </body>
</html>
