
package ca.qc.collegeahuntsic.weblab5.dao.interfaces;

import java.util.List;
import ca.qc.collegeahuntsic.weblab5.bean.AchatBean;
import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;

public interface IAchatDAO extends IDAO {

    List<AchatBean> findByClient(Connexion connexion,
        ClientBean clientBean) throws DAOException;
}
