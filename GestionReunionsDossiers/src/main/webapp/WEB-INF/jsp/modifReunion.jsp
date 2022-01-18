<%-- 
    Document   : modifReunion
    Created on : 22 mai 2021, 16 h 45 min 17 s
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
            <h1>Modification de Reunion</h1>
        </div>   
        <p class='erreur'>${message}</p>
        <f:form class="form" method="post" modelAttribute="formReunion"  id="modificationForm">
                <div class="row">
            
                    <div class="form-group col-sm-12">
                        <span class="erreur">${titre}</span>
                        <label>Titre</label>
                        <f:input path="titre" type="text" class="form-control" placeholder="${detailsReunion.titre}" value='${param.titre}'  />
                    </div>
                    <div class="form-group col-sm-6">                        
                        <span class="erreur">${date}</span>
                        <label>Date</label>
                        <f:input path="date" type="date" class="form-control"  value='${detailsReunion.date}' />                          
                    </div>
                    <div class="form-group col-sm-6">
                        <span class="erreur">${responsable}</span>
                        <label>Responsable</label>
                        <f:input path="responsable" type="text" class="form-control" placeholder="${detailsReunion.responsable}" value='${pram.responsable}' />
                          
                    </div>
                    <div class="form-group col-sm-12">
                        <label>Description</label>
                        <f:textarea path="description" class="form-control" rows="3"   placeholder="${detailsReunion.description}"></f:textarea>
                        
                    </div>
                    
                        
                    <div class="form-group col-sm-3 col-sm-offset-8">
                          <input type="submit" class="form-control btn btn-primary active" value=" Modifier " />
                    </div>
                </div>
            </f:form>
        </div>
        <jsp:include page="pieddepage.jsp" />
    </body>
</html>
