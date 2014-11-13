
package ca.qc.collegeahuntsic.weblab5.facade.implementations;

import ca.qc.collegeahuntsic.weblab5.bean.ProduitBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab5.facade.interfaces.IProduitFacade;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.IProduitService;

public class ProduitFacade extends Facade implements IProduitFacade {
    private IProduitService service;

    public ProduitFacade(IProduitService service) {
        setService(service);
    }

    private IProduitService getService() {
        return this.service;
    }

    private void setService(IProduitService service) {
        this.service = service;
    }

    @Override
    public ProduitBean get(Connexion connexion,
        ProduitBean produitBean) throws FacadeException {
        try {
            return getService().get(connexion,
                produitBean);
        } catch(ServiceException e) {
            throw new FacadeException(e);
        }
    }
}
