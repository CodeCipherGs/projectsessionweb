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
        <div class="container">
            <div class="row">
                <div class="col-sm-6 offset-3 ">
                   <p class='erreur'>${requestScope.message}</p>
                    <f:form class="form" method="post" modelAttribute="modele" id='loginForm'>
                        <div class="row">
                            <div class="form-group col-sm-12">
                                <span class="erreur">${requestScope.courriel}</span>
                                <f:input type="email" required="required" class="form-control" 
                                       path="courriel" value='${param.courriel}' placeholder="abc@gfe.xyz" />
                                <label>Courriel</label>
                            </div>
                            <div class="form-group col-sm-12">
                                <span class="erreur">${requestScope.motDePasse}</span>
                                <f:password class="form-control" path="password" />
                                <label>Mot de passe</label>
                            </div>
                            <div class="form-group col-sm-3">
                                <input type="submit" class="form-control btn btn-primary active" value=" OK " />
                            </div>
                        </div>
                    </f:form>
                    Vous n'avez pas de compte ? <a href='inscription'>Inscrivez-vous</a>
                </div>
            </div>
        </div>
        <jsp:include page="pieddepage.jsp" />
    </body>
</html>
