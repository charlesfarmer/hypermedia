
package ca.qc.collegeahuntsic.weblab5.service.interfaces;

import java.util.List;
import ca.qc.collegeahuntsic.weblab5.bean.AchatBean;
import ca.qc.collegeahuntsic.weblab5.bean.LigneFactureBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;

public interface ILigneFactureService extends IService {
    LigneFactureBean add(Connexion connexion,
        LigneFactureBean ligneFactureBean) throws ServiceException;

    LigneFactureBean get(Connexion connexion,
        LigneFactureBean ligneFactureBean) throws ServiceException;

    void update(Connexion connexion,
        LigneFactureBean ligneFactureBean) throws ServiceException;

    void delete(Connexion connexion,
        LigneFactureBean ligneFactureBean) throws ServiceException;

    List<LigneFactureBean> findByAchat(Connexion connexion,
        AchatBean achatBean) throws ServiceException;
}
