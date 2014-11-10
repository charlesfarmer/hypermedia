
package ca.qc.collegeahuntsic.weblab5.service.implementations;

import java.util.List;
import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.bean.LignePanierBean;
import ca.qc.collegeahuntsic.weblab5.bean.ProduitBean;
import ca.qc.collegeahuntsic.weblab5.bean.ProfilBean;
import ca.qc.collegeahuntsic.weblab5.bean.StockBean;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IClientDAO;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.ILignePanierDAO;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IProduitDAO;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IProfilDAO;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IStockDAO;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab5.exception.service.NotEnoughStockQuantityException;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.ILignePanierService;

public class LignePanierService extends Service implements ILignePanierService {

    private ILignePanierDAO lignePanierDAO;

    private IStockDAO stockDAO;

    private IClientDAO clientDAO;

    private IProfilDAO profilDAO;

    private IProduitDAO produitDAO;

    public LignePanierService(ILignePanierDAO lignePanierDAO,
        IStockDAO stockDAO,
        IClientDAO clientDAO,
        IProfilDAO profilDAO,
        IProduitDAO produitDAO) {
        super();
        setLignePanierDAO(lignePanierDAO);
        setStockDAO(stockDAO);
        setClientDAO(clientDAO);
        setProfilDAO(profilDAO);
        setProduitDAO(produitDAO);
    }

    public ILignePanierDAO getLignePanierDAO() {
        return this.lignePanierDAO;
    }

    public void setLignePanierDAO(ILignePanierDAO lignePanierDAO) {
        this.lignePanierDAO = lignePanierDAO;
    }

    public IStockDAO getStockDAO() {
        return this.stockDAO;
    }

    public void setStockDAO(IStockDAO stockDAO) {
        this.stockDAO = stockDAO;
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

    public IProduitDAO getProduitDAO() {
        return this.produitDAO;
    }

    public void setProduitDAO(IProduitDAO produitDAO) {
        this.produitDAO = produitDAO;
    }

    @Override
    public LignePanierBean add(Connexion connexion,
        LignePanierBean lignePanierBean) throws ServiceException {
        try {
            return (LignePanierBean) getLignePanierDAO().add(connexion,
                lignePanierBean);
        } catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public LignePanierBean get(Connexion connexion,
        LignePanierBean lignePanierBean) throws ServiceException {
        try {
            LignePanierBean lignePanier = (LignePanierBean) getLignePanierDAO().get(connexion,
                lignePanierBean.getIdLignePanier());

            //Peupler l'achatBean de lignePanier

            ClientBean client = (ClientBean) getClientDAO().get(connexion,
                lignePanier.getClientBean().getIdClient());

            ProfilBean profil = (ProfilBean) getProfilDAO().get(connexion,
                client.getProfilBean().getIdProfil());

            client.setProfilBean(profil);
            lignePanier.setClientBean(client);

            //Peupler le produitBean de lignePanier

            ProduitBean produit = (ProduitBean) getProduitDAO().get(connexion,
                lignePanier.getProduitBean().getIdProduit());

            StockBean stock = (StockBean) getStockDAO().get(connexion,
                produit.getStockBean().getIdStock());

            produit.setStockBean(stock);
            lignePanier.setProduitBean(produit);

            return lignePanier;
        } catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Connexion connexion,
        LignePanierBean lignePanierBean) throws ServiceException {
        try {
            getLignePanierDAO().update(connexion,
                lignePanierBean);
        } catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Connexion connexion,
        LignePanierBean lignePanierBean) throws ServiceException {
        try {
            getLignePanierDAO().delete(connexion,
                lignePanierBean);
        } catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<LignePanierBean> findByClient(Connexion connexion,
        ClientBean clientBean) throws ServiceException {
        try {
            List<LignePanierBean> panier = getLignePanierDAO().findByClient(connexion,
                clientBean);

            for(int i = 0 ; i < panier.size() ; i++) {
                panier.set(i,
                    get(connexion,
                        panier.get(i)));
            }
            return panier;
            //return getLignePanierDAO().findByClient(connexion,
            //    clientBean);
        } catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteByClient(Connexion connexion,
        ClientBean clientBean) throws ServiceException {
        try {
            getLignePanierDAO().deleteByClient(connexion,
                clientBean);
        } catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public LignePanierBean ajouterAuPanier(Connexion connexion,
        LignePanierBean lignePanierBean) throws ServiceException,
        NotEnoughStockQuantityException {
        try {

            List<LignePanierBean> panier = getLignePanierDAO().findByClient(connexion,
                lignePanierBean.getClientBean());
            for(int i = 0 ; i < panier.size() ; i++) {
                // Si...une ligne pour ce produit existe d�j�, on update la quantit� au lieu d'ajouter 
                if(panier.get(i).getProduitBean().getIdProduit().equals(lignePanierBean.getProduitBean().getIdProduit())) {
                    lignePanierBean.setQuantite(lignePanierBean.getQuantite()
                        + panier.get(i).getQuantite());

                    if(lignePanierBean.getProduitBean().getStockBean().getQuantite() < lignePanierBean.getQuantite()) {
                        throw new NotEnoughStockQuantityException();
                    }

                    update(connexion,
                        lignePanierBean);
                    return lignePanierBean;
                }
            }

            return add(connexion,
                lignePanierBean);
        } catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void modifierNombreDitems(Connexion connexion,
        LignePanierBean lignePanierBean) throws ServiceException,
        NotEnoughStockQuantityException {

        try {

            if(lignePanierBean.getProduitBean().getStockBean().getQuantite() < lignePanierBean.getQuantite()) {
                throw new NotEnoughStockQuantityException();
            }

            getLignePanierDAO().update(connexion,
                lignePanierBean);
        } catch(DAOException e) {
            throw new ServiceException(e);
        }
    }
}
