
package ca.qc.collegeahuntsic.weblab5.dao.implementations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab5.exception.dao.InvalidPrimaryKeyRequestException;
import ca.qc.collegeahuntsic.weblab5.exception.dto.InvalidDTOClassException;

public class DAO {

    private Class<?> beanClass;

    protected DAO(Class<?> beanClass) throws InvalidDTOClassException {
        super();
        if(beanClass == null) {
            throw new InvalidDTOClassException("La classe de DTO ne peut être null");
        }
        setBeanClass(beanClass);
    }

    protected Class<?> getBeanClass() {
        return this.beanClass;
    }

    private void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    protected static String getPrimaryKey(Connexion connexion,
        String createPrimaryKeyRequest) throws DAOException {
        try {
            if(connexion == null) {
                throw new InvalidHibernateSessionException("La connexion ne peut être null");
            }
            if(createPrimaryKeyRequest == null) {
                throw new InvalidPrimaryKeyRequestException("La requête de création de clef primaire ne peut être null");
            }
            try(
                PreparedStatement createPreparedStatement = connexion.getConnection().prepareStatement(createPrimaryKeyRequest)) {
                try(
                    ResultSet resultSet = createPreparedStatement.executeQuery()) {
                    if(resultSet.next()) {
                        return resultSet.getString(1);
                    }
                    throw new DAOException("Impossible de lire la séquence");
                }
            }
        } catch(
            SQLException
            | InvalidHibernateSessionException
            | InvalidPrimaryKeyRequestException sqlException) {
            throw new DAOException(sqlException);
        }
    }
}
