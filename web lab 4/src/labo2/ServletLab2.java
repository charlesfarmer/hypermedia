
package labo2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@WebServlet(
    name = "ServletLab2",
    urlPatterns = {"/servletlab2"})
public class ServletLab2 extends HttpServlet {
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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        String productList = "<html>\n"
            + "<head>\n"
            + "<title>Labo 2</title>\n"
            + "</head>\n"
            + "<body>\n";
        if(session == null) {
            productList += "<a href='login'><span class='mylogin'>Se connecter</span></a>";
        } else {
            productList += request.getParameter("user") == null ? "" : "<span>Bienvenue, "
                + request.getParameter("user")
                + "</span>&nbsp;&nbsp;";
            productList += "<a href='inscription?flag=-1'><span class='mylogin'><strong>(Déconnexion)</strong></span></a>";
        }
        productList += "<h1>Bienvenue sur le meilleur site de vente de produits! U wot m8?</h1>";

        productList += "<a href='panier'>Visionner votre panier d'achat</a>";
        productList += "<table border='1'>";

        File productListXML = new File(getServletContext().getRealPath("/produits.xml"));
        Document d = null;
        try {
            d = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(productListXML);
        } catch(
            ParserConfigurationException
            | SAXException e) {
            //
        }
        NodeList nl = d.getElementsByTagName("produit");

        for(int i = 0 ; i < nl.getLength() ; i++) {
            productList += "<tr>";
            Node n = nl.item(i);

            //On commence au deuxi�me node de produit, c'est � dire son nom.
            n = n.getFirstChild().getNextSibling().getNextSibling().getNextSibling();
            productList += "<td>"
                + n.getTextContent()
                + "</td>";

            n = n.getNextSibling().getNextSibling();
            productList += "<td>"
                + n.getTextContent()
                + "</td>";

            n = n.getNextSibling().getNextSibling();
            productList += "<td> <img height='150' src='PICS/"
                + n.getTextContent()
                + "'/> </td>";

            productList += "<td><a href='panier?id="
                + nl.item(i).getFirstChild().getNextSibling().getTextContent()
                + "' >Ajouter au panier</a></td>";

            productList += "</tr>";
        }
        productList += "</table>\n"
            + "</body>\n"
            + "</html>";
        out.write(productList);
        out.close();
    }
}
