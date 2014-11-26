
package ca.qc.collegeahuntsic.weblab6.servlet.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewTemplateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String FORWARD_RESOURCE = "/WEB-INF/jsp/viewTemplate/viewIndex.jsp";

    public ViewTemplateServlet() {
        super();
    }

    @Override
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        getServletContext().getRequestDispatcher(ViewTemplateServlet.FORWARD_RESOURCE).forward(request,
            response);
    }
}
