
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

    private static final String LOGIN_MEMBRE_SUCCESSFUL = "loginMembreSuccessful";

    private static final String MEMBRE_ATTRIBUTE_NAME = "membre";

    private static final String FORWARD_RESOURCE = "/WEB-INF/jsp/viewLogin/viewLoginIndex.jsp";

    private static final Log LOGGER = LogFactory.getLog(LoginMembreServlet.class);

    @Override
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) {
        String username = request.getParameter(MembreDTO.USERNAME_COLUMN_NAME);
        String password = request.getParameter(MembreDTO.PASSWORD_COLUMN_NAME);
        try {
            beginTransaction();
            List<MembreDTO> usernames = getMembreService().findByUsername(getSession(),
                username);
            commitTransaction();
            for(MembreDTO membre : usernames) {
                if(membre.getPassword().equals(password)) {
                    request.setAttribute(LoginMembreServlet.LOGIN_MEMBRE_SUCCESSFUL,
                        Boolean.TRUE);
                    HttpSession session = request.getSession();
                    session.setAttribute(LoginMembreServlet.MEMBRE_ATTRIBUTE_NAME,
                        membre);
                    LoginMembreServlet.LOGGER.warn("connexion réussie");
                }
            }
            request.getRequestDispatcher(LoginMembreServlet.FORWARD_RESOURCE).forward(request,
                response);
        } catch(
            ServiceException
            | ServletException
            | IOException
            | ApplicationException exception) {
            LoginMembreServlet.LOGGER.fatal(exception);
        }
    }
}
