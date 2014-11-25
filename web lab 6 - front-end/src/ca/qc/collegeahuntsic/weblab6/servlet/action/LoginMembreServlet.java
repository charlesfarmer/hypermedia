
package ca.qc.collegeahuntsic.weblab6.servlet.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ca.qc.collegeahuntsic.weblab6.dto.MembreDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.ApplicationException;
import ca.qc.collegeahuntsic.weblab6.exception.service.ServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

public class LoginMembreServlet extends ApplicationServlet {

    private static final long serialVersionUID = 1L;

    private static final String LOGIN_MEMBRE_SUCCESSFUL = "loginMembreSuccessful";

    private static final Log LOGGER = LogFactory.getLog(LoginMembreServlet.class);

    private static final String FORWARD_RESOURCE = "/WEB-INF/jsp/viewLogin/viewLoginIndex.jsp";

    @Override
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) {
        String u = request.getParameter(MembreDTO.USERNAME_COLUMN_NAME);
        String p = request.getParameter(MembreDTO.PASSWORD_COLUMN_NAME);
        try {
            beginTransaction();
            Session session = getSession();
            LoginMembreServlet.LOGGER.warn(session.toString()
                + "aaaaaaa");
            List<MembreDTO> list = getMembreService().findByUsername(getSession(),
                u);
            commitTransaction();
            for(MembreDTO m : list) {
                if(m.getUsername().equals(u)
                    && m.getPassword().equals(p)) {
                    LoginMembreServlet.LOGGER.warn("Bravo, tu es connecté");
                    request.setAttribute(LoginMembreServlet.LOGIN_MEMBRE_SUCCESSFUL,
                        Boolean.TRUE);
                }
            }

            request.getRequestDispatcher(LoginMembreServlet.FORWARD_RESOURCE).forward(request,
                response);
        } catch(
            ServiceException
            | ServletException
            | IOException
            | ApplicationException e) {
            LoginMembreServlet.LOGGER.fatal(e);
        }
    }
}
