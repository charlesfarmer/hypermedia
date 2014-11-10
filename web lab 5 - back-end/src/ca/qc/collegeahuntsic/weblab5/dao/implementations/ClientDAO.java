
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
import ca.qc.collegeahuntsic.weblab5.bean.ProfilBean;
import ca.qc.collegeahuntsic.weblab5.dao.interfaces.IClientDAO;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab5.exception.dto.InvalidDTOClassException;
import ca.qc.collegeahuntsic.weblab5.exception.dto.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab5.exception.service.InvalidDAOException;

public class ClientDAO extends DAO implements IClientDAO {

    private static final String CREATE_PRIMARY_KEY = "SELECT SEQ_ID_CLIENT.NEXTVAL FROM DUAL";

    private static final String CREATE_REQUEST = "INSERT INTO Client(IDClient, Email, Passwd, ProfilID)"
        + " VALUES (?,?,?,?)";

    private static final String READ_REQUEST = "SELECT IDClient, Email, Passwd, ProfilID"
        + " FROM Client WHERE IDClient = ?";

    private static final String UPDATE_REQUEST = "UPDATE Client"
        + " SET IDClient=?, Email=?, Passwd=?, ProfilID=?"
        + " WHERE IDClient = ?";

    private static final String DELETE_REQUEST = "DELETE FROM Client"
        + " WHERE IDClient = ?";

    private static final String GET_ALL_REQUEST = "SELECT IDClient, Email, Passwd, ProfilID"
        + " FROM Client";

    private static final String FIND_BY_EMAIL_REQUEST = "SELECT IDClient, Email, Passwd, ProfilID"
        + " FROM Client WHERE Email = ?";

    public ClientDAO(Class<ClientBean> beanClass) throws InvalidDTOClassException {
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
            ClientBean clientBean = (ClientBean) bean;
            try(
                PreparedStatement createPreparedStatement = connexion.getConnection().prepareStatement(ClientDAO.CREATE_REQUEST)) {
                clientBean.setIdClient(ClientDAO.getPrimaryKey(connexion));
                createPreparedStatement.setString(1,
                    clientBean.getIdClient());
                createPreparedStatement.setString(2,
                    clientBean.getEmail());
                createPreparedStatement.setString(3,
                    clientBean.getPassword());
                createPreparedStatement.setString(4,
                    clientBean.getProfilBean().getIdProfil());
                createPreparedStatement.executeUpdate();
            }
            return clientBean;
        } catch(
            SQLException
            | InvalidHibernateSessionException
            | InvalidDTOException
            | InvalidDTOClassException exception) {
            throw new DAOException(exception);
        }
    }

    @Override
    public ClientBean get(Connexion connexion,
        Serializable primaryKey) throws DAOException {
        try {
            if(connexion == null) {
                throw new InvalidHibernateSessionException("La connexion ne peut être null");
            }
            if(primaryKey == null) {
                throw new InvalidPrimaryKeyException("La clef primaire ne peut être null");
            }
            String idClient = (String) primaryKey;
            ClientBean clientBean = null;
            try(
                PreparedStatement readPreparedStatement = connexion.getConnection().prepareStatement(ClientDAO.READ_REQUEST)) {
                readPreparedStatement.setString(1,
                    idClient);
                try(
                    ResultSet resultSet = readPreparedStatement.executeQuery()) {
                    if(resultSet.next()) {
                        clientBean = new ClientBean();
                        clientBean.setIdClient(resultSet.getString(1));
                        clientBean.setEmail(resultSet.getString(2));
                        clientBean.setPassword(resultSet.getString(3));
                        ProfilBean profilBean = new ProfilBean();
                        profilBean.setIdProfil(resultSet.getString(4));
                        clientBean.setProfilBean(profilBean);
                    }
                }
            }
            return clientBean;
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
            ClientBean clientBean = (ClientBean) bean;
            try(
                PreparedStatement updatePreparedStatement = connexion.getConnection().prepareStatement(ClientDAO.UPDATE_REQUEST)) {
                updatePreparedStatement.setString(1,
                    clientBean.getIdClient());
                updatePreparedStatement.setString(2,
                    clientBean.getEmail());
                updatePreparedStatement.setString(3,
                    clientBean.getPassword());
                updatePreparedStatement.setString(4,
                    clientBean.getProfilBean().getIdProfil());
                updatePreparedStatement.setString(5,
                    clientBean.getIdClient());
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
            ClientBean clientBean = (ClientBean) bean;
            try(
                PreparedStatement deletePreparedStatement = connexion.getConnection().prepareStatement(ClientDAO.DELETE_REQUEST)) {
                deletePreparedStatement.setString(1,
                    clientBean.getIdClient());
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
    public List<ClientBean> getAll(Connexion connexion,
        String sortByPropertyName) throws DAOException {
        try {
            if(connexion == null) {
                throw new InvalidHibernateSessionException("La connexion ne peut être null");
            }
            if(sortByPropertyName == null) {
                throw new InvalidSortByPropertyException("La propriété utilisée pour classer ne peut être null");
            }
            List<ClientBean> clients = Collections.emptyList();
            try(
                PreparedStatement getAllPreparedStatement = connexion.getConnection().prepareStatement(ClientDAO.GET_ALL_REQUEST)) {
                try(
                    ResultSet resultSet = getAllPreparedStatement.executeQuery()) {
                    ClientBean clientBean = null;
                    if(resultSet.next()) {
                        clients = new ArrayList<>();
                        do {
                            clientBean = new ClientBean();
                            clientBean.setIdClient(resultSet.getString(1));
                            clientBean.setEmail(resultSet.getString(2));
                            clientBean.setPassword(resultSet.getString(3));
                            ProfilBean profilBean = new ProfilBean();
                            profilBean.setIdProfil(resultSet.getString(4));
                            clientBean.setProfilBean(profilBean);
                            clients.add(clientBean);
                        } while(resultSet.next());
                    }
                }
            }
            return clients;
        } catch(
            SQLException
            | InvalidHibernateSessionException
            | InvalidSortByPropertyException exception) {
            throw new DAOException(exception);
        }
    }

    private static String getPrimaryKey(Connexion connexion) throws DAOException {
        return DAO.getPrimaryKey(connexion,
            ClientDAO.CREATE_PRIMARY_KEY);
    }

    @Override
    public ClientBean findByEmail(Connexion connexion,
        String email) throws DAOException {
        try {
            if(connexion == null) {
                throw new InvalidHibernateSessionException("La connexion ne peut être null");
            }
            if(email == null) {
                throw new InvalidDAOException("Le courriel du membre ne peut être null");
            }
            ClientBean clientBean = null;
            try(
                PreparedStatement findByEmailPreparedStatement = connexion.getConnection().prepareStatement(ClientDAO.FIND_BY_EMAIL_REQUEST)) {
                findByEmailPreparedStatement.setString(1,
                    email);
                try(
                    ResultSet resultSet = findByEmailPreparedStatement.executeQuery()) {
                    if(resultSet.next()) {
                        clientBean = new ClientBean();
                        clientBean.setIdClient(resultSet.getString(1));
                        clientBean.setEmail(resultSet.getString(2));
                        clientBean.setPassword(resultSet.getString(3));
                        ProfilBean profilBean = new ProfilBean();
                        profilBean.setIdProfil(resultSet.getString(4));
                        clientBean.setProfilBean(profilBean);
                    }
                }
            }
            return clientBean;
        } catch(
            SQLException
            | InvalidHibernateSessionException
            | InvalidDAOException sqlException) {
            throw new DAOException(sqlException);
        }
    }
}
