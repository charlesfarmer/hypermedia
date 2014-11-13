
package ca.qc.collegeahuntsic.weblab5.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import ca.qc.collegeahuntsic.weblab5.exception.MagasinException;
import ca.qc.collegeahuntsic.weblab5.util.MagasinCreateur;

public class MagasinContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // TODO Auto-generated method stub
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        MagasinCreateur magasin;
        try {
            magasin = new MagasinCreateur("distant",
                "xe",
                "scott",
                "tiger");
            servletContextEvent.getServletContext().setAttribute("magasin",
                magasin);
            servletContextEvent.getServletContext().setAttribute("test",
                "1");
        } catch(MagasinException e) {
            e.printStackTrace();
        }
    }
}
