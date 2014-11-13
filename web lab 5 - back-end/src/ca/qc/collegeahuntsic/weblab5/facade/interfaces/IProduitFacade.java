
package ca.qc.collegeahuntsic.weblab5.facade.interfaces;

import ca.qc.collegeahuntsic.weblab5.bean.ProduitBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;

public interface IProduitFacade {

	ProduitBean get(Connexion connexion, ProduitBean produitBean)
			throws FacadeException;
    //
}
