
package ca.qc.collegeahuntsic.weblab5.dao.implementations;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ca.qc.collegeahuntsic.weblab5.bean.AchatBean;
import ca.qc.collegeahuntsic.weblab5.bean.Bean;
import ca.qc.collegeahuntsic.weblab5.bean.LigneFactureBean;
import ca.qc.collegeahuntsic.weblab5.bean.ProduitBean;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.ILigneFactureDAO;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab5.exception.dto.InvalidDTOClassException;
import ca.qc.collegeahuntsic.weblab5.exception.dto.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab5.exception.service.InvalidDAOException;

public class LigneFactureDAO extends DAO implements ILigneFactureDAO {

    private static final String CREATE_PRIMARY_KEY = "SELECT SEQ_ID_LIGNEFACTURE.NEXTVAL FROM DUAL";

    private static final String CREATE_REQUEST = "INSERT INTO LigneFacture(IDLigneFacture, AchatID, ProduitID, Quantite, Prix)"
        + " VALUES (?, ?, ?, ?, ?)";

    private static final String READ_REQUEST = "SELECT IDLigneFacture, AchatID, ProduitID, Quantite, Prix"
        + " FROM LigneFacture WHERE IDLigneFacture = ?";

    private static final String UPDATE_REQUEST = "UPDATE LigneFacture"
        + " SET IDLigneFacture = ?, AchatID = ?, ProduitID = ?, Quantite = ?, Prix = ?"
        + " WHERE IDLigneFacture = ?";

    private static final String DELETE_REQUEST = "DELETE FROM LigneFacture"
        + " WHERE IDLigneFacture = ?";

    private static final String GET_ALL_REQUEST = "SELECT IDLigneFacture, AchatID, ProduitID, Quantite, Prix"
        + " FROM LigneFacture";

    private static final String FIND_BY_ACHAT_REQUEST = "SELECT IDLigneFacture, AchatID, ProduitID, Quantite, Prix"
        + " FROM LigneFacture WHERE AchatID = ?";

    public LigneFactureDAO(Class<LigneFactureBean> beanClass) throws InvalidDTOClassException {
        super(beanClass);
    }

    @Override
    public Bean add(Connexion connexion,
        Bean bean) throws DAOException {
        try {
            if(connexion == null) {
                throw new InvalidHibernateSessionException("La connexion ne peut être null");
            }
            if(bean == null) {
                throw new InvalidDTOException("Le DTO ne peut être null");
            }
            if(!bean.getClass().equals(getBeanClass())) {
                throw new InvalidDTOClassException("Le DTO doit être un "
                    + getBeanClass().getName());
            }
            LigneFactureBean ligneFactureBean = (LigneFactureBean) bean;
            try(
                PreparedStatement createPreparedStatement = connexion.getConnection().prepareStatement(LigneFactureDAO.CREATE_REQUEST)) {
                ligneFactureBean.setIdLigneFacture(LigneFactureDAO.getPrimaryKey(connexion));
                createPreparedStatement.setString(1,
                    ligneFactureBean.getIdLigneFacture());
                createPreparedStatement.setString(2,
                    ligneFactureBean.getAchatBean().getIdAchat());
                createPreparedStatement.setString(3,
                    ligneFactureBean.getProduitBean().getIdProduit());
                createPreparedStatement.setInt(4,
                    ligneFactureBean.getQuantite());
                createPreparedStatement.setBigDecimal(5,
                    ligneFactureBean.getPrix());
                createPreparedStatement.executeUpdate();
            }
            return ligneFactureBean;
        } catch(
            SQLException
            | InvalidHibernateSessionException
            | InvalidDTOException
            | InvalidDTOClassException exception) {
            throw new DAOException(exception);
        }
    }

    @Override
    public LigneFactureBean get(Connexion connexion,
        Serializable primaryKey) throws DAOException {
        try {
            if(connexion == null) {
                throw new InvalidHibernateSessionException("La connexion ne peut être null");
            }
            if(primaryKey == null) {
                throw new InvalidPrimaryKeyException("La clef primaire ne peut être null");
            }
            String idAchat = (String) primaryKey;
            LigneFactureBean ligneFactureBean = null;
            try(
                PreparedStatement readPreparedStatement = connexion.getConnection().prepareStatement(LigneFactureDAO.READ_REQUEST)) {
                readPreparedStatement.setString(1,
                    idAchat);
                try(
                    ResultSet resultSet = readPreparedStatement.executeQuery()) {
                    if(resultSet.next()) {
                        ligneFactureBean = new LigneFactureBean();
                        ligneFactureBean.setIdLigneFacture(resultSet.getString(1));
                        AchatBean achatBean = new AchatBean();
                        achatBean.setIdAchat(resultSet.getString(2));
                        ligneFactureBean.setAchatBean(achatBean);
                        ProduitBean produitBean = new ProduitBean();
                        produitBean.setIdProduit(resultSet.getString(3));
                        ligneFactureBean.setProduitBean(produitBean);
                        ligneFactureBean.setQuantite(resultSet.getInt(4));
                        ligneFactureBean.setPrix(resultSet.getBigDecimal(5));
                    }
                }
            }
            return ligneFactureBean;
        } catch(
            SQLException
            | InvalidHibernateSessionException
            | InvalidPrimaryKeyException exception) {
            throw new DAOException(exception);
        }
    }

    @Override
    public void update(Connexion connexion,
        Bean bean) throws DAOException {
        try {
            if(connexion == null) {
                throw new InvalidHibernateSessionException("La connexion ne peut être null");
            }
            if(bean == null) {
                throw new InvalidDTOException("Le DTO ne peut être null");
            }
            if(!bean.getClass().equals(getBeanClass())) {
                throw new InvalidDTOClassException("Le DTO doit être un "
                    + getBeanClass().getName());
            }
            LigneFactureBean ligneFactureBean = (LigneFactureBean) bean;
            try(
                PreparedStatement updatePreparedStatement = connexion.getConnection().prepareStatement(LigneFactureDAO.UPDATE_REQUEST)) {
                updatePreparedStatement.setString(1,
                    ligneFactureBean.getIdLigneFacture());
                updatePreparedStatement.setString(2,
                    ligneFactureBean.getAchatBean().getIdAchat());
                updatePreparedStatement.setString(3,
                    ligneFactureBean.getProduitBean().getIdProduit());
                updatePreparedStatement.setInt(4,
                    ligneFactureBean.getQuantite());
                updatePreparedStatement.setBigDecimal(5,
                    ligneFactureBean.getPrix());
                updatePreparedStatement.setString(6,
                    ligneFactureBean.getIdLigneFacture());
                updatePreparedStatement.executeUpdate();
            }
        } catch(
            SQLException
            | InvalidHibernateSessionException
            | InvalidDTOClassException
            | InvalidDTOException exception) {
            throw new DAOException(exception);
        }
    }

    @Override
    public void delete(Connexion connexion,
        Bean bean) throws DAOException {
        try {
            if(connexion == null) {
                throw new InvalidHibernateSessionException("La connexion ne peut être null");
            }
            if(bean == null) {
                throw new InvalidDTOException("Le DTO ne peut être null");
            }
            if(!bean.getClass().equals(getBeanClass())) {
                throw new InvalidDTOClassException("Le DTO doit être un "
                    + getBeanClass().getName());
            }
            LigneFactureBean ligneFactureBean = (LigneFactureBean) bean;
            try(
                PreparedStatement deletePreparedStatement = connexion.getConnection().prepareStatement(LigneFactureDAO.DELETE_REQUEST)) {
                deletePreparedStatement.setString(1,
                    ligneFactureBean.getIdLigneFacture());
                deletePreparedStatement.executeUpdate();
            }
        } catch(
            SQLException
            | InvalidHibernateSessionException
            | InvalidDTOException
            | InvalidDTOClassException exception) {
            throw new DAOException(exception);
        }
    }

    @Override
    public List<LigneFactureBean> getAll(Connexion connexion,
        String sortByPropertyName) throws DAOException {
        try {
            if(connexion == null) {
                throw new InvalidHibernateSessionException("La connexion ne peut être null");
            }
            if(sortByPropertyName == null) {
                throw new InvalidSortByPropertyException("La propriété utilisée pour classer ne peut être null");
            }
            List<LigneFactureBean> factures = Collections.emptyList();
            try(
                PreparedStatement getAllPreparedStatement = connexion.getConnection().prepareStatement(LigneFactureDAO.GET_ALL_REQUEST)) {
                try(
                    ResultSet resultSet = getAllPreparedStatement.executeQuery()) {
                    LigneFactureBean ligneFactureBean = null;
                    if(resultSet.next()) {
                        factures = new ArrayList<>();
                        do {
                            ligneFactureBean = new LigneFactureBean();
                            ligneFactureBean.setIdLigneFacture(resultSet.getString(1));
                            AchatBean achatBean = new AchatBean();
                            achatBean.setIdAchat(resultSet.getString(2));
                            ligneFactureBean.setAchatBean(achatBean);
                            ProduitBean produitBean = new ProduitBean();
                            produitBean.setIdProduit(resultSet.getString(3));
                            ligneFactureBean.setProduitBean(produitBean);
                            ligneFactureBean.setQuantite(resultSet.getInt(4));
                            ligneFactureBean.setPrix(resultSet.getBigDecimal(5));
                            factures.add(ligneFactureBean);
                        } while(resultSet.next());
                    }
                }
            }
            return factures;
        } catch(
            SQLException
            | InvalidHibernateSessionException
            | InvalidSortByPropertyException exception) {
            throw new DAOException(exception);
        }
    }

    private static String getPrimaryKey(Connexion connexion) throws DAOException {
        return DAO.getPrimaryKey(connexion,
            LigneFactureDAO.CREATE_PRIMARY_KEY);
    }

    @Override
    public List<LigneFactureBean> findByAchat(Connexion connexion,
        AchatBean achatBean) throws DAOException {
        try {
            if(connexion == null) {
                throw new InvalidHibernateSessionException("La connexion ne peut être null");
            }
            if(achatBean == null) {
                throw new InvalidDAOException("L'achat ne peut être null");
            }
            List<LigneFactureBean> facture = Collections.emptyList();
            try(
                PreparedStatement findByAchatPreparedStatement = connexion.getConnection().prepareStatement(LigneFactureDAO.FIND_BY_ACHAT_REQUEST)) {
                findByAchatPreparedStatement.setString(1,
                    achatBean.getIdAchat());
                try(
                    ResultSet resultSet = findByAchatPreparedStatement.executeQuery()) {
                    LigneFactureBean ligneFactureBean = null;
                    if(resultSet.next()) {
                        facture = new ArrayList<>();
                        do {
                            ligneFactureBean = new LigneFactureBean();
                            ligneFactureBean.setIdLigneFacture(resultSet.getString(1));
                            achatBean.setIdAchat(resultSet.getString(2));
                            ligneFactureBean.setAchatBean(achatBean);
                            ProduitBean produitBean = new ProduitBean();
                            produitBean.setIdProduit(resultSet.getString(3));
                            ligneFactureBean.setProduitBean(produitBean);
                            ligneFactureBean.setQuantite(resultSet.getInt(4));
                            ligneFactureBean.setPrix(resultSet.getBigDecimal(5));
                            facture.add(ligneFactureBean);
                        } while(resultSet.next());
                    }
                }
                return facture;
            }
        } catch(
            SQLException
            | InvalidHibernateSessionException
            | InvalidDAOException sqlException) {
            throw new DAOException(sqlException);
        }
    }
}
