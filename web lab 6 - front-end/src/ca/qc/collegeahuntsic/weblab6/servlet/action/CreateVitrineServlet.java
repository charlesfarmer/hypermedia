
package ca.qc.collegeahuntsic.weblab6.servlet.action;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ca.qc.collegeahuntsic.weblab6.dto.MembreDTO;
import ca.qc.collegeahuntsic.weblab6.dto.VitrineDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.ApplicationException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dto.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab6.exception.service.ServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CreateVitrineServlet extends ApplicationServlet {

    private static final long serialVersionUID = 1L;

    private static final String FORWARD_RESOURCE = "viewVitrines.do";

    private static final Log LOGGER = LogFactory.getLog(CreateVitrineServlet.class);

    public static final String CREATE_STATUS_PARAMETER_NAME = "createSuccessful";

    public static final String VITRINE_TITLE_PARAMETER_NAME = "vitrineTitle";

    private Boolean createSuccessful;

    @Override
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        this.createSuccessful = Boolean.FALSE;
        String titleVitrine = request.getParameter(CreateVitrineServlet.VITRINE_TITLE_PARAMETER_NAME);
        if(!titleVitrine.isEmpty()) {
            try {
                beginTransaction();
                VitrineDTO vitrineDTO = new VitrineDTO();
                vitrineDTO.setDateAdded(new Timestamp(System.currentTimeMillis()));
                vitrineDTO.setTitle(titleVitrine);
                MembreDTO membreDTO = (MembreDTO) request.getSession().getAttribute(LoginMembreServlet.MEMBRE_ATTRIBUTE_NAME);
                vitrineDTO.setMembreDTO(membreDTO);
                getVitrineService().addVitrine(getSession(),
                    vitrineDTO);
                commitTransaction();
                this.createSuccessful = Boolean.TRUE;
            } catch(
                ApplicationException
                | InvalidHibernateSessionException
                | InvalidDTOException
                | ServiceException e) {
                try {
                    rollbackTransaction();
                } catch(ApplicationException e1) {
                    CreateVitrineServlet.LOGGER.fatal("problème de rollback");
                }
                CreateVitrineServlet.LOGGER.fatal("problème de création de vitrine");
            }
        }
        request.setAttribute(CreateVitrineServlet.CREATE_STATUS_PARAMETER_NAME,
            this.createSuccessful);
        request.getRequestDispatcher(CreateVitrineServlet.FORWARD_RESOURCE).forward(request,
            response);
    }
}
