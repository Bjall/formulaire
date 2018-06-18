package formulaire;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/inscription")
public class Inscription extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String VUE = "/WEB-INF/formulaire.jsp";
	public static final String CHAMP_EMAIL = "email";
	public static final String CHAMP_PASS = "motdepasse";
	public static final String CHAMP_CONF = "confirmation";
	public static final String CHECKBOX = "checkbox";
	public static final String ATT_ERREURS = "erreurs";
	public static final String ATT_RESULTAT = "resultat";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/* Affichage de la page d'inscription */
		req.setAttribute("message", "Bienvenue sur ce site en MVC");
		RequestDispatcher rq = getServletContext().getRequestDispatcher("/WEB-INF/formulaire.jsp");
		rq.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String resultat;
		Map<String, String> erreurs = new HashMap<String, String>();

		/* Récupération des champs du formulaire. */
		String email = req.getParameter(CHAMP_EMAIL);
		String motDePasse = req.getParameter(CHAMP_PASS);
		String confirmation = req.getParameter(CHAMP_CONF);
		String checkBox = req.getParameter(CHECKBOX);

		/* Validation du champ email. */
		try {
			validationEmail(email);
		} catch (Exception e) {
			erreurs.put(CHAMP_EMAIL, e.getMessage());
		}

		/* Validation des champs mot de passe et confirmation. */
		try {
			validationMotsDePasse(motDePasse, confirmation);
		} catch (Exception e) {
			erreurs.put(CHAMP_PASS, e.getMessage());
		}

		/* Validation de la checkbox. */
		try {
			validationCheckBox(checkBox);
		} catch (Exception e) {
			erreurs.put(CHECKBOX, e.getMessage());
		}

		/* Initialisation du résultat global de la validation. */
		if (erreurs.isEmpty()) {
			Date date = new Date();
			resultat = "Votre inscription a bien été prise en compte le " + date + " pour l'adresse mail "
					+ email + ".";
		} else {
			resultat = "Échec de l'inscription.";
		}

		/* Stockage du résultat et des messages d'erreur dans l'objet req */
		req.setAttribute(ATT_ERREURS, erreurs);
		req.setAttribute(ATT_RESULTAT, resultat);

		/* Transmission de la paire d'objets req/response à notre JSP */
		this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
	}

	/* Valide l'adresse mail saisie */
	private void validationEmail(String email) throws Exception {
		if (email != null && email.trim().length() != 0) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Merci de saisir une adresse mail valide.");
			}
		} else {
			throw new Exception("Merci de saisir une adresse mail.");
		}
	}

	/* Valide les mots de passe saisis */
	private void validationMotsDePasse(String motDePasse, String confirmation) throws Exception {
		if (motDePasse != null && motDePasse.trim().length() != 0 && confirmation != null
				&& confirmation.trim().length() != 0) {
			if (!motDePasse.equals(confirmation)) {
				throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
			} else if (motDePasse.trim().length() < 8) {
				throw new Exception("Les mots de passe doivent contenir au moins 8 caractères.");
			}
		} else {
			throw new Exception("Merci de saisir et confirmer votre mot de passe.");
		}
	}

	/* Valide que la checkbox est cochée */
	private void validationCheckBox(String checkbox) throws Exception {
		if (checkbox == null) {
			throw new Exception("Merci d'accepter les conditions générales du site");
		}
	}

}