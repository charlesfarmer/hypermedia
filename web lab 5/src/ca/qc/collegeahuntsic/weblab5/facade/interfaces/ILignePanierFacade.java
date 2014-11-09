package ca.qc.collegeahuntsic.weblab5.facade.interfaces;

import java.util.List;

import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.bean.LignePanierBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;

public interface ILignePanierFacade {
	LignePanierBean ajouterAuPanier(Connexion connexion, LignePanierBean lignePanierBean) throws FacadeException;
	void retirerDuPanier(Connexion connexion, LignePanierBean lignePanierBean) throws FacadeException;
	LignePanierBean modifierNombreDitems(Connexion connexion, LignePanierBean lignePanierBean) throws FacadeException;
	List<LignePanierBean> getPanier(Connexion connexion, ClientBean clientBean) throws FacadeException;
	void viderPanier(Connexion connexion, ClientBean clientBean) throws FacadeException;
}
