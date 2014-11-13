
package ca.qc.collegeahuntsic.weblab5.facade.interfaces;

import java.util.List;
import ca.qc.collegeahuntsic.weblab5.bean.AchatBean;
import ca.qc.collegeahuntsic.weblab5.bean.LigneFactureBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;

public interface ILigneFactureFacade {
    List<LigneFactureBean> findByAchat(Connexion connexion,
        AchatBean achatBean) throws ServiceException;
}
