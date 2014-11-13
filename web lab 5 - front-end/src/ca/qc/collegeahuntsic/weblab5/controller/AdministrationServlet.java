
package ca.qc.collegeahuntsic.weblab5.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ca.qc.collegeahuntsic.weblab5.bean.ProduitBean;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;
import ca.qc.collegeahuntsic.weblab5.util.MagasinCreateur;

/**
 * Servlet implementation class AdministrationServlet
 */
@WebServlet(
    name = "/AdministrationServlet",
    urlPatterns = {"/administration"})
public class AdministrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        processRequest(request,
            response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        processRequest(request,
            response);
    }

    private static void processRequest(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        MagasinCreateur magasin = (MagasinCreateur) request.getServletContext().getAttribute("magasin");
        ProduitBean produit = new ProduitBean();
        produit.setIdProduit(request.getParameter("produitVedette"));
        try {
            produit = magasin.getProduitFacade().get(magasin.getConnexion(),
                produit);
            request.setAttribute("produitValide",
                "true");
            request.getServletContext().setAttribute("vedette",
                produit.getIdProduit());
        } catch(FacadeException e) {
            request.setAttribute("produitInvalide",
                "true");
        } finally {
            request.getRequestDispatcher("/WEB-INF/administration.jsp").forward(request,
                response);
        }
    }
}
