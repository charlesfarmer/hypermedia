
package ca.qc.collegeahuntsic.weblab5.util;

import java.io.Serializable;
import java.sql.SQLException;
import ca.qc.collegeahuntsic.weblab5.bean.AchatBean;
import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.bean.LigneFactureBean;
import ca.qc.collegeahuntsic.weblab5.bean.LignePanierBean;
import ca.qc.collegeahuntsic.weblab5.bean.ProduitBean;
import ca.qc.collegeahuntsic.weblab5.bean.ProfilBean;
import ca.qc.collegeahuntsic.weblab5.bean.StockBean;
import ca.qc.collegeahuntsic.weblab5.dao.implementations.AchatDAO;
import ca.qc.collegeahuntsic.weblab5.dao.implementations.ClientDAO;
import ca.qc.collegeahuntsic.weblab5.dao.implementations.LigneFactureDAO;
import ca.qc.collegeahuntsic.weblab5.dao.implementations.LignePanierDAO;
import ca.qc.collegeahuntsic.weblab5.dao.implementations.ProduitDAO;
import ca.qc.collegeahuntsic.weblab5.dao.implementations.ProfilDAO;
import ca.qc.collegeahuntsic.weblab5.dao.implementations.StockDAO;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.MagasinException;
import ca.qc.collegeahuntsic.weblab5.exception.db.ConnexionException;
import ca.qc.collegeahuntsic.weblab5.exception.dto.InvalidDTOClassException;
import ca.qc.collegeahuntsic.weblab5.service.implementations.AchatService;
import ca.qc.collegeahuntsic.weblab5.service.implementations.ClientService;
import ca.qc.collegeahuntsic.weblab5.service.implementations.LigneFactureService;
import ca.qc.collegeahuntsic.weblab5.service.implementations.LignePanierService;
import ca.qc.collegeahuntsic.weblab5.service.implementations.ProduitService;
import ca.qc.collegeahuntsic.weblab5.service.implementations.ProfilService;
import ca.qc.collegeahuntsic.weblab5.service.implementations.StockService;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.IAchatService;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.IClientService;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.ILigneFactureService;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.ILignePanierService;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.IProduitService;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.IProfilService;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.IStockService;

public class MagasinCreateur implements Serializable {

    private static final long serialVersionUID = 1L;

    private Connexion connexion;

    private IAchatService achatService;

    private IClientService clientService;

    private ILignePanierService lignePanierService;

    private IProduitService produitService;

    private IProfilService profilService;

    private IStockService stockService;

    private ILigneFactureService ligneFactureService;

    public MagasinCreateur(String serveur,
        String bd,
        String user,
        String password) throws MagasinException {

        super();

        try {

            setConnexion(new Connexion(serveur,
                bd,
                user,
                password));
            setAchatService(new AchatService(new AchatDAO(AchatBean.class),
                new LignePanierDAO(LignePanierBean.class),
                new StockDAO(StockBean.class),
                new LigneFactureDAO(LigneFactureBean.class)));
            setClientService(new ClientService(new ClientDAO(ClientBean.class),
                new ProfilDAO(ProfilBean.class)));
            setLignePanierService(new LignePanierService(new LignePanierDAO(LignePanierBean.class),
                new StockDAO(StockBean.class)));
            setProduitService(new ProduitService(new ProduitDAO(ProduitBean.class)));
            setProfilService(new ProfilService(new ProfilDAO(ProfilBean.class)));
            setStockService(new StockService(new StockDAO(StockBean.class)));
            setLigneFactureService(new LigneFactureService(new LigneFactureDAO(LigneFactureBean.class)));

        } catch(
            ConnexionException
            | InvalidDTOClassException e) {
            throw new MagasinException(e);
        }

    }

    public void close() throws MagasinException {
        try {
            getConnexion().close();
        } catch(SQLException e) {
            throw new MagasinException(e);
        }
    }

    public void commit() throws MagasinException {
        try {
            getConnexion().commit();
        } catch(SQLException e) {
            throw new MagasinException(e);
        }
    }

    public void rollback() throws MagasinException {
        try {
            getConnexion().rollback();
        } catch(SQLException e) {
            throw new MagasinException(e);
        }
    }

    public Connexion getConnexion() {
        return this.connexion;
    }

    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
    }

    public IAchatService getAchatService() {
        return this.achatService;
    }

    public void setAchatService(IAchatService achatService) {
        this.achatService = achatService;
    }

    public IClientService getClientService() {
        return this.clientService;
    }

    public void setClientService(IClientService clientService) {
        this.clientService = clientService;
    }

    public ILignePanierService getLignePanierService() {
        return this.lignePanierService;
    }

    public void setLignePanierService(ILignePanierService lignePanierService) {
        this.lignePanierService = lignePanierService;
    }

    public IProduitService getProduitService() {
        return this.produitService;
    }

    public void setProduitService(IProduitService produitService) {
        this.produitService = produitService;
    }

    public IProfilService getProfilService() {
        return this.profilService;
    }

    public void setProfilService(IProfilService profilService) {
        this.profilService = profilService;
    }

    public IStockService getStockService() {
        return this.stockService;
    }

    public void setStockService(IStockService stockService) {
        this.stockService = stockService;
    }

    public ILigneFactureService getLigneFactureService() {
        return this.ligneFactureService;
    }

    public void setLigneFactureService(ILigneFactureService ligneFactureService) {
        this.ligneFactureService = ligneFactureService;
    }
}
