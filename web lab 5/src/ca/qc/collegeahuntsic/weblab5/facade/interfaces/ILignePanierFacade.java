package ca.qc.collegeahuntsic.weblab5.facade.interfaces;

import java.util.List;

import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.bean.LignePanierBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;
import ca.qc.collegeahuntsic.weblab5.exception.service.NotEnoughStockQuantityException;

public interface ILignePanierFacade {
	LignePanierBean ajouterAuPanier(Connexion connexion, LignePanierBean lignePanierBean) throws FacadeException, NotEnoughStockQuantityException;
	void retirerDuPanier(Connexion connexion, LignePanierBean lignePanierBean) throws FacadeException;
	void modifierNombreDitems(Connexion connexion, LignePanierBean lignePanierBean) throws FacadeException, NotEnoughStockQuantityException;
	List<LignePanierBean> getPanier(Connexion connexion, ClientBean clientBean) throws FacadeException;
	void viderPanier(Connexion connexion, ClientBean clientBean) throws FacadeException;
}
