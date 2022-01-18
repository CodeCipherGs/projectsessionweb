<%-- 
    Document   : modifCompteRendus
    Created on : 21 mai 2021, 23 h 54 min 57 s
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
        <div class="soustitre2" >
            <h2>Modification du Compte Rendus</h2>
        </div>
        <p class='erreur'>${message}</p>
        <f:form class="form" method="post" modelAttribute="formPointDordre" id="modificationForm">
                <div class="row">
            

                    <div class="form-group col-sm-6">
                          <span class="erreur">${comptesRendus}</span>
                          <label>Comptes Rendus</label>
                          <f:textarea path="comptesRendus" class="form-control" value='${param.comptesRendus}' rows="3" ></f:textarea>
                    </div>
                    
                    <div class="form-group col-sm-12 ">
                        <div class="form-group col-sm-2 ">
                          <input type="submit" class="form-control btn btn-primary active" value=" Insérer " />
                        </div>
                    </div>
                </div>
            </f:form>
                    
        </div>
        <jsp:include page="pieddepage.jsp" />
    </body>
</html>
