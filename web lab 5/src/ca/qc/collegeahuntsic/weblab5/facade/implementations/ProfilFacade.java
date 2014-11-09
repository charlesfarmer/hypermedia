package ca.qc.collegeahuntsic.weblab5.facade.implementations;

import ca.qc.collegeahuntsic.weblab5.facade.interfaces.IProfilFacade;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.IProfilService;

public class ProfilFacade extends Facade implements IProfilFacade {
	private IProfilService service;
	public ProfilFacade(IProfilService service){
		setService(service);
	}
	private IProfilService getService() {
		return service;
	}
	private void setService(IProfilService service) {
		this.service = service;
	}
}
