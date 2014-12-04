
package ca.qc.collegeahuntsic.weblab6.servlet.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ViewTemplateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String FORWARD_RESOURCE = "/WEB-INF/jsp/viewTemplate/viewIndex.jsp";

    private static final Log LOGGER = LogFactory.getLog(ViewTemplateServlet.class);

    public ViewTemplateServlet() {
        super();
    }

    @Override
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        processRequest(request,
            response);
    }

    @Override
    public void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        processRequest(request,
            response);
    }

    private void processRequest(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        ViewTemplateServlet.LOGGER.warn(String.format("%1$-20s PROCESS REQUEST",
            ViewVitrinesServlet.class.toString()));
        getServletContext().getRequestDispatcher(ViewTemplateServlet.FORWARD_RESOURCE).forward(request,
            response);
    }
}
