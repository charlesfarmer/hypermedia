package ca.qc.collegeahuntsic.weblab5.service.implementations;

import java.util.List;

import ca.qc.collegeahuntsic.weblab5.bean.AchatBean;
import ca.qc.collegeahuntsic.weblab5.bean.LigneFactureBean;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.ILigneFactureDAO;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.ILigneFactureService;

public class LigneFactureService extends Service implements ILigneFactureService {
	
	private ILigneFactureDAO ligneFactureDAO;
	
	public LigneFactureService(ILigneFactureDAO ligneFactureDAO){
		super();
		setLigneFactureDAO(ligneFactureDAO);
	}

	public ILigneFactureDAO getLigneFactureDAO() {
		return ligneFactureDAO;
	}

	public void setLigneFactureDAO(ILigneFactureDAO ligneFactureDAO) {
		this.ligneFactureDAO = ligneFactureDAO;
	}

	@Override
	public LigneFactureBean add(Connexion connexion,
			LigneFactureBean ligneFactureBean) throws ServiceException {
		try {
			return (LigneFactureBean) getLigneFactureDAO().add(connexion, ligneFactureBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public LigneFactureBean get(Connexion connexion,
			LigneFactureBean ligneFactureBean) throws ServiceException {
		try {
			return (LigneFactureBean) getLigneFactureDAO().get(connexion, ligneFactureBean.getIdLigneFacture());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void update(Connexion connexion, LigneFactureBean ligneFactureBean) throws ServiceException {
		try {
			getLigneFactureDAO().update(connexion, ligneFactureBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Connexion connexion, LigneFactureBean ligneFactureBean) throws ServiceException {
		try {
			getLigneFactureDAO().delete(connexion, ligneFactureBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<LigneFactureBean> findByAchat(Connexion connexion,
			AchatBean achatBean) throws ServiceException {
		try {
			return getLigneFactureDAO().findByAchat(connexion, achatBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}


}
