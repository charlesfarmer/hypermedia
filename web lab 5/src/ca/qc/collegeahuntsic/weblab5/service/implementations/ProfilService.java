package ca.qc.collegeahuntsic.weblab5.service.implementations;

import ca.qc.collegeahuntsic.weblab5.bean.ProfilBean;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IProfilDAO;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.IProfilService;

public class ProfilService extends Service implements IProfilService {

	private IProfilDAO profilDAO;
	
	public ProfilService(IProfilDAO profilDAO){
		super();
		setProfilDAO(profilDAO);
	}

	public IProfilDAO getProfilDAO() {
		return profilDAO;
	}
	public void setProfilDAO(IProfilDAO profilDAO) {
		this.profilDAO = profilDAO;
	}
	
	@Override
	public ProfilBean add(Connexion connexion, ProfilBean profilBean) throws ServiceException {
		try {
			return (ProfilBean) getProfilDAO().add(connexion, profilBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ProfilBean get(Connexion connexion, ProfilBean profilBean)
			throws ServiceException {
		try {
			return (ProfilBean) getProfilDAO().get(connexion, profilBean.getIdProfil());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void update(Connexion connexion, ProfilBean profilBean)
			throws ServiceException {
		try {
			getProfilDAO().update(connexion, profilBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Connexion connexion, ProfilBean profilBean)
			throws ServiceException {
		try {
			getProfilDAO().delete(connexion, profilBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
