
package ca.qc.collegeahuntsic.weblab5.service.implementations;

import java.util.List;
import ca.qc.collegeahuntsic.weblab5.bean.AchatBean;
import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.bean.LigneFactureBean;
import ca.qc.collegeahuntsic.weblab5.bean.ProduitBean;
import ca.qc.collegeahuntsic.weblab5.bean.ProfilBean;
import ca.qc.collegeahuntsic.weblab5.bean.StockBean;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IAchatDAO;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IClientDAO;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.ILigneFactureDAO;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IProduitDAO;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IProfilDAO;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IStockDAO;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.ILigneFactureService;

public class LigneFactureService extends Service implements ILigneFactureService {

    private ILigneFactureDAO ligneFactureDAO;

    private IClientDAO clientDAO;

    private IProfilDAO profilDAO;

    private IAchatDAO achatDAO;

    private IStockDAO stockDAO;

    private IProduitDAO produitDAO;

    public LigneFactureService(ILigneFactureDAO ligneFactureDAO,
        IClientDAO clientDAO,
        IProfilDAO profilDAO,
        IAchatDAO achatDAO,
        IStockDAO stockDAO,
        IProduitDAO produitDAO) {
        super();
        setLigneFactureDAO(ligneFactureDAO);
        setClientDAO(clientDAO);
        setProfilDAO(profilDAO);
        setAchatDAO(achatDAO);
        setStockDAO(stockDAO);
        setProduitDAO(produitDAO);
    }

    public ILigneFactureDAO getLigneFactureDAO() {
        return this.ligneFactureDAO;
    }

    public void setLigneFactureDAO(ILigneFactureDAO ligneFactureDAO) {
        this.ligneFactureDAO = ligneFactureDAO;
    }

    public IClientDAO getClientDAO() {
        return this.clientDAO;
    }

    public void setClientDAO(IClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public IProfilDAO getProfilDAO() {
        return this.profilDAO;
    }

    public void setProfilDAO(IProfilDAO profilDAO) {
        this.profilDAO = profilDAO;
    }

    public IAchatDAO getAchatDAO() {
        return this.achatDAO;
    }

    public void setAchatDAO(IAchatDAO achatDAO) {
        this.achatDAO = achatDAO;
    }

    public IStockDAO getStockDAO() {
        return this.stockDAO;
    }

    public void setStockDAO(IStockDAO stockDAO) {
        this.stockDAO = stockDAO;
    }

    public IProduitDAO getProduitDAO() {
        return this.produitDAO;
    }

    public void setProduitDAO(IProduitDAO produitDAO) {
        this.produitDAO = produitDAO;
    }

    @Override
    public LigneFactureBean add(Connexion connexion,
        LigneFactureBean ligneFactureBean) throws ServiceException {
        try {
            return (LigneFactureBean) getLigneFactureDAO().add(connexion,
                ligneFactureBean);
        } catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public LigneFactureBean get(Connexion connexion,
        LigneFactureBean ligneFactureBean) throws ServiceException {
        try {
            LigneFactureBean ligneFacture = (LigneFactureBean) getLigneFactureDAO().get(connexion,
                ligneFactureBean.getIdLigneFacture());

            //Peupler l'achatBean de ligneFacture

            AchatBean achat = (AchatBean) getAchatDAO().get(connexion,
                ligneFacture.getAchatBean().getIdAchat());

            ClientBean client = (ClientBean) getClientDAO().get(connexion,
                achat.getClientBean().getIdClient());

            ProfilBean profil = (ProfilBean) getProfilDAO().get(connexion,
                client.getProfilBean().getIdProfil());

            client.setProfilBean(profil);
            achat.setClientBean(client);
            ligneFacture.setAchatBean(achat);

            //Peupler le produitBean de ligneFacture

            ProduitBean produit = (ProduitBean) getProduitDAO().get(connexion,
                ligneFacture.getProduitBean().getIdProduit());

            StockBean stock = (StockBean) getStockDAO().get(connexion,
                produit.getStockBean().getIdStock());

            produit.setStockBean(stock);
            ligneFacture.setProduitBean(produit);

            return ligneFacture;
        } catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Connexion connexion,
        LigneFactureBean ligneFactureBean) throws ServiceException {
        try {
            getLigneFactureDAO().update(connexion,
                ligneFactureBean);
        } catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Connexion connexion,
        LigneFactureBean ligneFactureBean) throws ServiceException {
        try {
            getLigneFactureDAO().delete(connexion,
                ligneFactureBean);
        } catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<LigneFactureBean> findByAchat(Connexion connexion,
        AchatBean achatBean) throws ServiceException {
        try {
            List<LigneFactureBean> liste = getLigneFactureDAO().findByAchat(connexion,
                achatBean);

            for(int i = 0 ; i < liste.size() ; i++) {
                liste.set(i,
                    get(connexion,
                        liste.get(i)));
            }
            return liste;

            //return getLigneFactureDAO().findByAchat(connexion,
            //    achatBean);
        } catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

}
