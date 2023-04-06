<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Liste des auteurs existants</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/menu.jsp" />
        <div id="corps">
        <c:choose>
            <%-- Si aucun auteur n'existe en session, affichage d'un message par défaut. --%>
            <c:when test="${ empty auteurs }">
                <p class="erreur">Aucun auteur enregistré.</p>
            </c:when>
            <%-- Sinon, affichage du tableau. --%>
            <c:otherwise>
            <table>
                <tr>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Téléphone</th>
                    <th>Email</th>
                    <th class="action">Action</th>                    
                </tr>
                <%-- Parcours de la liste des auteurs en session, et utilisation de l'objet varStatus. --%>
                <c:forEach items="${ auteurs }" var="auteur" varStatus="boucle">
                <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                    <td><c:out value="${ auteur.nom }"/></td>
                    <td><c:out value="${ auteur.prenom }"/></td>
                    <td><c:out value="${ auteur.telephone }"/></td>
                    <td><c:out value="${ auteur.email }"/></td>
                    <%-- Lien vers la servlet de suppression, avec passage de l'id de l'auteur. --%>
                    <td class="action">
                        <a href="<c:url value="/suppressionAuteur"><c:param name="idAuteur" value="${ auteur.id }" /></c:url>">
                            <img src="<c:url value="/inc/delete.gif"/>" alt="Supprimer" />
                        </a>
                    </td>
                </tr>
                </c:forEach>
            </table>
            </c:otherwise>
        </c:choose>
        </div>
    </body>
</html>