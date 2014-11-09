package ca.qc.collegeahuntsic.weblab5.dao.interfaces;

import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;

public interface IClientDAO extends IDAO {

	ClientBean findByEmail(Connexion connexion, String email) throws DAOException;

}
