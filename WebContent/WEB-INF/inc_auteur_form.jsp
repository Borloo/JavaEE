<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<label for="nomAuteur">Nom <span class="requis">*</span></label>
<input type="text" id="nomAuteur" name="nomAuteur" value="<c:out value="${auteur.nom}"/>" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['nomAuteur']}</span>
<br />

<label for="prenomAuteur">Prénom </label>
<input type="text" id="prenomAuteur" name="prenomAuteur" value="<c:out value="${auteur.prenom}"/>" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['prenomAuteur']}</span>
<br />

<label for="telephoneAuteur">Numéro de téléphone <span class="requis">*</span></label>
<input type="text" id="telephoneAuteur" name="telephoneAuteur" value="<c:out value="${auteur.telephone}"/>" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['telephoneAuteur']}</span>
<br />

<label for="emailAuteur">Adresse email</label>
<input type="email" id="emailAuteur" name="emailAuteur" value="<c:out value="${auteur.email}"/>" size="30" maxlength="60" />
<span class="erreur">${form.erreurs['emailAuteur']}</span>
<br />

