
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
import ca.qc.collegeahuntsic.weblab5.bean.ProduitBean;
import ca.qc.collegeahuntsic.weblab5.exception.MagasinException;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;
import ca.qc.collegeahuntsic.weblab5.util.MagasinCreateur;

/**
 * Servlet implementation class PanierServlet
 */
@WebServlet(
    name = "panier",
    urlPatterns = {"/panier"})
public class PanierServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PanierServlet() {
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
            //set des variables

            String itemToAdd = request.getParameter("id");
            String itemToDel = request.getParameter("del");
            String modSub = request.getParameter("modSub");
            String modQ = request.getParameter("modQ");
            String modId = request.getParameter("modId");

            MagasinCreateur mag = (MagasinCreateur) getServletContext().getAttribute("magasin");

            List<LignePanierBean> panier = (List<LignePanierBean>) request.getSession().getAttribute("panier");
            if(panier == null || panier.equals(java.util.Collections.emptyList())) {
                panier = new ArrayList<>();
            }

            ClientBean client = (ClientBean) request.getSession().getAttribute("client");

            //Uniformisation du panier

            if(client != null) {
                panier = mag.getLignePanierFacade().getPanier(mag.getConnexion(),
                    client);
            }

            // S'il y a un ajout � faire

            if(itemToAdd != null) {
                try {
                    LignePanierBean l = new LignePanierBean();
                    l.setQuantite(1);
                    ProduitBean p = new ProduitBean();
                    p.setIdProduit(itemToAdd);
                    l.setProduitBean(p);

                    if(client != null) {
                        l.setClientBean((ClientBean) request.getSession().getAttribute("client"));
                        mag.getLignePanierFacade().ajouterAuPanier(mag.getConnexion(),
                            l);
                        mag.commit();
                        panier = mag.getLignePanierFacade().getPanier(mag.getConnexion(), client);
                    }else{
                    	boolean addIt = true;
                        for(int i = 0 ; i < panier.size() ; i++) {
                            if(panier.get(i).getProduitBean().getIdProduit().equals(l.getProduitBean().getIdProduit())) {
                                addIt = false;
                                panier.get(i).setQuantite(panier.get(i).getQuantite()
                                    + l.getQuantite());
                            }
                        }
                        if(addIt) {
                            panier.add(l);
                        }
                    }
                    
/*
                    boolean addIt = true;
                    for(int i = 0 ; i < panier.size() ; i++) {
                        if(panier.get(i).getProduitBean().getIdProduit().equals(l.getProduitBean().getIdProduit())) {
                            addIt = false;
                            panier.get(i).setQuantite(panier.get(i).getQuantite()
                                + l.getQuantite());
                        }
                    }
                    if(addIt) {
                        panier.add(l);
                    }
*/
                } catch(Exception e) {
                    e.printStackTrace();
                    try {
                        mag.rollback();
                    } catch(MagasinException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            // S'il y a une suppression � faire

            if(itemToDel != null) {
                try {
                    for(int i = 0 ; i < panier.size() ; i++) {
                        if(panier.get(i).getProduitBean().getIdProduit().equals(itemToDel)) {
                        	
                        	if(client != null) {
                                LignePanierBean l = panier.get(i);
                                mag.getLignePanierFacade().retirerDuPanier(mag.getConnexion(),
                                    l);
                                mag.commit();
                            }
                        	
                            panier.remove(i);
                            break;
                        }
                    }
                    
                } catch(Exception e) {
                    e.printStackTrace();
                    try {
                        mag.rollback();
                    } catch(MagasinException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            // S'il y a un update de quantit�

            if(modSub != null
                && modSub.equals("Modifier")
                && modQ != null
                && modId != null) {
                try {
                    Integer.parseInt(modQ);

                    for(int i = 0 ; i < panier.size() ; i++) {
                        if(panier.get(i).getProduitBean().getIdProduit().equals(modId)) {

                            panier.get(i).setQuantite(Integer.parseInt(modQ));

                            if(client != null) {
                                mag.getLignePanierFacade().modifierNombreDitems(mag.getConnexion(),
                                    panier.get(i));
                                mag.commit();
                            }

                            break;
                        }
                    }

                } catch(Exception e) {
                    e.printStackTrace();
                    try {
                        mag.rollback();
                    } catch(MagasinException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            request.getSession().setAttribute("panier",
                panier);
            getServletContext().getRequestDispatcher("/WEB-INF/panier.jsp").forward(request,
                response);
        } catch(FacadeException facadeException) {
            //temp
        }
    }

}
