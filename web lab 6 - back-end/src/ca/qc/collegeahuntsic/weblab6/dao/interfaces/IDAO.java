
package ca.qc.collegeahuntsic.weblab6.dao.interfaces;

import java.io.Serializable;
import java.util.List;
import ca.qc.collegeahuntsic.weblab6.dto.DTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.DAOException;
import org.hibernate.Session;

public interface IDAO {

    void add(Session session,
        DTO dto) throws DAOException;

    DTO get(Session session,
        Serializable primaryKey) throws DAOException;

    void update(Session session,
        DTO dto) throws DAOException;

    void save(Session session,
        DTO dto) throws DAOException;

    void delete(Session session,
        DTO dto) throws DAOException;

    List<?> getAll(Session session,
        String sortByPropertyName) throws DAOException;

}
