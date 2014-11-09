
package ca.qc.collegeahuntsic.weblab5.service.interfaces;

import ca.qc.collegeahuntsic.weblab5.bean.AchatBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
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
}
