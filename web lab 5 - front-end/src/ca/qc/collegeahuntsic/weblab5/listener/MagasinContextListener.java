
package ca.qc.collegeahuntsic.weblab5.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import ca.qc.collegeahuntsic.weblab5.exception.MagasinException;
import ca.qc.collegeahuntsic.weblab5.util.MagasinCreateur;

public class MagasinContextListener implements ServletContextListener {

    private String USERNAME;

    private String PASSWORD;

    private String SERVER_TYPE;

    private String SERVER_NAME;

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // TODO Auto-generated method stub
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext sc = servletContextEvent.getServletContext();

        this.USERNAME = sc.getInitParameter("USERNAME");
        this.PASSWORD = sc.getInitParameter("PASSWORD");
        this.SERVER_TYPE = sc.getInitParameter("SERVER_TYPE");
        this.SERVER_NAME = sc.getInitParameter("SERVER_NAME");

        MagasinCreateur magasin;
        try {
            magasin = new MagasinCreateur(this.SERVER_TYPE,
                this.SERVER_NAME,
                this.USERNAME,
                this.PASSWORD);
            sc.setAttribute("magasin",
                magasin);
            sc.setAttribute("vedette",
                "6");
        } catch(MagasinException e) {
            e.printStackTrace();
        }
    }
}
