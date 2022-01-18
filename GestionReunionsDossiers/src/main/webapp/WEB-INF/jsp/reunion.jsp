<%-- 
    Document   : reunion
    Created on : 11 avr. 2021, 18 h 02 min 07 s
    Author     : booska
--%>

<%@page import="com.ghilas.entites.Reunion"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ListIterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <div>
            <h1>${reunion.titre}</h1>   
            <p>${reunion.description}</p>
        </div>
        <div>
            <a class="nav-link" href="./ajoutReunion">
                <p class=" btn btn-primary active"  >Ajouter</p>
            </a>
            <p class=" btn btn-primary active"  >Supprimer</p>
        </div>
        <div>
            <h1>Point d'ordre</h1>
        </div>
        <div class="container">
            <div class="row"> 
                <c:if test="${empty reunion.listeDesPoints}">
                    <h3>Aucun point d'ordre</h3>
                </c:if>
                <c:if test="${reunion.listeDesPoints.size()>0}">
                    <table class="table table-striped table-bordered">
                        <c:forEach items="${reunion.listeDesPoints}" var="pointDordre" >
                            <tr>
                                <a href="/${pointDordre.nom}" >
                                    <td>${pointDordre.nom}</td>
                                </a>
                                <td>${pointDordre.description}</td>
                                <td>${pointDordre.date}</td>
                                <td>${pointDordre.comptresRenus}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
                <p class=" btn btn-primary active"  >Ajouter</p>
                <p class=" btn btn-primary active"  >Modifier</p>
            </div>
        </div>
        <div class="container">
            <div class="row"> 
                <c:if test="${empty reunion.membres}">
                    <h3>Aucun membre</h3>
                </c:if>
                <c:if test="${reunion.membres.size()>0}">
                    <table class="table table-striped table-bordered">
                        <c:forEach items="${reunion.membres}" var="membre" >
                            <tr>
                                <a href="/${membre.nom}" >
                                    <td>${pointDordre.nom}</td>
                                </a>
                                <td>${membre.courriel}</td>
                                <td>${membre.telephone}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </div>
        <jsp:include page="pieddepage.jsp" />
    </body>
</html>
