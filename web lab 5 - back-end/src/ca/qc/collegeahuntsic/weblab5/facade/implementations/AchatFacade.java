
package ca.qc.collegeahuntsic.weblab5.facade.implementations;

import java.util.List;
import ca.qc.collegeahuntsic.weblab5.bean.AchatBean;
import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;
import ca.qc.collegeahuntsic.weblab5.exception.service.NotEnoughStockQuantityException;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab5.facade.interfaces.IAchatFacade;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.IAchatService;

public class AchatFacade extends Facade implements IAchatFacade {
    private IAchatService service;

    public AchatFacade(IAchatService service) {
        setService(service);
    }

    private IAchatService getService() {
        return this.service;
    }

    private void setService(IAchatService service) {
        this.service = service;
    }

    @Override
    public AchatBean acheter(Connexion connexion,
        ClientBean clientBean) throws FacadeException,
        NotEnoughStockQuantityException {
        try {
            return getService().acheter(connexion,
                clientBean);
        } catch(ServiceException e) {
            throw new FacadeException(e);
        }
    }

    @Override
    public List<AchatBean> findByClient(Connexion connexion,
        ClientBean clientBean) throws FacadeException {
        try {
            return getService().findByClient(connexion,
                clientBean);
        } catch(ServiceException e) {
            throw new FacadeException(e);
        }
    }
}
