package ca.qc.collegeahuntsic.weblab5.service.implementations;

import java.util.List;

import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.bean.LignePanierBean;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.ILignePanierDAO;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IStockDAO;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab5.exception.service.NotEnoughStockQuantityException;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.ILignePanierService;

public class LignePanierService extends Service implements ILignePanierService {
	
	private ILignePanierDAO lignePanierDAO;
	private IStockDAO stockDAO;
	
	public LignePanierService(ILignePanierDAO lignePanierDAO, IStockDAO stockDAO){
		super();
		setLignePanierDAO(lignePanierDAO);
		setStockDAO(stockDAO);
	}

	public ILignePanierDAO getLignePanierDAO() {
		return lignePanierDAO;
	}

	public void setLignePanierDAO(ILignePanierDAO lignePanierDAO) {
		this.lignePanierDAO = lignePanierDAO;
	}

	public IStockDAO getStockDAO() {
		return stockDAO;
	}

	public void setStockDAO(IStockDAO stockDAO) {
		this.stockDAO = stockDAO;
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

	@Override
	public LignePanierBean ajouterAuPanier(Connexion connexion, LignePanierBean lignePanierBean) throws ServiceException, NotEnoughStockQuantityException{
		try {
			
			List<LignePanierBean> panier = getLignePanierDAO().findByClient(connexion, lignePanierBean.getClientBean());
			for(int i=0; i<panier.size(); i++){
				// Si...une ligne pour ce produit existe déjà, on update la quantité au lieu d'ajouter 
				if (panier.get(i).getProduitBean().getIdProduit().equals(lignePanierBean.getProduitBean().getIdProduit())){
					lignePanierBean.setQuantite(lignePanierBean.getQuantite() + panier.get(i).getQuantite());
					
					if (lignePanierBean.getProduitBean().getStockBean().getQuantite() < lignePanierBean.getQuantite()){
						throw new NotEnoughStockQuantityException();
					}
					
					update(connexion, lignePanierBean);
					return lignePanierBean;
				}
			}
			
			return add(connexion, lignePanierBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public void modifierNombreDitems(Connexion connexion, LignePanierBean lignePanierBean) throws ServiceException, NotEnoughStockQuantityException{
		
		try {
			
			if (lignePanierBean.getProduitBean().getStockBean().getQuantite() < lignePanierBean.getQuantite()){
				throw new NotEnoughStockQuantityException();
			}
			
			getLignePanierDAO().update(connexion, lignePanierBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
