package ca.qc.collegeahuntsic.weblab5.dao.implementations;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.qc.collegeahuntsic.weblab5.bean.Bean;
import ca.qc.collegeahuntsic.weblab5.bean.StockBean;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IStockDAO;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab5.exception.dto.InvalidDTOClassException;
import ca.qc.collegeahuntsic.weblab5.exception.dto.InvalidDTOException;

public class StockDAO extends DAO implements IStockDAO {

	private static final String CREATE_PRIMARY_KEY = "SELECT SEQ_ID_STOCK.NEXTVAL FROM DUAL";
	private static final String CREATE_REQUEST = "INSERT INTO Stock(IDStock, Quantite, Prix, Rabais)"
			+ " VALUES (?,?,?,?)";
	private static final String READ_REQUEST = "SELECT IDStock, Quantite, Prix, Rabais"
			+ " FROM Stock WHERE IDStock = ?";
	private static final String UPDATE_REQUEST = "UPDATE Stock"
			+ " SET IDStock = ?, Quantite = ?, Prix = ?, Rabais = ?"
			+ " WHERE IDStock = ?";
	private static final String DELETE_REQUEST = "DELETE FROM Stock"
			+ " WHERE IDStock = ?";
	private static final String GET_ALL_REQUEST = "SELECT IDStock, Quantite, Prix, Rabais"
			+ " FROM Stock";

	public StockDAO(Class<StockBean> beanClass)
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
			StockBean stockBean = (StockBean) bean;
			try (PreparedStatement createPreparedStatement = connexion
					.getConnection().prepareStatement(StockDAO.CREATE_REQUEST)) {
				stockBean.setIdStock(StockDAO.getPrimaryKey(connexion));
				createPreparedStatement.setString(1, stockBean.getIdStock());
				createPreparedStatement.setInt(2, stockBean.getQuantite());
				createPreparedStatement.setBigDecimal(3, stockBean.getPrix());
				createPreparedStatement.setBigDecimal(4, stockBean.getRabais());
				createPreparedStatement.executeUpdate();
			}
			return stockBean;
		} catch (SQLException 
				| InvalidHibernateSessionException 
				| InvalidDTOException 
				| InvalidDTOClassException exception) {
			throw new DAOException(exception);
		}
	}

	@Override
	public StockBean get(Connexion connexion, Serializable primaryKey)
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
			String idClient = (String) primaryKey;
			StockBean stockBean = null;
			try (PreparedStatement readPreparedStatement = connexion
					.getConnection().prepareStatement(StockDAO.READ_REQUEST)) {
				readPreparedStatement.setString(1, idClient);
				try (ResultSet resultSet = readPreparedStatement.executeQuery()) {
					if (resultSet.next()) {
						stockBean = new StockBean();
						stockBean.setIdStock(resultSet.getString(1));
						stockBean.setQuantite(resultSet.getInt(2));
						stockBean.setPrix(resultSet.getBigDecimal(3));
						stockBean.setRabais(resultSet.getBigDecimal(4));
					}
				}
			}
			return stockBean;
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
			StockBean stockBean = (StockBean) bean;
			try (PreparedStatement updatePreparedStatement = connexion
					.getConnection().prepareStatement(StockDAO.UPDATE_REQUEST)) {
				updatePreparedStatement.setString(1, stockBean.getIdStock());
				updatePreparedStatement.setInt(2, stockBean.getQuantite());
				updatePreparedStatement.setBigDecimal(3, stockBean.getPrix());
				updatePreparedStatement.setBigDecimal(4, stockBean.getRabais());
				updatePreparedStatement.setString(5, stockBean.getIdStock());
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
			StockBean stockBean = (StockBean) bean;
			try (PreparedStatement deletePreparedStatement = connexion
					.getConnection().prepareStatement(StockDAO.DELETE_REQUEST)) {
				deletePreparedStatement.setString(1, stockBean.getIdStock());
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
	public List<StockBean> getAll(Connexion connexion, String sortByPropertyName)
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
			List<StockBean> stocks = Collections.emptyList();
			try (PreparedStatement getAllPreparedStatement = connexion
					.getConnection().prepareStatement(StockDAO.GET_ALL_REQUEST)) {
				try (ResultSet resultSet = getAllPreparedStatement.executeQuery()) {
					StockBean stockBean = null;
					if (resultSet.next()) {
						stocks = new ArrayList<>();
						do {
							stockBean = new StockBean();
							stockBean.setIdStock(resultSet.getString(1));
							stockBean.setQuantite(resultSet.getInt(2));
							stockBean.setPrix(resultSet.getBigDecimal(3));
							stockBean.setRabais(resultSet.getBigDecimal(4));
							stocks.add(stockBean);
						} while (resultSet.next());
					}
				}
			}
			return stocks;
		} catch (SQLException 
				| InvalidHibernateSessionException 
				| InvalidSortByPropertyException exception) {
			throw new DAOException(exception);
		}
	}

	private static String getPrimaryKey(Connexion connexion)
			throws DAOException {
			return DAO.getPrimaryKey(connexion, StockDAO.CREATE_PRIMARY_KEY);
	}
}
