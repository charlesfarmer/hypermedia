
package ca.qc.collegeahuntsic.weblab5;

import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.bean.ProfilBean;
import ca.qc.collegeahuntsic.weblab5.exception.MagasinException;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;
import ca.qc.collegeahuntsic.weblab5.exception.service.EmailAlreadyUsedException;
import ca.qc.collegeahuntsic.weblab5.util.MagasinCreateur;

public class UnitTest {
    public static void main(String args[]) throws MagasinException,
        FacadeException {
        MagasinCreateur magasin = new MagasinCreateur("distant",
            "xe",
            "scott",
            "tiger");
        ClientBean clientBean = new ClientBean();
        clientBean.setEmail("a@a.a");
        clientBean.setPassword("a");
        ProfilBean profil = new ProfilBean();
        profil.setNom("");
        profil.setPrenom("");
        clientBean.setProfilBean(profil);
        try {
            clientBean = magasin.getClientFacade().ajouterClient(magasin.getConnexion(),
                clientBean);
            System.out.println(clientBean.getIdClient());
        } catch(EmailAlreadyUsedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
