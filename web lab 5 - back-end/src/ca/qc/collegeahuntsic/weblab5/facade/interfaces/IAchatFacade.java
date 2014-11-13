
package ca.qc.collegeahuntsic.weblab5.facade.interfaces;

import java.util.List;
import ca.qc.collegeahuntsic.weblab5.bean.AchatBean;
import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;
import ca.qc.collegeahuntsic.weblab5.exception.service.NotEnoughStockQuantityException;

public interface IAchatFacade extends IFacade {
    AchatBean acheter(Connexion connexion,
        ClientBean clientBean) throws FacadeException,
        NotEnoughStockQuantityException;

    List<AchatBean> findByClient(Connexion connexion,
        ClientBean clientBean) throws FacadeException;
}
