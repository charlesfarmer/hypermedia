
package ca.qc.collegeahuntsic.weblab5.service.interfaces;

import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.service.EmailAlreadyUsedException;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;

public interface IClientService extends IService {
    ClientBean add(Connexion connexion,
        ClientBean clientBean) throws ServiceException;

    ClientBean get(Connexion connexion,
        ClientBean clientBean) throws ServiceException;

    void update(Connexion connexion,
        ClientBean clientBean) throws ServiceException;

    void delete(Connexion connexion,
        ClientBean clientBean) throws ServiceException;

    ClientBean findByEmail(Connexion connexion,
        String email) throws ServiceException;

    ClientBean ajouterClient(Connexion connexion,
        ClientBean clientBean) throws ServiceException,
        EmailAlreadyUsedException;
}
