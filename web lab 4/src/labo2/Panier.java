
package labo2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@WebServlet(
    name = "Panier",
    urlPatterns = {"/panier"})
public class Panier extends HttpServlet {
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

        boolean hasParam = false;

        String idAjout = null;
        String idDel = null;
        try {
            idAjout = request.getParameter("id");
            idDel = request.getParameter("del");
        } catch(Exception e) {
            e.printStackTrace();
        }
        if(idAjout != null
            || idDel != null) {
            hasParam = true;
        }

        Cookie[] cookies = request.getCookies();
        Cookie cookiePanier = null;
        boolean hasPanier = false;
        if(cookies != null) {
            for(int i = 0 ; i < cookies.length ; i++) {

                if(cookies[i].getName().equals("panier")) {
                    hasPanier = true;
                    cookiePanier = cookies[i];

                    if(idAjout != null) {
                        cookiePanier.setValue(cookies[i].getValue()
                            + idAjout
                            + "-");
                    }

                    cookiePanier.setMaxAge(60 * 60 * 24 * 365);

                }

            }
        }
        if(!hasPanier) {
            cookiePanier = new Cookie("panier",
                idAjout == null ? "" : idAjout
                    + "-");
            cookiePanier.setMaxAge(60 * 60 * 24 * 365);
        }
        if(idDel != null) {
            String result = "";
            String[] tempPanier = cookiePanier.getValue().split("-");
            for(int i = 0 ; i < tempPanier.length ; i++) {
                if(!Integer.toString(i).equals(idDel)) {
                    result += tempPanier[i]
                        + "-";
                }
            }
            cookiePanier.setValue(result);
        }

        String[] panierIds = cookiePanier.getValue().split("-");

        response.addCookie(cookiePanier);

        if(hasParam) {
            response.sendRedirect("panier");
        }

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Panier</title></head>");
        out.println("<body>");

        File panierListXML = new File(getServletContext().getRealPath("/produits.xml"));
        Document d = null;
        try {
            d = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(panierListXML);
        } catch(
            ParserConfigurationException
            | SAXException e) {
            //
        }
        NodeList nl = d.getElementsByTagName("produit");

        String panierList = "";
        panierList += "<table border='1'>";

        for(int j = 0 ; j < panierIds.length ; j++) {
            for(int i = 0 ; i < nl.getLength() ; i++) {
                Node n = nl.item(i);
                n = n.getFirstChild().getNextSibling();

                if(n.getTextContent().equals(panierIds[j])) {
                    panierList += "<tr>";

                    //Pour le titre du produit
                    n = n.getNextSibling().getNextSibling();
                    panierList += "<td>"
                        + n.getTextContent()
                        + "</td>";

                    //Pour l'image du produit
                    n = n.getNextSibling().getNextSibling().getNextSibling().getNextSibling();
                    panierList += "<td> <img height='150' src='PICS/"
                        + n.getTextContent()
                        + "'/> </td>";

                    panierList += "<td><a href='?del="
                        + j
                        + "'>Supprimer du panier</a></td>";
                    panierList += "</tr>";
                }

            }
        }

        panierList += "</table>\n";
        panierList += "<a href='servletlab2'>Retour � la liste des produits</a>";

        out.println(panierList);
        out.println("</body></html>");
        out.close();

    }
}
