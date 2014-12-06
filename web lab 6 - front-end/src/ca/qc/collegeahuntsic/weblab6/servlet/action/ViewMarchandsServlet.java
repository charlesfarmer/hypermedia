
package ca.qc.collegeahuntsic.weblab6.servlet.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ca.qc.collegeahuntsic.weblab6.dto.MarchandDTO;
import ca.qc.collegeahuntsic.weblab6.dto.MembreDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.ApplicationException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.service.ServiceException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ViewMarchandsServlet extends ApplicationServlet {

    private static final long serialVersionUID = 1L;

    private static final String FORWARD_RESOURCE = "/WEB-INF/jsp/viewMarchands/viewIndex.jsp";

    public static final String MARCHANDS_ATTRIBUTE_NAME = "marchands";

    private static final Log LOGGER = LogFactory.getLog(ViewMarchandsServlet.class);

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
            List<MarchandDTO> marchands = (List<MarchandDTO>) membreDTO.getMarchands();
            request.setAttribute(ViewMarchandsServlet.MARCHANDS_ATTRIBUTE_NAME,
                marchands);
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
                ViewMarchandsServlet.LOGGER.error(ExceptionUtils.getStackTrace(e1));
            }
            ViewMarchandsServlet.LOGGER.error(ExceptionUtils.getStackTrace(e));
        }
        request.getRequestDispatcher(ViewMarchandsServlet.FORWARD_RESOURCE).forward(request,
            response);
    }
}
