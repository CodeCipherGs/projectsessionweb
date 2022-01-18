<%-- 
    Document   : dossier
    Created on : 11 avr. 2021, 18 h 01 min 55 s
    Author     : booska
--%>

<%@page import="com.ghilas.entites.Reunion"%>
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
        <div style="text-align: right">
            <c:if test="${fn:contains(statutMembre, 'Chef')}"> 
                <a class="nav-link" href="./ajoutPointDordre?idDossier=${detailsDossier.idDossier}">
                    <p class=" btn btn-primary active"  >Ajouter Point D'ordre</p>
                </a>
            </c:if>
        </div>
        <div class="container">

            <c:if test="${empty detailsDossier}">                
                <h3>Aucun Dossier</h3>
            </c:if>
            <c:if test="${not empty detailsDossier}">
                <div class="row"> 
                    <div class="col-sm-8">
                        <div class="afficherInfo">
                            <div class="col-sm-12 " style="min-height: 200px">
                                <div style="text-align: right" >
                                    <c:if test="${fn:contains(statutMembre, 'Chef')}"> 
                                        <a href="./modifierDossier?idDossier=${detailsDossier.idDossier}">
                                            <img src="resources/images/iconeModif1.png" width="25px"  alt="imageModif" >
                                        </a>
                                    </c:if>
                                </div>
                                <span>
                                    <p class="texteAfficher">Titre : ${detailsDossier.titre}</p>
                                    <p>Date : ${detailsDossier.date} <p>
                                    <p>Statut :
                                    <c:if test="${detailsDossier.isActive eq 1}"> 
                                        <b style="color: green;"> Activé</b>
                                    </c:if>
                                    <c:if test="${detailsDossier.isActive eq 0}"> 
                                        <b style="color: red;"> Desactivé </b>
                                    </c:if>
                                     </p>
                                     <p>Description : </p>
                                     <p>${detailsDossier.description} <p>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="col-4" style="min-height: 400px">
                        <%--<h3>Les Membres</h3>
                        <c:if test="${fn:contains(statutMembre, 'Chef')}"> 
                        <select name="membres" class='btn btn-info' style='width: 200px; margin-bottom: 3px'>
                            <option value="">--Choisir--</option>    
                            <c:forEach items="${membres}" var="membre" >
                                <option value="">${membre.nom}</option>                                
                                <c:set var="idMembre" value="${membre.idMembre}"></c:set>
                            </c:forEach>
                        </select>
                        <a href="./addMembreDossier?idMembre=${idMembre}">
                            <p style=" margin-bottom: 3px" class="btn btn-primary active">Ajouter</p>
                        </a>
                        </c:if>
                        <table class="table table-striped table-bordered">
                            <c:if test="${empty listeMembreAjouter}">
                                <h3>Aucun membre</h3>
                            </c:if>
                            <c:if test="${not empty listeMembreAjouter}">
                                <c:forEach items="${listeMembreAjouter}" var="unMembre" >
                                <tr style="border-style: inset"><td>${unMembre.nom}</td></tr>
                                </c:forEach>
                            </c:if>
                        </table>--%>    
                    </div>
                </div>
            </c:if>                
        </div>

        <jsp:include page="pieddepage.jsp" />

    </body>
</html>