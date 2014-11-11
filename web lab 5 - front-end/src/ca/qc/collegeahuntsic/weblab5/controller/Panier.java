
package ca.qc.collegeahuntsic.weblab5.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.bean.LignePanierBean;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;
import ca.qc.collegeahuntsic.weblab5.util.MagasinCreateur;

/**
 * Servlet implementation class Panier
 */
@WebServlet("/Panier")
public class Panier extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Panier() {
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

    private void processRequest(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        try {
            MagasinCreateur mag = (MagasinCreateur) request.getAttribute("Magasin");
            List<LignePanierBean> panier = (List<LignePanierBean>) request.getAttribute("panier");
            ClientBean client = (ClientBean) request.getAttribute("client");

            if(client != null) {
                panier = mag.getLignePanierFacade().getPanier(mag.getConnexion(),
                    client);
                request.setAttribute("panier",
                    panier);
            } else {
                if(panier == null) {

                    panier = new ArrayList<>();
                    request.setAttribute("panier",
                        panier);
                }
            }
            request.getSession().setAttribute("panier",
                request.getAttribute("panier"));

            getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,
                response);
        } catch(FacadeException facadeException) {
            //temp
        }
    }

}
