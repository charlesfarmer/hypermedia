
package ca.qc.collegeahuntsic.weblab5.service.implementations;

import java.sql.Timestamp;
import java.util.List;
import ca.qc.collegeahuntsic.weblab5.bean.AchatBean;
import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.bean.LigneFactureBean;
import ca.qc.collegeahuntsic.weblab5.bean.LignePanierBean;
import ca.qc.collegeahuntsic.weblab5.bean.ProfilBean;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IAchatDAO;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IClientDAO;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.ILigneFactureDAO;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.ILignePanierDAO;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IProfilDAO;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IStockDAO;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab5.exception.service.NotEnoughStockQuantityException;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.IAchatService;

public class AchatService extends Service implements IAchatService {

    private IAchatDAO achatDAO;

    private ILignePanierDAO lignePanierDAO;

    private IStockDAO stockDAO;

    private ILigneFactureDAO ligneFactureDAO;

    private IClientDAO clientDAO;

    private IProfilDAO profilDAO;

    public AchatService(IAchatDAO achatDAO,
        ILignePanierDAO lignePanierDAO,
        IStockDAO stockDAO,
        ILigneFactureDAO ligneFactureDAO,
        IClientDAO clientDAO,
        IProfilDAO profilDAO) {
        super();
        setAchatDAO(achatDAO);
        setLignePanierDAO(lignePanierDAO);
        setStockDAO(stockDAO);
        setLigneFactureDAO(ligneFactureDAO);
        setClientDAO(clientDAO);
        setProfilDAO(profilDAO);
    }

    @Override
    public AchatBean add(Connexion connexion,
        AchatBean achatBean) throws ServiceException {
        try {
            return (AchatBean) getAchatDAO().add(connexion,
                achatBean);
        } catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public AchatBean get(Connexion connexion,
        AchatBean achatBean) throws ServiceException {
        try {
            AchatBean achat = (AchatBean) getAchatDAO().get(connexion,
                achatBean.getIdAchat());

            ClientBean client = (ClientBean) getClientDAO().get(connexion,
                achat.getClientBean().getIdClient());

            ProfilBean profil = (ProfilBean) getProfilDAO().get(connexion,
                achat.getClientBean().getProfilBean().getIdProfil());

            client.setProfilBean(profil);
            achat.setClientBean(client);

            return achat;
        } catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Connexion connexion,
        AchatBean achatBean) throws ServiceException {
        try {
            getAchatDAO().update(connexion,
                achatBean);
        } catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Connexion connexion,
        AchatBean achatBean) throws ServiceException {
        try {
            getAchatDAO().delete(connexion,
                achatBean);
        } catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    public IAchatDAO getAchatDAO() {
        return this.achatDAO;
    }

    public void setAchatDAO(IAchatDAO achatDAO) {
        this.achatDAO = achatDAO;
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

    @Override
    public void acheter(Connexion connexion,
        ClientBean clientBean) throws ServiceException,
        NotEnoughStockQuantityException {
        try {
            //Partie 1 Update stocks
            List<LignePanierBean> panier = getLignePanierDAO().findByClient(connexion,
                clientBean);
            for(int i = 0 ; i < panier.size() ; i++) {
                if(panier.get(i).getProduitBean().getStockBean().getQuantite() < panier.get(i).getQuantite()) {
                    throw new NotEnoughStockQuantityException();
                }
                panier.get(i).getProduitBean().getStockBean().setQuantite(panier.get(i).getProduitBean().getStockBean().getQuantite()
                    - panier.get(i).getQuantite());
                getStockDAO().update(connexion,
                    panier.get(i).getProduitBean().getStockBean());
            }

            //Partie 2 Créer l'achat

            AchatBean achat = new AchatBean();
            achat.setClientBean(clientBean);
            achat.setDateAchat(new Timestamp(System.currentTimeMillis()));

            add(connexion,
                achat);

            //Partie 3 Créer les ligneFacture

            for(int i = 0 ; i < panier.size() ; i++) {
                LigneFactureBean lf = new LigneFactureBean();
                lf.setAchatBean(achat);
                lf.setPrix(panier.get(i).getProduitBean().getStockBean().getPrix());
                lf.setProduitBean(panier.get(i).getProduitBean());
                lf.setQuantite(panier.get(i).getQuantite());

                getLigneFactureDAO().add(connexion,
                    lf);
            }

            //Partie 4 Vider le panier

            getLignePanierDAO().deleteByClient(connexion,
                clientBean);

        } catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

}
