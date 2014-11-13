
package ca.qc.collegeahuntsic.weblab5.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.bean.LignePanierBean;
import ca.qc.collegeahuntsic.weblab5.bean.ProfilBean;
import ca.qc.collegeahuntsic.weblab5.exception.MagasinException;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;
import ca.qc.collegeahuntsic.weblab5.exception.service.EmailAlreadyUsedException;
import ca.qc.collegeahuntsic.weblab5.exception.service.NotEnoughStockQuantityException;
import ca.qc.collegeahuntsic.weblab5.util.MagasinCreateur;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet(
    name = "connexion",
    urlPatterns = {"/connexion"})
public class ConnexionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnexionServlet() {
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

        String connexion = request.getParameter("connexion");
        String deconnexion = request.getParameter("deconnexion");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nouveauCompte = request.getParameter("nouveauCompte");
        String nouveauEmail = request.getParameter("nouveauEmail");
        String password1 = request.getParameter("password1");

        if(deconnexion != null) {
            request.getSession().setAttribute("client",
                null);
        }
        MagasinCreateur magasin = (MagasinCreateur) getServletContext().getAttribute("magasin");

        if(nouveauCompte != null) {
            ClientBean client = new ClientBean();
            client.setEmail(nouveauEmail);
            client.setPassword(password1);
            ProfilBean profil = new ProfilBean();
            profil.setNom("null");
            profil.setPrenom("null");
            client.setProfilBean(profil);
            try {
                client = magasin.getClientFacade().ajouterClient(magasin.getConnexion(),
                    client);
                request.getSession().setAttribute("client",
                    client);
                magasin.commit();
            } catch(
                FacadeException
                | EmailAlreadyUsedException e) {
                request.setAttribute("clientExistant",
                    "true");
                try {
                    magasin.rollback();
                } catch(MagasinException e1) {
                    e1.printStackTrace();
                }
            } catch(MagasinException e) {
                e.printStackTrace();
            }
        }
        if(connexion != null) {
            try {
                ClientBean client = magasin.getClientFacade().getClientByEmail(magasin.getConnexion(),
                    email);
                if(client == null
                    || !password.equals(client.getPassword())) {
                    throw new FacadeException();
                }
                request.getSession().setAttribute("client",
                    client);
                
                List<LignePanierBean> vieuxPanier = (List<LignePanierBean>) request.getSession().getAttribute("panier");
                System.out.println("L'ancien panier est long de "+vieuxPanier.size());
                for(LignePanierBean item : vieuxPanier){
                	item.setClientBean(client);
                	magasin.getLignePanierFacade().ajouterAuPanier(magasin.getConnexion(), item);
                }
                magasin.commit();
                
                List<LignePanierBean> panier = magasin.getLignePanierFacade().getPanier(magasin.getConnexion(),
                    client);
                request.getSession().setAttribute("panier",
                    panier);
            } catch(FacadeException e) {
                request.setAttribute("clientInconnu",
                    "true");
            }catch(NotEnoughStockQuantityException | MagasinException e){
            	e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request,
            response);
    }
}
