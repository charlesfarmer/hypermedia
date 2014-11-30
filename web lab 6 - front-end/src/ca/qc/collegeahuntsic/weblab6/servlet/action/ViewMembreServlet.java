
package ca.qc.collegeahuntsic.weblab6.servlet.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ca.qc.collegeahuntsic.weblab6.dto.MembreDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.ApplicationException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.service.ServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ViewMembreServlet extends ApplicationServlet {

    private static final long serialVersionUID = 1L;

    private static final String FORWARD_RESOURCE = "/WEB-INF/jsp/viewMembre/viewIndex.jsp";

    public static final String MEMBRE_ID_ATTRIBUTE_NAME = "idMembre";

    public static final String VIEW_MEMBRE_ATTRIBUTE_NAME = "membre";

    private static final Log LOGGER = LogFactory.getLog(ViewMembreServlet.class);

    public ViewMembreServlet() {
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
        String idMembre = request.getParameter(ViewMembreServlet.MEMBRE_ID_ATTRIBUTE_NAME);
        try {
            beginTransaction();
            MembreDTO membre = getMembreService().getMembre(getSession(),
                idMembre);
            commitTransaction();
            request.setAttribute(ViewMembreServlet.VIEW_MEMBRE_ATTRIBUTE_NAME,
                membre);
        } catch(
            InvalidHibernateSessionException
            | InvalidPrimaryKeyException
            | ServiceException
            | ApplicationException e) {
            try {
                rollbackTransaction();
            } catch(ApplicationException e1) {
                ViewMembreServlet.LOGGER.fatal("problème");
            }
            ViewMembreServlet.LOGGER.fatal("problème");
        }
        getServletContext().getRequestDispatcher(ViewMembreServlet.FORWARD_RESOURCE).forward(request,
            response);
    }
}
