
package ca.qc.collegeahuntsic.weblab5.facade.implementations;

import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;
import ca.qc.collegeahuntsic.weblab5.exception.service.EmailAlreadyUsedException;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab5.facade.interfaces.IClientFacade;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.IClientService;

public class ClientFacade extends Facade implements IClientFacade {
    private IClientService service;

    public ClientFacade(IClientService service) {
        setService(service);
    }

    private IClientService getService() {
        return this.service;
    }

    private void setService(IClientService service) {
        this.service = service;
    }

    @Override
    public ClientBean getClientByEmail(Connexion connexion,
        String email) throws FacadeException {
        try {
            return getService().findByEmail(connexion,
                email);
        } catch(ServiceException e) {
            throw new FacadeException(e);
        }
    }

    @Override
    public ClientBean ajouterClient(Connexion connexion,
        ClientBean clientBean) throws FacadeException,
        EmailAlreadyUsedException {
        try {
            return getService().ajouterClient(connexion,
                clientBean);
        } catch(ServiceException e) {
            throw new FacadeException(e);
        }
    }

    @Override
    public void modifierMotDePasse(Connexion connexion,
        ClientBean clientBean) throws FacadeException {
        try {
            getService().update(connexion,
                clientBean);
        } catch(ServiceException e) {
            throw new FacadeException(e);
        }
    }
}
