
package ca.qc.collegeahuntsic.weblab6.servlet.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeconnexionServlet extends ApplicationServlet {

    private static final long serialVersionUID = 1L;

    private static final String FORWARD_RESOURCE = "/index.jsp";

    @Override
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        request.getSession().invalidate();
        request.getRequestDispatcher(DeconnexionServlet.FORWARD_RESOURCE).forward(request,
            response);
    }

    @Override
    public void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        request.getSession().invalidate();
        request.getRequestDispatcher(DeconnexionServlet.FORWARD_RESOURCE).forward(request,
            response);
    }
}
