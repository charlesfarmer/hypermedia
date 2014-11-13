
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
        	
        	String itemToAdd = (String)request.getParameter("id");
        	String itemToDel = (String)request.getParameter("del");
        	MagasinCreateur mag = (MagasinCreateur) getServletContext().getAttribute("magasin");
        	
            List<LignePanierBean> panier = (List<LignePanierBean>) request.getSession().getAttribute("panier");
            ClientBean client = (ClientBean) request.getSession().getAttribute("client");

            //Uniformisation du panier
            
            if(client != null) {
                panier = mag.getLignePanierFacade().getPanier(mag.getConnexion(),
                    client);
            } else if(panier == null){
                    panier = new ArrayList<>();
            }
            
            
            // S'il y a un ajout à faire
            
            if (itemToAdd != null){
            	try{
            		LignePanierBean l = new LignePanierBean();
            		l.setQuantite(1);
            		ProduitBean p = new ProduitBean();
            		p.setIdProduit(itemToAdd);
            		l.setProduitBean(p);
            		
            		if (request.getAttribute("client")!=null){
            			l.setClientBean((ClientBean)request.getAttribute("client"));
            			mag.getLignePanierFacade().ajouterAuPanier(mag.getConnexion(), l);
            		}
            		
            		boolean addIt = true;
            		for(int i=0;i<panier.size();i++){
            			if(panier.get(i).getProduitBean().getIdProduit().equals(l.getProduitBean().getIdProduit())){
            				addIt = false;
            				panier.get(i).setQuantite(panier.get(i).getQuantite() + l.getQuantite());
            			}
            		}
            		if (addIt)
            			panier.add(l);
            		
        			
            		
            	}catch(Exception e){
            		e.printStackTrace();
            	}
            }
            
            // S'il y a une suppression à faire
            
            if (itemToDel != null){
            	for(int i=0;i<panier.size();i++){
            		if(panier.get(i).getProduitBean().getIdProduit().equals(itemToDel)){
            			panier.remove(i);
            			break;
        			}
            	}
            	if(client!=null){
            		LignePanierBean l = new LignePanierBean();
            		l.setIdLignePanier(itemToDel);
            		mag.getLignePanierFacade().retirerDuPanier(mag.getConnexion(), l);
            	}
            }
            
            
            
            
            
            request.getSession().setAttribute("panier", panier);
            getServletContext().getRequestDispatcher("/WEB-INF/panier.jsp").forward(request,
                response);
        } catch(FacadeException facadeException) {
            //temp
        }
    }

}
