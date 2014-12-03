
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DeleteVitrineServlet extends ApplicationServlet {

    private static final long serialVersionUID = 1L;

    private static final String FORWARD_RESOURCE = "viewVitrines.do";

    private static final Log LOGGER = LogFactory.getLog(DeleteVitrineServlet.class);

    public static final String DELETE_STATUS_ATTRIBUTE_NAME = "deleteSuccessful";

    public static final String DELETE_VITRINE_ATTRIBUTE_NAME = "idVitrine";

    public static final String DELETE_VITRINE_TITLE_ATTRIBUTE_NAME = "deletedVitrineTitle";

    private Boolean deleteSuccessful;

    @Override
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        this.deleteSuccessful = Boolean.FALSE;
        String idVitrine = request.getParameter(DeleteVitrineServlet.DELETE_VITRINE_ATTRIBUTE_NAME);
        String idMembre = request.getParameter(ViewMembreServlet.MEMBRE_ID_ATTRIBUTE_NAME);
        String deletedVitrineTitle = "";
        if(!idVitrine.isEmpty()
            && !idMembre.isEmpty()) {
            try {
                beginTransaction();
                VitrineDTO vitrine = getVitrineService().getVitrine(getSession(),
                    idVitrine);
                deletedVitrineTitle = vitrine.getTitle();
                getVitrineService().deleteVitrine(getSession(),
                    vitrine);
                commitTransaction();
                this.deleteSuccessful = Boolean.TRUE;
            } catch(
                ApplicationException
                | InvalidHibernateSessionException
                | InvalidPrimaryKeyException
                | ServiceException
                | InvalidDTOException e) {
                try {
                    rollbackTransaction();
                } catch(ApplicationException e1) {
                    DeleteVitrineServlet.LOGGER.fatal("problème de rollback");
                }
                DeleteVitrineServlet.LOGGER.fatal("problème de transaction");
            }
        }
        request.setAttribute(DeleteVitrineServlet.DELETE_STATUS_ATTRIBUTE_NAME,
            this.deleteSuccessful);
        request.setAttribute(DeleteVitrineServlet.DELETE_VITRINE_TITLE_ATTRIBUTE_NAME,
            deletedVitrineTitle);
        request.setAttribute(ViewMembreServlet.MEMBRE_ID_ATTRIBUTE_NAME,
            idMembre);
        request.getRequestDispatcher(DeleteVitrineServlet.FORWARD_RESOURCE).forward(request,
            response);
    }
}
