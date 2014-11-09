
package ca.qc.collegeahuntsic.weblab5.dao.interfaces;

import java.util.List;
import ca.qc.collegeahuntsic.weblab5.bean.AchatBean;
import ca.qc.collegeahuntsic.weblab5.bean.LigneFactureBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;

public interface ILigneFactureDAO extends IDAO {

    List<LigneFactureBean> findByAchat(Connexion connexion,
        AchatBean achatBean) throws DAOException;

}
