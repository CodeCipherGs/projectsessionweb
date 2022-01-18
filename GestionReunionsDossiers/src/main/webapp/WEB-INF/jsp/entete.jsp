<%-- 
    Document   : entete
    Created on : 2021-02-14, 16:06:41
    Author     : ghilas
--%>
<%@page import="com.ghilas.entites.Membre"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-sm navBG navbar-light ">
  <a class="navbar-brand" href="./">Accueil</a>
  
  <!-- Links -->
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="./listeReunions">Réunions</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="./listeDossiers">Dossiers</a>
    </li>
    <li class="nav-item">
        <c:if test="${not empty sessionScope.membre}">
              <a class="nav-link" href="logout">Déconnexion (${sessionScope.membre.nom})</a>
        </c:if>
        <c:if test="${empty sessionScope.membre}">
              <a class="nav-link" href="login">Connexion</a>
        </c:if>
    </li>
  </ul>
</nav>
<div class="bg-neutral-alternate">
    <h1 class="bg-neutral-alternate titre">Gestion de Reunion et Dossier</h1> 
</div>

