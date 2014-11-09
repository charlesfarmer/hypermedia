package ca.qc.collegeahuntsic.weblab5.facade.interfaces;

import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;
import ca.qc.collegeahuntsic.weblab5.exception.service.EmailAlreadyUsedException;

public interface IClientFacade extends IFacade {

	ClientBean getClientByEmail(Connexion connexion, String email)
			throws FacadeException;

	ClientBean ajouterClient(Connexion connexion, ClientBean clientBean)
			throws FacadeException, EmailAlreadyUsedException;
	
	ClientBean modifierMotDePasse(Connexion connexion, ClientBean clientBean) throws FacadeException;

}
