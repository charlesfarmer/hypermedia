
package ca.qc.collegeahuntsic.weblab5.facade.interfaces;

import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;
import ca.qc.collegeahuntsic.weblab5.exception.service.NotEnoughStockQuantityException;

public interface IAchatFacade extends IFacade {
    void acheter(Connexion connexion,
        ClientBean clientBean) throws FacadeException,
        NotEnoughStockQuantityException;
}