
package ca.qc.collegeahuntsic.weblab6.servlet.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ca.qc.collegeahuntsic.weblab6.dto.MembreDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.ApplicationException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dto.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab6.exception.service.ServiceException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UpdateMembreServlet extends ApplicationServlet {

    private static final long serialVersionUID = 1L;

    private static final String FORWARD_RESOURCE = "/WEB-INF/jsp/viewMembre/viewIndex.jsp";

    private static final Log LOGGER = LogFactory.getLog(UpdateMembreServlet.class);

    public static final String UPDATE_STATUS_PARAMETER_NAME = "updateSuccessful";

    private Boolean updateSuccessful;

    @Override
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        this.updateSuccessful = Boolean.FALSE;
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String ancien_password = request.getParameter("ancien_password");
        MembreDTO membre = (MembreDTO) request.getSession().getAttribute(LoginMembreServlet.MEMBRE_ATTRIBUTE_NAME);
        try {
            if(membre.getPassword().equals(ancien_password)) {
                if(password != null
                    && !password.isEmpty()) {
                    membre.setPassword(password);
                }
                membre.setEmail(email);
                beginTransaction();
                getMembreService().updateMembre(getSession(),
                    membre);
                commitTransaction();
                request.getSession().setAttribute(LoginMembreServlet.MEMBRE_ATTRIBUTE_NAME,
                    membre);
                this.updateSuccessful = Boolean.TRUE;
            }
        } catch(
            ServiceException
            | ApplicationException
            | InvalidHibernateSessionException
            | InvalidDTOException e) {
            try {
                rollbackTransaction();
            } catch(ApplicationException e1) {
                UpdateMembreServlet.LOGGER.error(ExceptionUtils.getStackTrace(e1));
            }
            UpdateMembreServlet.LOGGER.error("problème");
        }
        request.setAttribute(ViewMembreServlet.VIEW_MEMBRE_ATTRIBUTE_NAME,
            membre);
        request.setAttribute(UpdateMembreServlet.UPDATE_STATUS_PARAMETER_NAME,
            this.updateSuccessful);
        request.getRequestDispatcher(UpdateMembreServlet.FORWARD_RESOURCE).forward(request,
            response);
    }
}
