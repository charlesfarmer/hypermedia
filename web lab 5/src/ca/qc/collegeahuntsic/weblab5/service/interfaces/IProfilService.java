package ca.qc.collegeahuntsic.weblab5.service.interfaces;

import ca.qc.collegeahuntsic.weblab5.bean.ProfilBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;

public interface IProfilService extends IService {
	ProfilBean add(Connexion connexion, ProfilBean profilBean) throws ServiceException;
	ProfilBean get(Connexion connexion, ProfilBean profilBean) throws ServiceException;
	void update(Connexion connexion, ProfilBean profilBean) throws ServiceException;
	void delete(Connexion connexion, ProfilBean profilBean) throws ServiceException;
}
