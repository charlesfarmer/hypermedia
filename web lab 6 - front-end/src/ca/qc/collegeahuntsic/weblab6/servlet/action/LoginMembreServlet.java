
package ca.qc.collegeahuntsic.weblab6.servlet.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ca.qc.collegeahuntsic.weblab6.dto.MembreDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.ApplicationException;
import ca.qc.collegeahuntsic.weblab6.exception.service.ServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginMembreServlet extends ApplicationServlet {

    private static final long serialVersionUID = 1L;

    public static final String MEMBRE_ATTRIBUTE_NAME = "membre";

    public static final String LOGIN_STATUS_ATTRIBUTE_NAME = "loginSuccessful";

    private static final String FORWARD_RESOURCE_SUCCESSFUL_LOGIN = "/WEB-INF/jsp/viewTemplate/viewIndex.jsp";

    private static final String FORWARD_RESOURCE_FAILED_LOGIN = "/WEB-INF/jsp/viewLogin/viewIndex.jsp";

    private static final Log LOGGER = LogFactory.getLog(LoginMembreServlet.class);

    @Override
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) {
        String username = request.getParameter(MembreDTO.USERNAME_COLUMN_NAME);
        String password = request.getParameter(MembreDTO.PASSWORD_COLUMN_NAME);
        Boolean loginSuccessful = Boolean.FALSE;
        try {
            beginTransaction();
            List<MembreDTO> membres = getMembreService().findByUsername(getSession(),
                username);
            commitTransaction();
            for(MembreDTO membre : membres) {
                if(membre.getPassword().equals(password)) {
                    loginSuccessful = Boolean.TRUE;
                    HttpSession session = request.getSession();
                    session.setAttribute(LoginMembreServlet.MEMBRE_ATTRIBUTE_NAME,
                        membre);
                    LoginMembreServlet.LOGGER.warn("connexion réussie");
                }
            }
            if(loginSuccessful.booleanValue()) {
                request.setAttribute(LoginMembreServlet.LOGIN_STATUS_ATTRIBUTE_NAME,
                    loginSuccessful);
                request.getRequestDispatcher(LoginMembreServlet.FORWARD_RESOURCE_SUCCESSFUL_LOGIN).forward(request,
                    response);
            } else {
                request.setAttribute(LoginMembreServlet.LOGIN_STATUS_ATTRIBUTE_NAME,
                    loginSuccessful);
                request.getRequestDispatcher(LoginMembreServlet.FORWARD_RESOURCE_FAILED_LOGIN).forward(request,
                    response);
            }
        } catch(
            ServiceException
            | ServletException
            | IOException
            | ApplicationException exception) {
            LoginMembreServlet.LOGGER.fatal(exception);
        }
    }
}
