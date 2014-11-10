
package labo2;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "Login",
    urlPatterns = {"/login"})
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        response.setContentType(CONTENT_TYPE);

        if(request.getSession(false) != null) {
            response.sendRedirect("servletlab2");
        }

        String failConnect = request.getParameter("failConnect");
        String failRegister = request.getParameter("failRegister");
        String failNonMatchingPasswords = request.getParameter("failPasswords");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><style>"
            + "label{\n"
            + "    display: inline-block;\n"
            + "    float: left;\n"
            + "    clear: left;\n"
            + "    width: 250px;\n"
            + "    text-align: right;\n"
            + "}\n"
            + "input {\n"
            + "  display: inline-block;\n"
            + "  float: left;\n"
            + "}\n"
            + ".button{\n"
            + "    display: inline-block;\n"
            + "    float: left;\n"
            + "    clear: left;\n"
            + "    margin-left: 250px;\n"
            + "    text-align: right;"
            + "}\n"
            + ".fail {color: red;}\n "
            + "</style><title>login</title></head>");//<script type=\"text/javascript\">function _(){var __=document.getElementById('nom'),$=document.getElementById('motdepasse'),a=document.getElementById('motdepasse2');if($.value.toUpperCase()===a.value.toUpperCase()){document.location.href='inscription?nom='+__.value+'&motdepasse='+$.value+'&flag=1';}else{alert('Vous devez entrer deux mots de passe identiques\\n\\n Veuillez réessayer');}}</script>
        out.println("<body>");
        out.println("<h1>écran de connexion</h1>");
        out.println("<form action='inscription' method='post'>"
            + "<div class='connecterUsager'>"
            + "<h3>Usager inscrit</h3>"
            + "<label for='username'>Nom d'usager</label><input name='username' id='username' type='text'>");
        if(failConnect != null) {
            out.println("<span class='fail'>&nbsp;&nbsp; Le pseudo et/ou le mot de passe sont incorrects</span>");
        }

        out.println("<br>"
            + "<label for='password'>Mot de passe</label><input name='password' id='password' type='password'>"
            + "<br><br>"
            + "<input class='button' type='submit' value='Connexion'>"
            + "<input name='flag' type='hidden' value='0'>"
            + "</div>"
            + "</form><br>"
            + "<form action='inscription' method='post'>"
            + "<div class='enregistrerUsager'>"
            + "<h3>Inscription d'un nouvel usager</h3>"
            + "<label for='nom'>Nouveau nom d'usager</label><input name='nom' id='nom' type='text'>");
        if(failRegister != null) {
            out.println("<span class='fail'>&nbsp;&nbsp; Le nom d'usager existe déjà, veuillez entrez un autre nom</span>");
        }
        out.println("<br>"
            + "<label for='motdepasse'>Mot de passe</label><input name='motdepasse' id='motdepasse' type='password'><br>"
            + "<label for='motdepasse2'>Répétez Mot de passe</label><input name='motdepasse2' id='motdepasse2' type='password'>");
        if(failNonMatchingPasswords != null) {
            out.println("<span class='fail'>&nbsp;&nbsp; Vos deux mots de passes ne sont pas identiques, veuillez réessayer</span>");
        }
        out.println("<br><br>"
            + "<input class='button' type='submit' value='Inscris-toi !'>"
            + "<input name='flag' type='hidden' value='1'>"
            + "</div>"
            + "</form>");
        out.println("</body></html>");
        out.close();
    }//<script type=\"text/javascript\">var b=document.getElementById('formEnregistrement');if(b.addEventListener){b.addEventListener(\"submit\", _(), false);}else if(b.attachEvent){b.attachEvent('onsubmit', _());}</script>
}
