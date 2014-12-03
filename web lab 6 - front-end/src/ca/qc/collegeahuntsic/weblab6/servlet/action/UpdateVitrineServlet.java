
package ca.qc.collegeahuntsic.weblab6.servlet.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ca.qc.collegeahuntsic.weblab6.dto.VitrineDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.ApplicationException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.dto.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab6.exception.service.ServiceException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UpdateVitrineServlet extends ApplicationServlet {

    private static final long serialVersionUID = 1L;

    private static final String FORWARD_RESOURCE = "viewVitrines.do";

    private static final Log LOGGER = LogFactory.getLog(UpdateVitrineServlet.class);

    public static final String UPDATE_STATUS_PARAMETER_NAME = "updateSuccessful";

    public static final String UPDATE_VITRINE_PARAMETER_NAME = "idVitrine";

    public static final String VITRINE_NEW_TITLE_PARAMETER_NAME = "new-title";

    private Boolean updateSuccessful;

    @Override
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        this.updateSuccessful = Boolean.FALSE;
        String idVitrine = request.getParameter(UpdateVitrineServlet.UPDATE_VITRINE_PARAMETER_NAME);
        String newTitle = request.getParameter(UpdateVitrineServlet.VITRINE_NEW_TITLE_PARAMETER_NAME);
        if(!idVitrine.isEmpty()) {
            try {
                beginTransaction();
                VitrineDTO vitrineDTO = getVitrineService().getVitrine(getSession(),
                    idVitrine);
                vitrineDTO.setTitle(newTitle);
                getVitrineService().updateVitrine(getSession(),
                    vitrineDTO);
                commitTransaction();
            } catch(
                ApplicationException
                | InvalidHibernateSessionException
                | InvalidPrimaryKeyException
                | ServiceException
                | InvalidDTOException e) {
                try {
                    rollbackTransaction();
                } catch(ApplicationException e1) {
                    UpdateVitrineServlet.LOGGER.fatal(ExceptionUtils.getStackTrace(e1));
                }
                UpdateVitrineServlet.LOGGER.fatal("problème de transaction");
            }
            this.updateSuccessful = Boolean.TRUE;
            request.setAttribute(UpdateVitrineServlet.UPDATE_STATUS_PARAMETER_NAME,
                this.updateSuccessful);
            request.getRequestDispatcher(UpdateVitrineServlet.FORWARD_RESOURCE).forward(request,
                response);
        } else {
            request.setAttribute(UpdateVitrineServlet.UPDATE_STATUS_PARAMETER_NAME,
                this.updateSuccessful);
            request.getRequestDispatcher(UpdateVitrineServlet.FORWARD_RESOURCE).forward(request,
                response);
        }
    }
}
