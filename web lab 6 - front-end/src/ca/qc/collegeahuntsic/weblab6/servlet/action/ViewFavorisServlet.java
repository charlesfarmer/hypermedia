
package ca.qc.collegeahuntsic.weblab6.servlet.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ca.qc.collegeahuntsic.weblab6.dto.FavoriDTO;
import ca.qc.collegeahuntsic.weblab6.dto.MembreDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.ApplicationException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.service.ServiceException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ViewFavorisServlet extends ApplicationServlet {

    private static final long serialVersionUID = 1L;

    private static final String FORWARD_RESOURCE = "/WEB-INF/jsp/viewFavoris/viewIndex.jsp";

    public static final String FAVORIS_ATTRIBUTE_NAME = "favoris";

    private static final Log LOGGER = LogFactory.getLog(ViewFavorisServlet.class);

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
        try {
            beginTransaction();
            MembreDTO membreDTO = getMembreService().getMembre(getSession(),
                idMembre);
            List<FavoriDTO> favoris = (List<FavoriDTO>) membreDTO.getFavoris();
            request.setAttribute(ViewFavorisServlet.FAVORIS_ATTRIBUTE_NAME,
                favoris);
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
                ViewFavorisServlet.LOGGER.error(ExceptionUtils.getStackTrace(e1));
            }
            ViewFavorisServlet.LOGGER.error(ExceptionUtils.getStackTrace(e));
        }
        request.getRequestDispatcher(ViewFavorisServlet.FORWARD_RESOURCE).forward(request,
            response);
    }

}
