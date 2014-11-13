
package ca.qc.collegeahuntsic.weblab5.service.interfaces;

import java.util.List;
import ca.qc.collegeahuntsic.weblab5.bean.AchatBean;
import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.service.NotEnoughStockQuantityException;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;

public interface IAchatService extends IService {
    AchatBean add(Connexion connexion,
        AchatBean achatBean) throws ServiceException;

    AchatBean get(Connexion connexion,
        AchatBean achatBean) throws ServiceException;

    void update(Connexion connexion,
        AchatBean achatBean) throws ServiceException;

    void delete(Connexion connexion,
        AchatBean achatBean) throws ServiceException;

    AchatBean acheter(Connexion connexion,
        ClientBean clientBean) throws ServiceException,
        NotEnoughStockQuantityException;

    List<AchatBean> findByClient(Connexion connexion,
        ClientBean clientBean) throws ServiceException;
}
