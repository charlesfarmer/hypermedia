
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
import ca.qc.collegeahuntsic.weblab6.exception.service.UsernameAlreadyTakenException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RegisterMembreServlet extends ApplicationServlet {

    private static final long serialVersionUID = 1L;

    public static final String REGISTER_STATUS_ATTRIBUTE_NAME = "registerSuccessful";

    private static final String FORWARD_RESOURCE_SUCCESSFUL = "/WEB-INF/jsp/viewTemplate/viewIndex.jsp";

    private static final String FORWARD_RESOURCE_FAILED = "/WEB-INF/jsp/viewLogin/viewIndex.jsp";

    private static final Log LOGGER = LogFactory.getLog(RegisterMembreServlet.class);

    @Override
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        String username = request.getParameter(MembreDTO.USERNAME_COLUMN_NAME);
        String password = request.getParameter(MembreDTO.PASSWORD_COLUMN_NAME);
        String email = request.getParameter(MembreDTO.EMAIL_COLUMN_NAME);
        MembreDTO membre = new MembreDTO();
        membre.setUsername(username);
        membre.setPassword(password);
        membre.setEmail(email);
        Boolean registerSuccessful = Boolean.FALSE;
        try {
            beginTransaction();
            getMembreService().inscrireMembre(getSession(),
                membre);
            commitTransaction();
            registerSuccessful = Boolean.TRUE;
            request.getSession().setAttribute(LoginMembreServlet.MEMBRE_ATTRIBUTE_NAME,
                membre);
            RegisterMembreServlet.LOGGER.info("membre créé");
            request.setAttribute(RegisterMembreServlet.REGISTER_STATUS_ATTRIBUTE_NAME,
                registerSuccessful);
            request.getRequestDispatcher(RegisterMembreServlet.FORWARD_RESOURCE_SUCCESSFUL).forward(request,
                response);
        } catch(
            ApplicationException
            | InvalidHibernateSessionException
            | InvalidDTOException
            | UsernameAlreadyTakenException
            | ServiceException exception) {
            try {
                rollbackTransaction();
            } catch(ApplicationException e) {
                RegisterMembreServlet.LOGGER.warn(ExceptionUtils.getStackTrace(e));
            }
            RegisterMembreServlet.LOGGER.warn("échec création de membre");
            request.setAttribute(RegisterMembreServlet.REGISTER_STATUS_ATTRIBUTE_NAME,
                registerSuccessful);
            request.getRequestDispatcher(RegisterMembreServlet.FORWARD_RESOURCE_FAILED).forward(request,
                response);
        }
    }
}
