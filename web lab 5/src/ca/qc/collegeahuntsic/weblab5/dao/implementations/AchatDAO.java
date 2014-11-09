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
import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IAchatDAO;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab5.exception.dto.InvalidDTOClassException;
import ca.qc.collegeahuntsic.weblab5.exception.dto.InvalidDTOException;

public class AchatDAO extends DAO implements IAchatDAO {

	private static final String CREATE_PRIMARY_KEY = "SELECT SEQ_ID_ACHAT.NEXTVAL FROM DUAL";
	private static final String CREATE_REQUEST = "INSERT INTO Achat(IDAchat, ClientID, DateAchat)"
			+ " VALUES (?, ?, ?)";
	private static final String READ_REQUEST = "SELECT IDAchat, ClientID, DateAchat"
			+ " FROM Achat WHERE IDAchat = ?";
	private static final String UPDATE_REQUEST = "UPDATE Achat"
			+ " SET IDAchat=?, ClientID=?, DateAchat=?"
			+ " WHERE IDAchat = ?";
	private static final String DELETE_REQUEST = "DELETE FROM Achat"
			+ " WHERE IDAchat = ?";
	private static final String GET_ALL_REQUEST = "SELECT IDAchat, ClientID, DateAchat"
			+ " FROM Achat";

	public AchatDAO(Class<AchatBean> beanClass)
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
			AchatBean achatBean = (AchatBean) bean;
			try (PreparedStatement createPreparedStatement = connexion
					.getConnection().prepareStatement(AchatDAO.CREATE_REQUEST)) {
				achatBean.setIdAchat(AchatDAO.getPrimaryKey(connexion));
				createPreparedStatement.setString(1, achatBean.getIdAchat());
				createPreparedStatement.setString(2, achatBean.getClientBean()
						.getIdClient());
				createPreparedStatement.setTimestamp(3, achatBean.getDateAchat());
				createPreparedStatement.executeUpdate();
			} 
			return achatBean;
		} catch (SQLException 
				| InvalidHibernateSessionException 
				| InvalidDTOException 
				| InvalidDTOClassException exception) {
			throw new DAOException(exception);
		}
	}

	@Override
	public AchatBean get(Connexion connexion, Serializable primaryKey)
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
			String idAchat = (String) primaryKey;
			AchatBean achatBean = null;
			try (PreparedStatement readPreparedStatement = connexion
					.getConnection().prepareStatement(AchatDAO.READ_REQUEST)) {
				readPreparedStatement.setString(1, idAchat);
				try (ResultSet resultSet = readPreparedStatement.executeQuery()) {
					if (resultSet.next()) {
						achatBean = new AchatBean();
						achatBean.setIdAchat(resultSet.getString(1));
						ClientBean clientBean = new ClientBean();
						clientBean.setIdClient(resultSet.getString(2));
						achatBean.setClientBean(clientBean);
						achatBean.setDateAchat(resultSet.getTimestamp(3));
					}
				}
			}
			return achatBean;
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
			AchatBean achatBean = (AchatBean) bean;
			try (PreparedStatement updatePreparedStatement = connexion
					.getConnection().prepareStatement(AchatDAO.UPDATE_REQUEST)) {
				updatePreparedStatement.setString(1, achatBean.getIdAchat());
				updatePreparedStatement.setString(2, achatBean.getClientBean()
						.getIdClient());
				updatePreparedStatement.setTimestamp(3, achatBean.getDateAchat());
				updatePreparedStatement.setString(4, achatBean.getIdAchat());
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
			AchatBean achatBean = (AchatBean) bean;
			try (PreparedStatement deletePreparedStatement = connexion
					.getConnection().prepareStatement(AchatDAO.DELETE_REQUEST)) {
				deletePreparedStatement.setString(1, achatBean.getIdAchat());
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
	public List<AchatBean> getAll(Connexion connexion, String sortByPropertyName)
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
			List<AchatBean> achats = Collections.emptyList();
			try (PreparedStatement getAllPreparedStatement = connexion
					.getConnection().prepareStatement(AchatDAO.GET_ALL_REQUEST)) {
				try (ResultSet resultSet = getAllPreparedStatement.executeQuery()) {
					AchatBean achatBean = null;
					if (resultSet.next()) {
						achats = new ArrayList<>();
						do {
							achatBean = new AchatBean();
							achatBean.setIdAchat(resultSet.getString(1));
							ClientBean clientBean = new ClientBean();
							clientBean.setIdClient(resultSet.getString(2));
							achatBean.setClientBean(clientBean);
							achatBean.setDateAchat(resultSet.getTimestamp(3));
							achats.add(achatBean);
						} while (resultSet.next());
					}
				}
			}
			return achats;
		} catch (SQLException 
				| InvalidHibernateSessionException 
				| InvalidSortByPropertyException exception) {
			throw new DAOException(exception);
		}
	}

	private static String getPrimaryKey(Connexion connexion)
			throws DAOException {
			return DAO.getPrimaryKey(connexion, AchatDAO.CREATE_PRIMARY_KEY);
	}
}
