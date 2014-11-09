package ca.qc.collegeahuntsic.weblab5.service.implementations;

import ca.qc.collegeahuntsic.weblab5.bean.AchatBean;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IAchatDAO;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.IAchatService;

public class AchatService extends Service implements IAchatService {

	private IAchatDAO achatDAO;

	public AchatService(IAchatDAO achatDAO) {
		super();
		setAchatDAO(achatDAO);
	}

	@Override
	public AchatBean add(Connexion connexion, AchatBean achatBean)
			throws ServiceException {
		try {
			return (AchatBean) getAchatDAO().add(connexion, achatBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public AchatBean get(Connexion connexion, AchatBean achatBean)
			throws ServiceException {
		try {
			return (AchatBean) getAchatDAO().get(connexion,
					achatBean.getIdAchat());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void update(Connexion connexion, AchatBean achatBean)
			throws ServiceException {
		try {
			getAchatDAO().update(connexion, achatBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Connexion connexion, AchatBean achatBean)
			throws ServiceException {
		try {
			getAchatDAO().delete(connexion, achatBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public IAchatDAO getAchatDAO() {
		return this.achatDAO;
	}

	public void setAchatDAO(IAchatDAO achatDAO) {
		this.achatDAO = achatDAO;
	}

}
