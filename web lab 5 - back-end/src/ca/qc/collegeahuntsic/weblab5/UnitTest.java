
package ca.qc.collegeahuntsic.weblab5;

import ca.qc.collegeahuntsic.weblab5.exception.MagasinException;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;
import ca.qc.collegeahuntsic.weblab5.util.MagasinCreateur;

public class UnitTest {
    public static void main(String args[]) throws MagasinException,
        FacadeException {
        MagasinCreateur magasin = new MagasinCreateur("distant",
            "xe",
            "scott",
            "tiger");
        magasin.getClientFacade().getClientByEmail(magasin.getConnexion(),
            "BigD@love.com");
    }
}
