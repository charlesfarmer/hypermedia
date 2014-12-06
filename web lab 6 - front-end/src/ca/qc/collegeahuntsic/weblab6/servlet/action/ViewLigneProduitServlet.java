
package ca.qc.collegeahuntsic.weblab6.servlet.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ca.qc.collegeahuntsic.weblab6.dto.MembreDTO;
import ca.qc.collegeahuntsic.weblab6.dto.VitrineDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.ApplicationException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.service.ServiceException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ViewLigneProduitServlet extends ApplicationServlet {

    private static final long serialVersionUID = 1L;

    private static final String FORWARD_RESOURCE = "/WEB-INF/jsp/viewLigneProduit/viewIndex.jsp";

    private static final Log LOGGER = LogFactory.getLog(ViewLigneProduitServlet.class);

    public static final String VITRINE_ID_ATTRIBUTE_NAME = "idVitrine";

    public static final String VITRINE_ATTRIBUTE_NAME = "vitrine";

    @Override
    public void doGet(HttpServletRequest req,
        HttpServletResponse resp) throws ServletException,
        IOException {
        processRequest(req,
            resp);
    }

    @Override
    public void doPost(HttpServletRequest req,
        HttpServletResponse resp) throws ServletException,
        IOException {
        processRequest(req,
            resp);
    }

    private void processRequest(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        String idMembre = request.getParameter(ViewMembreServlet.MEMBRE_ID_ATTRIBUTE_NAME);
        String idVitrine = request.getParameter(ViewLigneProduitServlet.VITRINE_ID_ATTRIBUTE_NAME);
        try {
            beginTransaction();
            MembreDTO membreDTO = getMembreService().getMembre(getSession(),
                idMembre);
            VitrineDTO vitrineDTO = getVitrineService().getVitrine(getSession(),
                idVitrine);
            vitrineDTO.getLigneVitrines().size();
            request.setAttribute(ViewLigneProduitServlet.VITRINE_ATTRIBUTE_NAME,
                vitrineDTO);
            request.setAttribute(ViewMembreServlet.MEMBRE_ID_ATTRIBUTE_NAME,
                membreDTO);
            commitTransaction();
        } catch(
            InvalidHibernateSessionException
            | InvalidPrimaryKeyException
            | ServiceException
            | ApplicationException e) {
            try {
                rollbackTransaction();
            } catch(ApplicationException e1) {
                ViewLigneProduitServlet.LOGGER.error(ExceptionUtils.getStackTrace(e1));
            }
            ViewLigneProduitServlet.LOGGER.error(ExceptionUtils.getStackTrace(e));
        }
        request.getRequestDispatcher(ViewLigneProduitServlet.FORWARD_RESOURCE).forward(request,
            response);
    }
}
