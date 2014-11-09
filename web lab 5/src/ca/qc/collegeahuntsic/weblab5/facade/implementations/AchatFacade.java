package ca.qc.collegeahuntsic.weblab5.facade.implementations;

import ca.qc.collegeahuntsic.weblab5.facade.interfaces.IAchatFacade;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.IAchatService;

public class AchatFacade extends Facade implements IAchatFacade {
	private IAchatService service;
	
	public AchatFacade(IAchatService service){
		setService(service);
	}
	
	private IAchatService getService() {
		return service;
	}
	private void setService(IAchatService service) {
		this.service = service;
	}
}
