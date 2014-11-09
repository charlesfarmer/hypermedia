package ca.qc.collegeahuntsic.weblab5.dao.implementations;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.qc.collegeahuntsic.weblab5.bean.Bean;
import ca.qc.collegeahuntsic.weblab5.bean.ProfilBean;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IProfilDAO;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab5.exception.dto.InvalidDTOClassException;
import ca.qc.collegeahuntsic.weblab5.exception.dto.InvalidDTOException;

public class ProfilDAO extends DAO implements IProfilDAO {

	private static final String CREATE_PRIMARY_KEY = "SELECT SEQ_ID_PROFIL.NEXTVAL FROM DUAL";
	private static final String CREATE_REQUEST = "INSERT INTO Profil(IDProfil, Nom, Prenom)"
			+ " VALUES (?,?,?)";
	private static final String READ_REQUEST = "SELECT IDProfil, Nom, Prenom"
			+ " FROM Profil WHERE IDProfil= ?";
	private static final String UPDATE_REQUEST = "UPDATE Profil"
			+ " SET IDProfil=?, Nom=?, Prenom=?" + " WHERE IDProfil = ?";
	private static final String DELETE_REQUEST = "DELETE FROM Profil"
			+ " WHERE IDProfil = ?";
	private static final String GET_ALL_REQUEST = "SELECT IDProfil, Nom, Prenom"
			+ " FROM Profil";

	public ProfilDAO(Class<ProfilBean> beanClass)
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
			ProfilBean profilBean = (ProfilBean) bean;
			try (PreparedStatement createPreparedStatement = connexion
					.getConnection().prepareStatement(ProfilDAO.CREATE_REQUEST)) {
				profilBean.setIdProfil(ProfilDAO.getPrimaryKey(connexion));
				createPreparedStatement.setString(1, profilBean.getIdProfil());
				createPreparedStatement.setString(2, profilBean.getNom());
				createPreparedStatement.setString(3, profilBean.getPrenom());
				createPreparedStatement.executeUpdate();
			}
			return profilBean;
		} catch (SQLException 
				| InvalidHibernateSessionException 
				| InvalidDTOClassException 
				| InvalidDTOException exception) {
			throw new DAOException(exception);
		}
	}

	@Override
	public ProfilBean get(Connexion connexion, Serializable primaryKey)
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
			ProfilBean profilBean = null;
			try (PreparedStatement readPreparedStatement = connexion
					.getConnection().prepareStatement(ProfilDAO.READ_REQUEST)) {
				readPreparedStatement.setString(1, idClient);
				try (ResultSet resultSet = readPreparedStatement.executeQuery()) {
					if (resultSet.next()) {
						profilBean = new ProfilBean();
						profilBean.setIdProfil(resultSet.getString(1));
						profilBean.setNom(resultSet.getString(2));
						profilBean.setPrenom(resultSet.getString(3));
					}
				}
			}
			return profilBean;
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
			ProfilBean profilBean = (ProfilBean) bean;
			try (PreparedStatement updatePreparedStatement = connexion
					.getConnection().prepareStatement(ProfilDAO.UPDATE_REQUEST)) {
				updatePreparedStatement.setString(1, profilBean.getIdProfil());
				updatePreparedStatement.setString(2, profilBean.getNom());
				updatePreparedStatement.setString(3, profilBean.getPrenom());
				updatePreparedStatement.setString(4, profilBean.getIdProfil());
				updatePreparedStatement.executeUpdate();
			}
		} catch (SQLException 
				| InvalidHibernateSessionException 
				| InvalidDTOException 
				| InvalidDTOClassException exception) {
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
			ProfilBean profilBean = (ProfilBean) bean;
			try (PreparedStatement deletePreparedStatement = connexion
					.getConnection().prepareStatement(ProfilDAO.DELETE_REQUEST)) {
				deletePreparedStatement.setString(1, profilBean.getIdProfil());
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
	public List<ProfilBean> getAll(Connexion connexion,
			String sortByPropertyName) throws DAOException {
		try {
			if (connexion == null) {
				throw new InvalidHibernateSessionException(
						"La connexion ne peut être null");
			}
			if (sortByPropertyName == null) {
				throw new InvalidSortByPropertyException(
						"La propriété utilisée pour classer ne peut être null");
			}
			List<ProfilBean> profils = Collections.emptyList();
			try (PreparedStatement getAllPreparedStatement = connexion
					.getConnection().prepareStatement(ProfilDAO.GET_ALL_REQUEST)) {
				try (ResultSet resultSet = getAllPreparedStatement.executeQuery()) {
					ProfilBean profilBean = null;
					if (resultSet.next()) {
						profils = new ArrayList<>();
						do {
							profilBean = new ProfilBean();
							profilBean.setIdProfil(resultSet.getString(1));
							profilBean.setNom(resultSet.getString(2));
							profilBean.setPrenom(resultSet.getString(3));
							profils.add(profilBean);
						} while (resultSet.next());
					}
				}
			}
			return profils;
		} catch (SQLException 
				| InvalidHibernateSessionException 
				| InvalidSortByPropertyException sqlException) {
			throw new DAOException(sqlException);
		}
	}

	private static String getPrimaryKey(Connexion connexion)
			throws DAOException {
		return DAO.getPrimaryKey(connexion, ProfilDAO.CREATE_PRIMARY_KEY);
	}
}
