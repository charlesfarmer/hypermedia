
package ca.qc.collegeahuntsic.weblab5.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.bean.ProfilBean;
import ca.qc.collegeahuntsic.weblab5.exception.MagasinException;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;
import ca.qc.collegeahuntsic.weblab5.exception.service.EmailAlreadyUsedException;
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
            ProfilBean profilBean = new ProfilBean();
            profilBean.setNom("Zoe");
            profilBean.setPrenom("A");
            ClientBean clientBean = new ClientBean();
            clientBean.setEmail("a@a.a");
            clientBean.setPassword("abc");
            clientBean.setProfilBean(profilBean);
            magasin.getClientFacade().ajouterClient(magasin.getConnexion(),
                clientBean);
        } catch(
            MagasinException
            | FacadeException
            | EmailAlreadyUsedException e) {
            e.printStackTrace();
        }
    }
}
