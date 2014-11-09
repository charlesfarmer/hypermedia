package ca.qc.collegeahuntsic.weblab5.service.implementations;

import ca.qc.collegeahuntsic.weblab5.bean.StockBean;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IStockDAO;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.IStockService;

public class StockService extends Service implements IStockService {

	private IStockDAO stockDAO;
	
	public StockService(IStockDAO stockDAO) {
		super();
		setStockDAO(stockDAO);
	}

	public IStockDAO getStockDAO() {
		return stockDAO;
	}

	public void setStockDAO(IStockDAO stockDAO) {
		this.stockDAO = stockDAO;
	}

	@Override
	public StockBean add(Connexion connexion, StockBean stockBean) throws ServiceException {
		try {
			return (StockBean) getStockDAO().add(connexion, stockBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public StockBean get(Connexion connexion, StockBean stockBean) throws ServiceException {
		try {
			return (StockBean) getStockDAO().get(connexion, stockBean.getIdStock());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void update(Connexion connexion, StockBean stockBean) throws ServiceException {
		try {
			getStockDAO().update(connexion, stockBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Connexion connexion, StockBean stockBean) throws ServiceException {
		try {
			getStockDAO().delete(connexion, stockBean);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
