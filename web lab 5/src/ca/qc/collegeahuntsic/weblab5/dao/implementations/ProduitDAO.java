package ca.qc.collegeahuntsic.weblab5.dao.implementations;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.qc.collegeahuntsic.weblab5.bean.Bean;
import ca.qc.collegeahuntsic.weblab5.bean.ProduitBean;
import ca.qc.collegeahuntsic.weblab5.bean.StockBean;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IProduitDAO;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab5.exception.dto.InvalidDTOClassException;
import ca.qc.collegeahuntsic.weblab5.exception.dto.InvalidDTOException;

public class ProduitDAO extends DAO implements IProduitDAO {

	private static final String CREATE_PRIMARY_KEY = "SELECT SEQ_ID_PRODUIT.NEXTVAL FROM DUAL";
	private static final String CREATE_REQUEST = "INSERT INTO Produit(IDProduit, Nom, Description, StockID)"
			+ " VALUES (?, ?, ?, ?)";
	private static final String READ_REQUEST = "SELECT IDProduit, Nom, Description, StockID"
			+ " FROM Produit WHERE IDProduit = ?";
	private static final String UPDATE_REQUEST = "UPDATE Produit"
			+ " SET IDProduit=?, Nom=?, Description=?, StockID=?"
			+ " WHERE IDProduit = ?";
	private static final String DELETE_REQUEST = "DELETE FROM Produit"
			+ " WHERE IDProduit = ?";
	private static final String GET_ALL_REQUEST = "SELECT IDProduit, Nom, Description, StockID"
			+ " FROM Produit";

	public ProduitDAO(Class<ProduitBean> beanClass)
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
			ProduitBean produitBean = (ProduitBean) bean;
			try (PreparedStatement createPreparedStatement = connexion
					.getConnection().prepareStatement(ProduitDAO.CREATE_REQUEST)) {
				produitBean.setIdProduit(ProduitDAO.getPrimaryKey(connexion));
				createPreparedStatement.setString(1, produitBean.getIdProduit());
				createPreparedStatement.setString(2, produitBean.getNom());
				createPreparedStatement.setString(3, produitBean.getDescription());
				createPreparedStatement.setString(4, produitBean.getStockBean()
						.getIdStock());
				createPreparedStatement.executeUpdate();
			}
			return produitBean;
		} catch (SQLException 
				| InvalidHibernateSessionException 
				| InvalidDTOException 
				| InvalidDTOClassException exception) {
			throw new DAOException(exception);
		}
	}

	@Override
	public ProduitBean get(Connexion connexion, Serializable primaryKey)
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
			String idProduit = (String) primaryKey;
			ProduitBean produitBean = null;
			try (PreparedStatement readPreparedStatement = connexion
					.getConnection().prepareStatement(ProduitDAO.READ_REQUEST)) {
				readPreparedStatement.setString(1, idProduit);
				try (ResultSet resultSet = readPreparedStatement.executeQuery()) {
					if (resultSet.next()) {
						produitBean = new ProduitBean();
						produitBean.setIdProduit(resultSet.getString(1));
						produitBean.setNom(resultSet.getString(2));
						produitBean.setDescription(resultSet.getString(3));
						StockBean stockBean = new StockBean();
						stockBean.setIdStock(resultSet.getString(4));
						produitBean.setStockBean(stockBean);
					}
				}
			}
			return produitBean;
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
			ProduitBean produitBean = (ProduitBean) bean;
			try (PreparedStatement updatePreparedStatement = connexion
					.getConnection().prepareStatement(ProduitDAO.UPDATE_REQUEST)) {
				updatePreparedStatement.setString(1, produitBean.getIdProduit());
				updatePreparedStatement.setString(2, produitBean.getNom());
				updatePreparedStatement.setString(3, produitBean.getDescription());
				updatePreparedStatement.setString(4, produitBean.getStockBean()
						.getIdStock());
				updatePreparedStatement.setString(5, produitBean.getIdProduit());
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
			ProduitBean produitBean = (ProduitBean) bean;
			try (PreparedStatement deletePreparedStatement = connexion
					.getConnection().prepareStatement(ProduitDAO.DELETE_REQUEST)) {
				deletePreparedStatement.setString(1, produitBean.getIdProduit());
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
	public List<ProduitBean> getAll(Connexion connexion, String sortByPropertyName)
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
			List<ProduitBean> produits = Collections.emptyList();
			try (PreparedStatement getAllPreparedStatement = connexion
					.getConnection().prepareStatement(ProduitDAO.GET_ALL_REQUEST)) {
				try (ResultSet resultSet = getAllPreparedStatement.executeQuery()) {
					ProduitBean produitBean = null;
					if (resultSet.next()) {
						produits = new ArrayList<>();
						do {
							produitBean = new ProduitBean();
							produitBean.setIdProduit(resultSet.getString(1));
							produitBean.setNom(resultSet.getString(2));
							produitBean.setDescription(resultSet.getString(3));
							StockBean stockBean = new StockBean();
							stockBean.setIdStock(resultSet.getString(4));
							produitBean.setStockBean(stockBean);
							produits.add(produitBean);
						} while (resultSet.next());
					}
				}
			}
			return produits;
		} catch (SQLException 
				| InvalidHibernateSessionException 
				| InvalidSortByPropertyException exception) {
			throw new DAOException(exception);
		}
		
	}

	private static String getPrimaryKey(Connexion connexion)
			throws DAOException {
		return DAO.getPrimaryKey(connexion, ProduitDAO.CREATE_PRIMARY_KEY);
	}

}
