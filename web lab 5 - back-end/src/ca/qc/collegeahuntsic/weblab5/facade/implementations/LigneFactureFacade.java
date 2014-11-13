
package ca.qc.collegeahuntsic.weblab5.facade.implementations;

import java.util.List;
import ca.qc.collegeahuntsic.weblab5.bean.AchatBean;
import ca.qc.collegeahuntsic.weblab5.bean.LigneFactureBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab5.facade.interfaces.ILigneFactureFacade;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.ILigneFactureService;

public class LigneFactureFacade extends Facade implements ILigneFactureFacade {
    private ILigneFactureService service;

    public LigneFactureFacade(ILigneFactureService service) {
        setService(service);
    }

    private ILigneFactureService getService() {
        return this.service;
    }

    private void setService(ILigneFactureService service) {
        this.service = service;
    }

    @Override
    public List<LigneFactureBean> findByAchat(Connexion connexion,
        AchatBean achatBean) throws ServiceException {
        return getService().findByAchat(connexion,
            achatBean);
    }
}
