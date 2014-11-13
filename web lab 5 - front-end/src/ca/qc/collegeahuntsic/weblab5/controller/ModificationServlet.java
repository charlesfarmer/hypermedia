
package ca.qc.collegeahuntsic.weblab5.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;

/**
 * Servlet implementation class ModificationServlet
 */
@WebServlet(
    name = "modificationProfil",
    urlPatterns = {"/profil/modification"})
public class ModificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificationServlet() {
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

    @SuppressWarnings("static-method")
    private void processRequest(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        ClientBean clientBean = (ClientBean) request.getSession(false).getAttribute("client");
        if(clientBean == null) {
            request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request,
                response);
        }
        request.getRequestDispatcher("/WEB-INF/modification.jsp").forward(request,
            response);
    }

}
