
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
import ca.qc.collegeahuntsic.weblab5.facade.implementations.AchatFacade;
import ca.qc.collegeahuntsic.weblab5.facade.implementations.ClientFacade;
import ca.qc.collegeahuntsic.weblab5.facade.implementations.LigneFactureFacade;
import ca.qc.collegeahuntsic.weblab5.facade.implementations.LignePanierFacade;
import ca.qc.collegeahuntsic.weblab5.facade.implementations.ProduitFacade;
import ca.qc.collegeahuntsic.weblab5.facade.implementations.ProfilFacade;
import ca.qc.collegeahuntsic.weblab5.facade.implementations.StockFacade;
import ca.qc.collegeahuntsic.weblab5.facade.interfaces.IAchatFacade;
import ca.qc.collegeahuntsic.weblab5.facade.interfaces.IClientFacade;
import ca.qc.collegeahuntsic.weblab5.facade.interfaces.ILigneFactureFacade;
import ca.qc.collegeahuntsic.weblab5.facade.interfaces.ILignePanierFacade;
import ca.qc.collegeahuntsic.weblab5.facade.interfaces.IProduitFacade;
import ca.qc.collegeahuntsic.weblab5.facade.interfaces.IProfilFacade;
import ca.qc.collegeahuntsic.weblab5.facade.interfaces.IStockFacade;
import ca.qc.collegeahuntsic.weblab5.service.implementations.AchatService;
import ca.qc.collegeahuntsic.weblab5.service.implementations.ClientService;
import ca.qc.collegeahuntsic.weblab5.service.implementations.LigneFactureService;
import ca.qc.collegeahuntsic.weblab5.service.implementations.LignePanierService;
import ca.qc.collegeahuntsic.weblab5.service.implementations.ProduitService;
import ca.qc.collegeahuntsic.weblab5.service.implementations.ProfilService;
import ca.qc.collegeahuntsic.weblab5.service.implementations.StockService;

public class MagasinCreateur implements Serializable {

    private static final long serialVersionUID = 1L;

    private Connexion connexion;

    private IAchatFacade achatFacade;

    private IClientFacade clientFacade;

    private ILignePanierFacade lignePanierFacade;

    private IProduitFacade produitFacade;

    private IProfilFacade profilFacade;

    private IStockFacade stockFacade;

    private ILigneFactureFacade ligneFactureFacade;

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
            setAchatFacade(new AchatFacade(new AchatService(new AchatDAO(AchatBean.class),
                new LignePanierDAO(LignePanierBean.class),
                new StockDAO(StockBean.class),
                new LigneFactureDAO(LigneFactureBean.class),
                new ClientDAO(ClientBean.class),
                new ProfilDAO(ProfilBean.class))));
            setClientFacade(new ClientFacade(new ClientService(new ClientDAO(ClientBean.class),
                new ProfilDAO(ProfilBean.class))));
            setLignePanierFacade(new LignePanierFacade(new LignePanierService(new LignePanierDAO(LignePanierBean.class),
                new StockDAO(StockBean.class),
                new ClientDAO(ClientBean.class),
                new ProfilDAO(ProfilBean.class),
                new ProduitDAO(ProduitBean.class))));
            setProduitFacade(new ProduitFacade(new ProduitService(new ProduitDAO(ProduitBean.class),
                new StockDAO(StockBean.class))));
            setProfilFacade(new ProfilFacade(new ProfilService(new ProfilDAO(ProfilBean.class))));
            setStockFacade(new StockFacade(new StockService(new StockDAO(StockBean.class))));
            setLigneFactureFacade(new LigneFactureFacade(new LigneFactureService(new LigneFactureDAO(LigneFactureBean.class),
                new ClientDAO(ClientBean.class),
                new ProfilDAO(ProfilBean.class),
                new AchatDAO(AchatBean.class),
                new StockDAO(StockBean.class),
                new ProduitDAO(ProduitBean.class))));

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

    public IAchatFacade getAchatFacade() {
        return this.achatFacade;
    }

    public void setAchatFacade(IAchatFacade achatFacade) {
        this.achatFacade = achatFacade;
    }

    public IClientFacade getClientFacade() {
        return this.clientFacade;
    }

    public void setClientFacade(IClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    public ILignePanierFacade getLignePanierFacade() {
        return this.lignePanierFacade;
    }

    public void setLignePanierFacade(ILignePanierFacade lignePanierFacade) {
        this.lignePanierFacade = lignePanierFacade;
    }

    public IProduitFacade getProduitFacade() {
        return this.produitFacade;
    }

    public void setProduitFacade(IProduitFacade produitFacade) {
        this.produitFacade = produitFacade;
    }

    public IProfilFacade getProfilFacade() {
        return this.profilFacade;
    }

    public void setProfilFacade(IProfilFacade profilFacade) {
        this.profilFacade = profilFacade;
    }

    public IStockFacade getStockFacade() {
        return this.stockFacade;
    }

    public void setStockFacade(IStockFacade stockFacade) {
        this.stockFacade = stockFacade;
    }

    public ILigneFactureFacade getLigneFactureFacade() {
        return this.ligneFactureFacade;
    }

    public void setLigneFactureFacade(ILigneFactureFacade ligneFactureFacade) {
        this.ligneFactureFacade = ligneFactureFacade;
    }
}
