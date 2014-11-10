
package ca.qc.collegeahuntsic.weblab5.service.interfaces;

import ca.qc.collegeahuntsic.weblab5.bean.StockBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;

public interface IStockService extends IService {
    StockBean add(Connexion connexion,
        StockBean stockBean) throws ServiceException;

    StockBean get(Connexion connexion,
        StockBean stockBean) throws ServiceException;

    void update(Connexion connexion,
        StockBean stockBean) throws ServiceException;

    void delete(Connexion connexion,
        StockBean stockBean) throws ServiceException;
}
