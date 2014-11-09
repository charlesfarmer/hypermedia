package ca.qc.collegeahuntsic.weblab5.dao.implementations;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.qc.collegeahuntsic.weblab5.bean.Bean;
import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.bean.LignePanierBean;
import ca.qc.collegeahuntsic.weblab5.bean.ProduitBean;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.ILignePanierDAO;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidCriterionException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab5.exception.dto.InvalidDTOClassException;
import ca.qc.collegeahuntsic.weblab5.exception.dto.InvalidDTOException;

public class LignePanierDAO extends DAO implements ILignePanierDAO {

	private static final String CREATE_PRIMARY_KEY = "SELECT SEQ_ID_LIGNEPANIER.NEXTVAL FROM DUAL";
	private static final String CREATE_REQUEST = "INSERT INTO LignePanier(IDLignePanier, ClientID, ProduitID, Quantite)"
			+ " VALUES (?,?,?,?)";
	private static final String READ_REQUEST = "SELECT IDLignePanier, ClientID, ProduitID, Quantite"
			+ " FROM LignePanier WHERE IDLignePanier= ?";
	private static final String UPDATE_REQUEST = "UPDATE LignePanier"
			+ " SET IDLignePanier=?, ClientID=?, ProduitID=?, Quantite=?"
			+ " WHERE IDLignePanier = ?";
	private static final String DELETE_REQUEST = "DELETE FROM LignePanier"
			+ " WHERE IDLignePanier = ?";
	private static final String GET_ALL_REQUEST = "SELECT IDLignePanier, ClientID, ProduitID, Quantite"
			+ " FROM LignePanier";
	private static final String DELETE_BY_CLIENT_REQUEST = "DELETE FROM LignePanier"
			+ "	WHERE ClientID = ?";
	private static final String FIND_BY_CLIENT_REQUEST = "SELECT IDLignePanier, ClientID, ProduitID, Quantite"
			+ " FROM LignePanier"
			+ " WHERE ClientID = ?";

	public LignePanierDAO(Class<LignePanierBean> beanClass)
			throws InvalidDTOClassException {
		super(beanClass);
	}

	@Override
	public Bean add(Connexion connexion, Bean bean)
			throws DAOException {
		try {
			if (connexion == null) {
				throw new InvalidHibernateSessionException(
						"La connexion ne peut être null");
			}
			if (bean == null) {
				throw new InvalidDTOException("Le DTO ne peut être null");
			}
			if (!bean.getClass().equals(getBeanClass())) {
				throw new InvalidDTOClassException("Le DTO doit être un "
						+ getBeanClass().getName());
			}
			LignePanierBean lignePanierBean = (LignePanierBean) bean;
			try (PreparedStatement createPreparedStatement = connexion
					.getConnection()
					.prepareStatement(LignePanierDAO.CREATE_REQUEST)) {
				lignePanierBean.setIdLignePanier(LignePanierDAO
						.getPrimaryKey(connexion));
				createPreparedStatement.setString(1,
						lignePanierBean.getIdLignePanier());
				createPreparedStatement.setString(2, lignePanierBean
						.getClientBean().getIdClient());
				createPreparedStatement.setString(3, lignePanierBean
						.getProduitBean().getIdProduit());
				createPreparedStatement.setInt(4, lignePanierBean.getQuantite());
				createPreparedStatement.executeUpdate();
			}
			return lignePanierBean;
		} catch (SQLException 
				| InvalidHibernateSessionException 
				| InvalidDTOException 
				| InvalidDTOClassException exception) {
			throw new DAOException(exception);
		}
	}

	@Override
	public LignePanierBean get(Connexion connexion, Serializable primaryKey)
			throws DAOException {
		try {
			if (connexion == null) {
				throw new InvalidHibernateSessionException(
						"La connexion ne peut être null");
			}
			if (primaryKey == null) {
				throw new InvalidPrimaryKeyException(
						"La clef primaire ne peut être null");
			}
			String idLignePanier = (String) primaryKey;
			LignePanierBean lignePanierBean = null;
			try (PreparedStatement readPreparedStatement = connexion
					.getConnection().prepareStatement(LignePanierDAO.READ_REQUEST)) {
				readPreparedStatement.setString(1, idLignePanier);
				try (ResultSet resultSet = readPreparedStatement.executeQuery()) {
					if (resultSet.next()) {
						lignePanierBean = new LignePanierBean();
						lignePanierBean.setIdLignePanier(resultSet.getString(1));
						ClientBean clientBean = new ClientBean();
						clientBean.setIdClient(resultSet.getString(2));
						lignePanierBean.setClientBean(clientBean);
						ProduitBean produitBean = new ProduitBean();
						produitBean.setIdProduit(resultSet.getString(3));
						lignePanierBean.setProduitBean(produitBean);
						lignePanierBean.setQuantite(resultSet.getInt(4));
					}
				}
			}
			return lignePanierBean;
		} catch (SQLException 
				| InvalidHibernateSessionException 
				| InvalidPrimaryKeyException exception) {
			throw new DAOException(exception);
		}
	}

	@Override
	public void update(Connexion connexion, Bean bean)
			throws DAOException {
		try {
			if (connexion == null) {
				throw new InvalidHibernateSessionException(
						"La connexion ne peut être null");
			}
			if (bean == null) {
				throw new InvalidDTOException("Le DTO ne peut être null");
			}
			if (!bean.getClass().equals(getBeanClass())) {
				throw new InvalidDTOClassException("Le DTO doit être un "
						+ getBeanClass().getName());
			}
			LignePanierBean lignePanierBean = (LignePanierBean) bean;
			try (PreparedStatement updatePreparedStatement = connexion
					.getConnection()
					.prepareStatement(LignePanierDAO.UPDATE_REQUEST)) {
				updatePreparedStatement.setString(1,
						lignePanierBean.getIdLignePanier());
				updatePreparedStatement.setString(2, lignePanierBean
						.getClientBean().getIdClient());
				updatePreparedStatement.setString(3, lignePanierBean
						.getProduitBean().getIdProduit());
				updatePreparedStatement.setInt(4, lignePanierBean.getQuantite());
				updatePreparedStatement.setString(5,
						lignePanierBean.getIdLignePanier());
				updatePreparedStatement.executeUpdate();
			}
		} catch (SQLException 
				| InvalidHibernateSessionException 
				| InvalidDTOClassException 
				| InvalidDTOException exception) {
			throw new DAOException(exception);
		}
	}

	@Override
	public void delete(Connexion connexion, Bean bean)
			throws DAOException {
		try {
			if (connexion == null) {
				throw new InvalidHibernateSessionException(
						"La connexion ne peut être null");
			}
			if (bean == null) {
				throw new InvalidDTOException("Le DTO ne peut être null");
			}
			if (!bean.getClass().equals(getBeanClass())) {
				throw new InvalidDTOClassException("Le DTO doit être un "
						+ getBeanClass().getName());
			}
			LignePanierBean lignePanierBean = (LignePanierBean) bean;
			try (PreparedStatement deletePreparedStatement = connexion
					.getConnection()
					.prepareStatement(LignePanierDAO.DELETE_REQUEST)) {
				deletePreparedStatement.setString(1,
						lignePanierBean.getIdLignePanier());
				deletePreparedStatement.executeUpdate();
			}
		} catch (SQLException 
				| InvalidHibernateSessionException 
				| InvalidDTOException 
				| InvalidDTOClassException exception) {
			throw new DAOException(exception);
		}
	}

	@Override
	public List<LignePanierBean> getAll(Connexion connexion, String sortByPropertyName)
			throws DAOException {
		try {
			if (connexion == null) {
				throw new InvalidHibernateSessionException(
						"La connexion ne peut être null");
			}
			if (sortByPropertyName == null) {
				throw new InvalidSortByPropertyException(
						"La propriété utilisée pour classer ne peut être null");
			}
			List<LignePanierBean> paniers = Collections.emptyList();
			try (PreparedStatement getAllPreparedStatement = connexion
					.getConnection().prepareStatement(
							LignePanierDAO.GET_ALL_REQUEST)) {
				try (ResultSet resultSet = getAllPreparedStatement.executeQuery()) {
					LignePanierBean lignePanierBean = null;
					if (resultSet.next()) {
						paniers = new ArrayList<>();
						do {
							lignePanierBean = new LignePanierBean();
							lignePanierBean
									.setIdLignePanier(resultSet.getString(1));
							ClientBean clientBean = new ClientBean();
							clientBean.setIdClient(resultSet.getString(2));
							lignePanierBean.setClientBean(clientBean);
							ProduitBean produitBean = new ProduitBean();
							produitBean.setIdProduit(resultSet.getString(3));
							lignePanierBean.setProduitBean(produitBean);
							lignePanierBean.setQuantite(resultSet.getInt(4));
							paniers.add(lignePanierBean);
						} while (resultSet.next());
					}
				}
			}
			return paniers;
		} catch (SQLException 
				| InvalidHibernateSessionException 
				| InvalidSortByPropertyException exception) {
			throw new DAOException(exception);
		}
	}

	private static String getPrimaryKey(Connexion connexion)
			throws DAOException {
		return DAO.getPrimaryKey(connexion, LignePanierDAO.CREATE_PRIMARY_KEY);
	}

	@Override
	public List<LignePanierBean> findByClient(Connexion connexion,
			ClientBean clientBean) throws DAOException {
		try {
			if (connexion == null) {
				throw new InvalidHibernateSessionException(
						"La connexion ne peut être null");
			}
			if (clientBean == null) {
				throw new InvalidCriterionException(
						"Le client ne peut être null");
			}
			List<LignePanierBean> panier = Collections.emptyList();
			try (PreparedStatement findByClientPreparedStatement = connexion
					.getConnection().prepareStatement(
							LignePanierDAO.FIND_BY_CLIENT_REQUEST)) {
				findByClientPreparedStatement.setString(1, clientBean.getIdClient());
				try (ResultSet resultSet = findByClientPreparedStatement.executeQuery()) {
					LignePanierBean lignePanierBean = null;
					if (resultSet.next()) {
						panier = new ArrayList<>();
						do {
							lignePanierBean = new LignePanierBean();
							lignePanierBean.setIdLignePanier(resultSet.getString(1));
							clientBean.setIdClient(resultSet.getString(2));
							lignePanierBean.setClientBean(clientBean);
							ProduitBean produitBean = new ProduitBean();
							produitBean.setIdProduit(resultSet.getString(3));
							lignePanierBean.setProduitBean(produitBean);
							lignePanierBean.setQuantite(resultSet.getInt(4));
							panier.add(lignePanierBean);
						} while (resultSet.next());
					}
				}
			}
			return panier;
		} catch (SQLException 
				| InvalidHibernateSessionException 
				| InvalidCriterionException exception) {
			throw new DAOException(exception);
		}
	}

	@Override
	public void deleteByClient(Connexion connexion, ClientBean clientBean) throws DAOException {
		try {
			if (connexion == null) {
				throw new InvalidHibernateSessionException(
						"La connexion ne peut être null");
			}
			if (clientBean == null) {
				throw new InvalidDTOException("Le DTO ne peut être null");
			}
			try (PreparedStatement deleteByClientPreparedStatement = connexion
					.getConnection()
					.prepareStatement(LignePanierDAO.DELETE_BY_CLIENT_REQUEST)) {
				deleteByClientPreparedStatement.setString(1,
						clientBean.getIdClient());
				deleteByClientPreparedStatement.executeUpdate();
			}
		} catch (SQLException 
				| InvalidHibernateSessionException 
				| InvalidDTOException exception) {
			throw new DAOException(exception);
		}
	}
}
