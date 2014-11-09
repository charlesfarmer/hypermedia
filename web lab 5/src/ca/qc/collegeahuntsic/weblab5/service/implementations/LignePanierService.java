package ca.qc.collegeahuntsic.weblab5.service.implementations;

import java.util.List;

import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.bean.LignePanierBean;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.ILignePanierDAO;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.ILignePanierService;

public class LignePanierService extends Service implements ILignePanierService {
	
	private ILignePanierDAO lignePanierDAO;
	
	public LignePanierService(ILignePanierDAO lignePanierDAO){
		super();
		setLignePanierDAO(lignePanierDAO);
	}

	public ILignePanierDAO getLignePanierDAO() {
		return lignePanierDAO;
	}

	public void setLignePanierDAO(ILignePanierDAO lignePanierDAO) {
		this.lignePanierDAO = lignePanierDAO;
	}

	@Override
	public LignePanierBean add(Connexion connexion,
			LignePanierBean lignePanierBean) throws ServiceException {
		try {
			return (LignePanierBean) getLignePanierDAO().add(connexion, lignePanierBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public LignePanierBean get(Connexion connexion,
			LignePanierBean lignePanierBean) throws ServiceException {
		try {
			return (LignePanierBean) getLignePanierDAO().get(connexion, lignePanierBean.getIdLignePanier());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void update(Connexion connexion, LignePanierBean lignePanierBean) throws ServiceException {
		try {
			getLignePanierDAO().update(connexion, lignePanierBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Connexion connexion, LignePanierBean lignePanierBean) throws ServiceException {
		try {
			getLignePanierDAO().delete(connexion, lignePanierBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<LignePanierBean> findByClient(Connexion connexion,
			ClientBean clientBean) throws ServiceException {
		try {
			return getLignePanierDAO().findByClient(connexion, clientBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteByClient(Connexion connexion, ClientBean clientBean) throws ServiceException {
		try {
			getLignePanierDAO().deleteByClient(connexion, clientBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
