
package ca.qc.collegeahuntsic.weblab5.facade.implementations;

import java.util.List;
import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.bean.LignePanierBean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;
import ca.qc.collegeahuntsic.weblab5.exception.service.NotEnoughStockQuantityException;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab5.facade.interfaces.ILignePanierFacade;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.ILignePanierService;

public class LignePanierFacade extends Facade implements ILignePanierFacade {
    private ILignePanierService service;

    public LignePanierFacade(ILignePanierService service) {
        setService(service);
    }

    private ILignePanierService getService() {
        return this.service;
    }

    private void setService(ILignePanierService service) {
        this.service = service;
    }

    @Override
    public LignePanierBean ajouterAuPanier(Connexion connexion,
        LignePanierBean lignePanierBean) throws FacadeException,
        NotEnoughStockQuantityException {
        try {
            return getService().ajouterAuPanier(connexion,
                lignePanierBean);
        } catch(ServiceException e) {
            throw new FacadeException(e);
        }
    }

    @Override
    public void retirerDuPanier(Connexion connexion,
        LignePanierBean lignePanierBean) throws FacadeException {
        try {
            getService().delete(connexion,
                lignePanierBean);
        } catch(ServiceException e) {
            throw new FacadeException(e);
        }
    }

    @Override
    public void modifierNombreDitems(Connexion connexion,
        LignePanierBean lignePanierBean) throws FacadeException,
        NotEnoughStockQuantityException {
        try {
            getService().modifierNombreDitems(connexion,
                lignePanierBean);
        } catch(ServiceException e) {
            throw new FacadeException(e);
        }
    }

    @Override
    public List<LignePanierBean> getPanier(Connexion connexion,
        ClientBean clientBean) throws FacadeException {
        try {
            return getService().findByClient(connexion,
                clientBean);
        } catch(ServiceException e) {
            throw new FacadeException(e);
        }
    }

    @Override
    public void viderPanier(Connexion connexion,
        ClientBean clientBean) throws FacadeException {
        try {
            getService().deleteByClient(connexion,
                clientBean);
        } catch(ServiceException e) {
            throw new FacadeException(e);
        }
    }
}
