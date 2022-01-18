<%-- 
    Document   : detailsLivre
    Created on : 2021-03-18, 15:19:26
    Author     : moumene
--%>

<%@page import="com.ghilas.entites.Reunion"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ListIterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="resources/style/bootstrap413/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
        <link href='resources/style/style.css' rel="stylesheet" type="text/css" />
        <script src="resources/js/ajaxGestion.js"></script>
    </head>
    <body>
        <jsp:include page="entete.jsp" />
        <div class="container">
            <f:form class="form" method="post" modelAttribute="formDetailReunion">
            <c:if test="${empty detailsReunion}">                
                <h3>Aucune Reunion</h3>
            </c:if>
            <c:if test="${not empty detailsReunion}">
            <div class="row">
                <div class="col-8">
                    <div class="col-sm-12 bg-light text-info" style="min-height: 200px">
                            <c:if test="${isActive eq 1}">
                                <c:if test="${fn:contains(statutMembre, 'Chef')}">
                                    <div style="text-align: right"> 
                                            <a href="./modifierReunion?idReunion=${detailsReunion.idReunion}">
                                                <img src="resources/images/iconeModif1.png" width="25px"  alt="imageModif" >
                                            </a>
                                    </div>
                                </c:if>
                            </c:if>
                       
                        <span>
                           Titre :
                           <br />${detailsReunion.titre}<br />
                           Date : 
                           <br />${detailsReunion.date}<br />
                           Description : 
                           <br />${detailsReunion.description}
                        </span>
                    </div>
                    <div class="col-sm-12" style="min-height: 200px">
                        <h3>Les points d'ordres</h3>
                        <table class="table table-striped table-bordered">
                            <c:if test="${empty pointDordresReunion}">
                                <td>Aucun point d'ordre</td>
                            </c:if>
                            <c:if test="${not empty pointDordresReunion}">
                                <tr>
                                    <th>Nom</th>
                                    <th>Description</th>
                                </tr>
                                <c:forEach items="${pointDordresReunion}" var="pointDordreliste" >
                                    
                                <tr>
                                    <td>${pointDordreliste.nom}</td>
                                    <td>
                                        ${pointDordreliste.comptesRendus}
                                        <c:if test="${isActive eq 1}">
                                            <div style="text-align: right" >
                                                <a href="./compteRendu?idPointDordre=${pointDordreliste.idPointDordre}">
                                                    <img src="resources/images/iconeModif1.png" width="25px"  alt="imageModif" >
                                                </a>
                                            </div>
                                        </c:if>
                                    </td>
                                    <c:if test="${isActive eq 1}">
                                    <c:if test="${fn:contains(statutMembre, 'Chef')}"> 
                                    <td style="width: 140px">
                                        <a title="Supprimer" href="./removePointDordre?idPointDordre=${pointDordreliste.idPointDordre}">
                                            <p style=" margin-bottom: 3px" class=" btn btn-primary active"  >Supprimer</p>
                                        </a>
                                    </td>
                                    </c:if>
                                    </c:if>
                                </tr>
                                </c:forEach>
                            </c:if>                           
                        </table>
                    </div>
                    
                </div>  
                <div class="col-4" style="min-height: 400px">
                    <h3>Les Membres</h3>
                    <c:if test="${isActive eq 1}">
                        <c:if test="${fn:contains(statutMembre, 'Chef')}"> 
                            <f:select path="selectMembre" class='btn btn-info' style='width: 200px; margin-bottom: 3px'>
                                <f:option value="${param.idMembre}">--Choisir--</f:option>    
                                <c:forEach items="${membres}" var="membre" >
                                    <f:option label="idMembre" value="${membre.idMembre}">${membre.nom}</f:option>
                                </c:forEach>
                            </f:select>
                            <input type="submit" class="btn btn-primary active" value="Ajouter" />
                        </c:if>
                    </c:if>
                    <c:if test="${isActive eq 1}">
                        <c:if test="${fn:contains(statutMembre, 'Membre')}"> 
                            <td style="width: 140px">
                                <a id="Confirmer" href="./confirmerParticipation?idReunion=${detailsReunion.idReunion}">
                                    <p style=" margin-bottom: 3px" class=" btn btn-primary active"  >Confirmer</p>
                                </a>
                            </td>
                            <td style="width: 140px">
                                <a id="Infirmer" href="./infirmerParticipation?idReunion=${detailsReunion.idReunion}">
                                    <p style=" margin-bottom: 3px" class=" btn btn-primary active"  >Infirmer</p>
                                </a>
                            </td>
                            <script>
                                document.getElementById()('Confirmer').onclick = function() {
                                    var url = 'ajaxAdd.html?nb1='+n1+'&nb2='+n2;
                                    document.getElementById('msg').innerHTML = '';
                                    document.getElementById('res').innerHTML = '';
                                    ajax(url,function(evt){
                                        var xhr = evt.target;
                                        if (xhr.readyState == 4 && xhr.status == 200)  //réponse OK
                                        {
                                            var reponseJSON = xhr.responseText;
                                            var rep = JSON.parse(reponseJSON);
                                            if (rep.R) {
                                                document.getElementById('res').innerHTML = 'Somme : '+rep.R;
                                            }
                                            else {
                                                document.getElementById('msg').innerHTML = rep.message;
                                            }
                                        }
                                    });
                                    document.getElementById()('Infirmer').onclick = function() {
                                    var n1 = document.getElementById('nb1').value;
                                    var n2 = document.getElementById('nb2').value;
                                    var url = 'ajaxAdd.html?nb1='+n1+'&nb2='+n2;
                                    document.getElementById('msg').innerHTML = '';
                                    document.getElementById('res').innerHTML = '';
                                    ajax(url,function(evt){
                                        var xhr = evt.target;
                                        if (xhr.readyState == 4 && xhr.status == 200)  //réponse OK
                                        {
                                            var reponseJSON = xhr.responseText;
                                            var rep = JSON.parse(reponseJSON);
                                            if (rep.R) {
                                                document.getElementById('res').innerHTML = 'Somme : '+rep.R;
                                            }
                                            else {
                                                document.getElementById('msg').innerHTML = rep.message;
                                            }
                                        }
                                    });
                                }
                            </script>
                        </c:if>
                    </c:if>
                    <table class="table table-striped table-bordered">
                        <c:if test="${empty reunionMembres}">
                            <h3>Aucun membre</h3>
                        </c:if>
                        <c:if test="${not empty reunionMembres}">
                            <c:forEach items="${reunionMembres}" var="unMembre">
                            <tr style="border-style: inset">
                                <td>${unMembre.nom}</td>
                                <c:if test="${isActive eq 1}">
                                    <c:if test="${fn:contains(statutMembre, 'Chef')}"> 
                                    <td style="width: 140px">
                                        <a title="Supprimer" href="./removeMembre?idMembre=${unMembre.idMembre}">
                                            <p style=" margin-bottom: 3px" class=" btn btn-primary active"  >Supprimer</p>
                                        </a>
                                    </td>
                                    </c:if>
                                </c:if>
                            </tr>
                            </c:forEach>
                        </c:if>
                    </table>    
                </div>
                <c:if test="${isActive eq 1}">
                    <c:if test="${fn:contains(statutMembre, 'Chef')}"> 
                        <div style="margin: 10px">
                            <a title="fermerReunion" href="./fermerReunion?idReunion=${detailsReunion.idReunion}">
                                <p style=" margin-bottom: 3px;" class=" btn btn-primary active"  >Fermer La Reunion</p>
                            </a>
                        </div>
                    </c:if>
                </c:if>
                <c:if test="${isActive eq 1}">
                    <c:if test="${fn:contains(statutMembre, 'Chef')}"> 
                        <div style="margin: 10px">
                            <a title="annulerReunion" href="./annulerReunion?idReunion=${detailsReunion.idReunion}">
                                <p style=" margin-bottom: 3px;" class=" btn btn-primary active"  >Annuler La Reunion</p>
                            </a>
                        </div>
                    </c:if>
                </c:if> 
            </div>
            </c:if>
        </f:form>
        </div>  
        <jsp:include page="pieddepage.jsp" />
    </body>
</html>
