
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
import ca.qc.collegeahuntsic.weblab5.exception.service.NotEnoughStockQuantityException;
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
        	String itemToAdd = (String)request.getParameter("id");
        	MagasinCreateur mag = (MagasinCreateur) getServletContext().getAttribute("magasin");
        	
            List<LignePanierBean> panier = (List<LignePanierBean>) request.getSession().getAttribute("panier");
            ClientBean client = (ClientBean) request.getSession().getAttribute("client");

            if(client != null) {
                panier = mag.getLignePanierFacade().getPanier(mag.getConnexion(),
                    client);
                
//                if(itemToAdd!=null){
//                	LignePanierBean l = new LignePanierBean();
//            		l.setQuantite(1);
//            		ProduitBean p = new ProduitBean();
//            		p.setIdProduit("itemToAdd");
//            		l.setProduitBean(p);
//            		l.setClientBean((ClientBean)request.getAttribute("client"));
//        			try {
//    					mag.getLignePanierFacade().ajouterAuPanier(mag.getConnexion(), l);
//    				} catch (NotEnoughStockQuantityException e) {
//    					e.printStackTrace();
//    				}
//        			
//                }
                
                
                request.getSession().setAttribute("panier",
                    panier);
            } else {
                if(panier == null) {
                	
                    panier = new ArrayList<>();
                    //LignePanierBean l = new LignePanierBean();
                    //ProduitBean p = new ProduitBean();
                    //p.setIdProduit("1");
                    //l.setProduitBean(p);
                   // panier.add(l);
                    request.getSession().setAttribute("panier",
                        panier);
                }
            }
            
            
            
            
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
            		
            		panier.add(l);
        			request.getSession().setAttribute("panier", panier);
            		
            	}catch(Exception e){
            		e.printStackTrace();
            	}
            }

            getServletContext().getRequestDispatcher("/WEB-INF/panier.jsp").forward(request,
                response);
        } catch(FacadeException facadeException) {
            //temp
        }
    }

}
