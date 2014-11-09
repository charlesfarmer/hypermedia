package ca.qc.collegeahuntsic.weblab5.facade.implementations;

import ca.qc.collegeahuntsic.weblab5.facade.interfaces.ILignePanierFacade;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.ILignePanierService;

public class LignePanierFacade extends Facade implements ILignePanierFacade {
	private ILignePanierService service;
	
	public LignePanierFacade(ILignePanierService service){
		setService(service);
	}

	private ILignePanierService getService() {
		return service;
	}

	private void setService(ILignePanierService service) {
		this.service = service;
	}
}
