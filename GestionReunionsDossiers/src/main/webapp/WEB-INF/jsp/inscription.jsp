<%-- 
    Document   : login
    Created on : 3 avr. 2021, 22 h 25 min 27 s
    Author     : booska
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
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
        <div class="container ">
            <div class="row">
                <div class="col-sm-6 offset-3 ">
               
                <p class='erreur'>${message}</p>
                    <f:form class="form" method="post" modelAttribute="form" id='registerForm'>
                           <h1 class="form_head">Inscription</h1>
                    <div class="row">
                        <div class="form-group col-sm-12">
                            <span class="erreur">${courriel}</span>
                            <f:input type="email" required="required" class="form-control" 
                                       path="courriel" value='${param.courriel}' placeholder="abc@gfe.xyz" />
                            <label>Courriel</label>
                        </div>
                        <div class="form-group col-sm-12">                            
                            <span class="erreur">${telephone}</span>
                            <f:input type="tel" required="required" class="form-control" 
                                       path="telephone" value='${param.telephone}' placeholder="abc@gfe.xyz" />
                            <label>Telephone</label>
                        </div>
                        <div class="form-group col-sm-12">
                            <f:input class="form-control" path="nom" value="${param.nom}" placeholder="nom"/>
                            <label>Nom</label>
                        </div>
                            <div class="form-group col-sm-12">
                            <f:input class="form-control" path="statut" value="${param.statut}" placeholder="Membre"/>
                            <label>Statut</label>
                        </div>
                        <div class="form-group col-sm-12">                            
                            <span class="erreur">${password}</span>
                            <f:password class="form-control" path="password" />
                            <label>Mot de passe</label>
                        </div>
                        <div class="form-group col-sm-12">
                            <span class="erreur">${password2}</span>
                            <f:password class="form-control" path="password2" />
                            <label>Copie du mot de passe</label>
                        </div>
                        <div class="form-group col-sm-3">
                            <input type="submit" class="form-control btn btn-primary active" value=" OK " />
                        </div>
                    </div>
                </f:form>
                Vous avez déjà un compte ? <a href='login'>Connectez-vous</a>
                </div>
            </div>
        </div>
        <jsp:include page="pieddepage.jsp" />
    </body>
</html>
