package ca.qc.collegeahuntsic.weblab5.service.interfaces;

import java.util.List;

import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.bean.LignePanierBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.service.NotEnoughStockQuantityException;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;

public interface ILignePanierService extends IService {
	LignePanierBean add(Connexion connexion, LignePanierBean lignePanierBean) throws ServiceException;
	LignePanierBean get(Connexion connexion, LignePanierBean lignePanierBean) throws ServiceException;
	void update(Connexion connexion, LignePanierBean lignePanierBean) throws ServiceException;
	void delete(Connexion connexion, LignePanierBean lignePanierBean) throws ServiceException;
	List<LignePanierBean> findByClient(Connexion connexion, ClientBean clientBean) throws ServiceException;
	void deleteByClient(Connexion connexion, ClientBean clientBean) throws ServiceException;
	LignePanierBean ajouterAuPanier(Connexion connexion,
			LignePanierBean lignePanierBean) throws ServiceException, NotEnoughStockQuantityException;
	void modifierNombreDitems(Connexion connexion,
			LignePanierBean lignePanierBean) throws ServiceException,
			NotEnoughStockQuantityException;
}
