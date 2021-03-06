
package ca.qc.collegeahuntsic.weblab5.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.exception.MagasinException;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;
import ca.qc.collegeahuntsic.weblab5.exception.service.NotEnoughStockQuantityException;
import ca.qc.collegeahuntsic.weblab5.util.MagasinCreateur;

/**
 * Servlet implementation class ConfirmationServlet
 */
@WebServlet(
    name = "confirmation",
    urlPatterns = {"/confirmation"})
public class ConfirmationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmationServlet() {
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

MagasinCreateur mag = (MagasinCreateur) getServletContext().getAttribute("magasin");
    	
    	try {
			mag.getAchatFacade().acheter(mag.getConnexion(), (ClientBean) request.getSession().getAttribute("client"));
			mag.commit();
		} catch (FacadeException | NotEnoughStockQuantityException e) {
			e.printStackTrace();
		} catch (MagasinException e) {
			e.printStackTrace();
		}

        request.getRequestDispatcher("/WEB-INF/confirmation.jsp").forward(request,
            response);
    }

}
