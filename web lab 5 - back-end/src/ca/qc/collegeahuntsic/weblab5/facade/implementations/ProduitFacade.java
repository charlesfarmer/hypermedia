
package ca.qc.collegeahuntsic.weblab5.facade.implementations;

import ca.qc.collegeahuntsic.weblab5.facade.interfaces.IProduitFacade;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.IProduitService;

public class ProduitFacade extends Facade implements IProduitFacade {
    private IProduitService service;

    public ProduitFacade(IProduitService service) {
        setService(service);
    }

    @SuppressWarnings("unused")
    private IProduitService getService() {
        return this.service;
    }

    private void setService(IProduitService service) {
        this.service = service;
    }
}
