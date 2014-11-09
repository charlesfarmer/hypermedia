package ca.qc.collegeahuntsic.weblab5.service.implementations;

import ca.qc.collegeahuntsic.weblab5.bean.ProduitBean;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IProduitDAO;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.IProduitService;

public class ProduitService extends Service implements IProduitService {

	private IProduitDAO produitDAO;

	public ProduitService(IProduitDAO produitDAO) {
		super();
		setProduitDAO(produitDAO);
	}

	public IProduitDAO getProduitDAO() {
		return this.produitDAO;
	}

	public void setProduitDAO(IProduitDAO produitDAO) {
		this.produitDAO = produitDAO;
	}

	@Override
	public ProduitBean add(Connexion connexion, ProduitBean produitBean)
			throws ServiceException {
		try {
			return (ProduitBean) getProduitDAO().add(connexion, produitBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ProduitBean get(Connexion connexion, ProduitBean produitBean)
			throws ServiceException {
		try {
			return (ProduitBean) getProduitDAO().get(connexion,
					produitBean.getIdProduit());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void update(Connexion connexion, ProduitBean produitBean)
			throws ServiceException {
		try {
			getProduitDAO().update(connexion, produitBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Connexion connexion, ProduitBean produitBean)
			throws ServiceException {
		try {
			getProduitDAO().delete(connexion, produitBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
