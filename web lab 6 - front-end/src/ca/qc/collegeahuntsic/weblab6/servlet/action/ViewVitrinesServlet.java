
package ca.qc.collegeahuntsic.weblab6.servlet.action;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ca.qc.collegeahuntsic.weblab6.dto.MembreDTO;
import ca.qc.collegeahuntsic.weblab6.dto.VitrineDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.ApplicationException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.service.ServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ViewVitrinesServlet extends ApplicationServlet {

    private static final long serialVersionUID = 1L;

    private static final Log LOGGER = LogFactory.getLog(ViewVitrinesServlet.class);

    public static final String VITRINES_ATTRIBUTE_NAME = "vitrines";

    private static final String FORWARD_RESOURCE = "/WEB-INF/jsp/viewVitrines/viewIndex.jsp";

    public ViewVitrinesServlet() {
        super();
    }

    @Override
    public void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        processRequest(request,
            response);
    }

    @Override
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        processRequest(request,
            response);
    }

    private void processRequest(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        String idMembre = request.getParameter(ViewMembreServlet.MEMBRE_ID_ATTRIBUTE_NAME);
        if(idMembre == null
            || idMembre.isEmpty()
            || Integer.parseInt(idMembre) < 1) {
            MembreDTO membre = (MembreDTO) request.getSession().getAttribute(LoginMembreServlet.MEMBRE_ATTRIBUTE_NAME);
            if(membre != null) {
                idMembre = membre.getIdMembre();
            }
        }
        MembreDTO membre = null;
        Set<VitrineDTO> vitrines = Collections.emptySet();
        try {
            beginTransaction();
            membre = getMembreService().getMembre(getSession(),
                idMembre);
            vitrines = membre.getVitrines();
            for(VitrineDTO v : vitrines) {
                v.getLigneVitrines().size();
            }
            commitTransaction();
        } catch(
            ApplicationException
            | InvalidHibernateSessionException
            | InvalidPrimaryKeyException
            | ServiceException e) {
            try {
                rollbackTransaction();
            } catch(ApplicationException e1) {
                ViewVitrinesServlet.LOGGER.fatal("problème de rollback");
            }
            ViewVitrinesServlet.LOGGER.fatal("problème transactionnel");
        }
        request.setAttribute(LoginMembreServlet.MEMBRE_ATTRIBUTE_NAME,
            membre);
        request.setAttribute(ViewVitrinesServlet.VITRINES_ATTRIBUTE_NAME,
            vitrines);
        request.getRequestDispatcher(ViewVitrinesServlet.FORWARD_RESOURCE).forward(request,
            response);
    }
}
