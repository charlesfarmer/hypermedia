package ca.qc.collegeahuntsic.weblab5.service.interfaces;

import ca.qc.collegeahuntsic.weblab5.bean.ProduitBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;

public interface IProduitService extends IService {
	ProduitBean add(Connexion connexion, ProduitBean produitBean) throws ServiceException;
	ProduitBean get(Connexion connexion, ProduitBean produitBean) throws ServiceException;
	void update(Connexion connexion, ProduitBean produitBean) throws ServiceException;
	void delete(Connexion connexion, ProduitBean produitBean) throws ServiceException;
}
