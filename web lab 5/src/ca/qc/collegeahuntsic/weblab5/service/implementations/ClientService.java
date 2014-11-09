package ca.qc.collegeahuntsic.weblab5.service.implementations;

import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.bean.ProfilBean;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IClientDAO;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IProfilDAO;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab5.exception.service.EmailAlreadyUsedException;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.IClientService;

public class ClientService extends Service implements IClientService {
	
	private IClientDAO clientDAO;
	private IProfilDAO profilDAO;

	public ClientService(IClientDAO clientDAO, IProfilDAO profilDAO) {
		super();
		setClientDAO(clientDAO);
		setProfilDAO(profilDAO);
	}

	@Override
	public ClientBean add(Connexion connexion, ClientBean clientBean) throws ServiceException {
		try {
			return (ClientBean) getClientDAO().add(connexion, clientBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public ClientBean get(Connexion connexion, ClientBean clientBean) throws ServiceException {
		try {
			return (ClientBean) getClientDAO().get(connexion, clientBean.getIdClient());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void update(Connexion connexion, ClientBean clientBean) throws ServiceException {
		try {
			getClientDAO().update(connexion, clientBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Connexion connexion, ClientBean clientBean) throws ServiceException {
		try {
			getClientDAO().delete(connexion, clientBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ClientBean findByEmail(Connexion connexion, String email)
			throws ServiceException {
		try {
			return getClientDAO().findByEmail(connexion, email);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public ClientBean ajouterClient(Connexion connexion, ClientBean clientBean)
			throws ServiceException, EmailAlreadyUsedException {
		
		try{
			if( getClientDAO().findByEmail(connexion, clientBean.getEmail()) != null ){
				throw new EmailAlreadyUsedException();
			}
				
			
			clientBean.setProfilBean((ProfilBean)getProfilDAO().add(connexion, clientBean.getProfilBean()));
			return (ClientBean) getClientDAO().add(connexion, clientBean);
		}catch(DAOException e){
			throw new ServiceException(e);
		}
	}
	
	public IClientDAO getClientDAO() {
		return clientDAO;
	}

	public void setClientDAO(IClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	public IProfilDAO getProfilDAO() {
		return profilDAO;
	}

	public void setProfilDAO(IProfilDAO profilDAO) {
		this.profilDAO = profilDAO;
	}

}
