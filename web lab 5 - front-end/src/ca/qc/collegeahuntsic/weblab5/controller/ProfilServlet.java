
package ca.qc.collegeahuntsic.weblab5.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;
import ca.qc.collegeahuntsic.weblab5.util.MagasinCreateur;

/**
 * Servlet implementation class ProfilServlet
 */
@WebServlet(
    name = "profil",
    urlPatterns = {"/profil"})
public class ProfilServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfilServlet() {
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

        //TEST PROFIL
        MagasinCreateur magasin = ((MagasinCreateur) getServletContext().getAttribute("magasin"));
        try {
            System.out.println(getServletContext().getAttribute("test"));
            ClientBean client = magasin.getClientFacade().getClientByEmail(magasin.getConnexion(),
                "BigD@love.com");
            System.out.println(client.getEmail());
            /*request.getSession(false).setAttribute("client",
                client);*/
        } catch(FacadeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/profil.jsp").forward(request,
            response);
    }
}
