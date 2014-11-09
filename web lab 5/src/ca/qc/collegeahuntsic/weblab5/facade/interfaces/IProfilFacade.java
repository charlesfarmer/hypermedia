package ca.qc.collegeahuntsic.weblab5.facade.interfaces;

import ca.qc.collegeahuntsic.weblab5.bean.ProfilBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;

public interface IProfilFacade {
	ProfilBean modifierProfil(Connexion connexion, ProfilBean profilBean);
}
