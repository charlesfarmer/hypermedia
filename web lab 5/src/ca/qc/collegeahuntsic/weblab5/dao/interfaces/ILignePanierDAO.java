package ca.qc.collegeahuntsic.weblab5.dao.interfaces;

import java.util.List;

import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.bean.LignePanierBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;

public interface ILignePanierDAO extends IDAO {

	List<LignePanierBean> findByClient(Connexion connexion,
			ClientBean clientBean) throws DAOException;

	void deleteByClient(Connexion connexion, ClientBean clientBean)
			throws DAOException;

}
