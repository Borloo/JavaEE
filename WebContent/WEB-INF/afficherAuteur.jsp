<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Affichage d'un auteur</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/menu.jsp" />
        <div id="corps">
            <p class="info">${ form.resultat }</p>
            <p>Nom : <c:out value="${ auteur.nom }"/></p>
            <p>Prénom : <c:out value="${ auteur.prenom }"/></p>
            <p>Numéro de téléphone : <c:out value="${ auteur.telephone }"/></p>
            <p>Email : <c:out value="${ auteur.email }"/></p>
        </div>
    </body>
</html>