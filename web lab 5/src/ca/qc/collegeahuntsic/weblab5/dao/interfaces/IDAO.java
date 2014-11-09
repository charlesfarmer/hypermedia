
package ca.qc.collegeahuntsic.weblab5.dao.interfaces;

import java.io.Serializable;
import java.util.List;
import ca.qc.collegeahuntsic.weblab5.bean.Bean;
import ca.qc.collegeahuntsic.weblab5.db.Connexion;
import ca.qc.collegeahuntsic.weblab5.exception.dao.DAOException;

public interface IDAO {

    Bean add(Connexion connexion,
        Bean bean) throws DAOException;

    Bean get(Connexion connexion,
        Serializable primaryKey) throws DAOException;

    void update(Connexion connexion,
        Bean bean) throws DAOException;

    void delete(Connexion connexion,
        Bean bean) throws DAOException;

    List<?> getAll(Connexion connexion,
        String sortByPropertyName) throws DAOException;
}
