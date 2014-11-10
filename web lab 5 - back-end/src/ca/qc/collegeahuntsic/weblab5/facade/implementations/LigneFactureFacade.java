
package ca.qc.collegeahuntsic.weblab5.facade.implementations;

import ca.qc.collegeahuntsic.weblab5.facade.interfaces.ILigneFactureFacade;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.ILigneFactureService;

public class LigneFactureFacade extends Facade implements ILigneFactureFacade {
    private ILigneFactureService service;

    public LigneFactureFacade(ILigneFactureService service) {
        setService(service);
    }

    @SuppressWarnings("unused")
    private ILigneFactureService getService() {
        return this.service;
    }

    private void setService(ILigneFactureService service) {
        this.service = service;
    }
}
