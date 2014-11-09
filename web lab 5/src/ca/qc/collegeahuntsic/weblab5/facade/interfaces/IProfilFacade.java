package ca.qc.collegeahuntsic.weblab5.facade.interfaces;

import ca.qc.collegeahuntsic.weblab5.bean.ProfilBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;

public interface IProfilFacade {
	ProfilBean modifierProfil(Connexion connexion, ProfilBean profilBean)
			throws FacadeException;
}
