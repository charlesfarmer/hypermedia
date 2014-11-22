
package ca.qc.collegeahuntsic.weblab6.dao.interfaces;

import java.util.List;
import ca.qc.collegeahuntsic.weblab6.dto.MembreDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.DAOException;
import org.hibernate.Session;

public interface IMembreDAO extends IDAO {

    List<MembreDTO> findByUsername(Session session,
        String username) throws DAOException;
}
