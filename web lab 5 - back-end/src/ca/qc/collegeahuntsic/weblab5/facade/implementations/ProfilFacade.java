
package ca.qc.collegeahuntsic.weblab5.facade.implementations;

import ca.qc.collegeahuntsic.weblab5.bean.ProfilBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab5.facade.interfaces.IProfilFacade;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.IProfilService;

public class ProfilFacade extends Facade implements IProfilFacade {
    private IProfilService service;

    public ProfilFacade(IProfilService service) {
        setService(service);
    }

    private IProfilService getService() {
        return this.service;
    }

    private void setService(IProfilService service) {
        this.service = service;
    }

    @Override
    public ProfilBean modifierProfil(Connexion connexion,
        ProfilBean profilBean) throws FacadeException {
        try {
            getService().update(connexion,
                profilBean);
            return getService().get(connexion,
                profilBean);
        } catch(ServiceException e) {
            throw new FacadeException(e);
        }
    }
}
