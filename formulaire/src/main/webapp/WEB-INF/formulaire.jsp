<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Inscription</title>
<link type="text/css" rel="stylesheet" href="form.css" />
</head>
<body>
	<form method="post" action="inscription">
		<fieldset>
			<legend>Inscription</legend>
			<label for="email">Adresse email</label> <input type="text" id="email" name="email" value="<c:out value="${param.email}"/>" /> <span>${erreurs['email']}</span> <br />
			<label for="motdepasse">Mot de passe</label> <input type="password" name="motdepasse" /> <span>${erreurs['motdepasse']}</span> <br />
			<label for="confirmation">Confirmation du mot de passe</label> <input type="password" name="confirmation" /> <span>${erreurs['confirmation']}</span> <br />
			<input type="checkbox" name="checkbox"/>J’ai lu et approuvé les conditions générales de ce site <span>${erreurs['checkbox']}</span> <br />
			<input type="submit" value="Inscription" /> <br />
			<p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>
		</fieldset>
	</form>
</body>
</html>