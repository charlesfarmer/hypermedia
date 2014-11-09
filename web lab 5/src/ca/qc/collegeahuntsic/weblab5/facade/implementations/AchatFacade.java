package ca.qc.collegeahuntsic.weblab5.facade.implementations;

import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;
import ca.qc.collegeahuntsic.weblab5.facade.interfaces.IAchatFacade;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.IAchatService;

public class AchatFacade extends Facade implements IAchatFacade {
	private IAchatService service;

	public AchatFacade(IAchatService service) {
		setService(service);
	}

	private IAchatService getService() {
		return service;
	}

	private void setService(IAchatService service) {
		this.service = service;
	}

	@Override
	public void acheter(Connexion connexion, ClientBean clientBean)
			throws FacadeException {
		// TODO Écrire la logique d'achat

	}
}
