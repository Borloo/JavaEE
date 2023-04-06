<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Gestion de bibliothèque</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/menu.jsp" />
        <c:if test="${ sessionScope.user != null }">
            <p><a href="<c:url value="/creationAuteur"/>">Créer un nouvel auteur</a></p>
            <p><a href="<c:url value="/creationLivre"/>">Créer un nouveau livre</a></p>
        </c:if>
        
        <c:if test="${ sessionScope.user == null }">
            <div id="connexion">
                <form action="login" method="post">
                    <label for="username">Nom d'utilisateur:</label>
                    <input type="text" id="username" name="username" required>
                    <br>
                    <label for="password">Mot de passe:</label>
                    <input type="password" id="password" name="password" required>
                    <br>
                    <input type="submit" value="Se connecter">
                </form>
            </div>
        </c:if>
    </body>
</html>
