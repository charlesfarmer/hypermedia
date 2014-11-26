
package ca.qc.collegeahuntsic.weblab6.dao.implementations;

import java.util.List;
import ca.qc.collegeahuntsic.weblab6.dao.interfaces.IMembreDAO;
import ca.qc.collegeahuntsic.weblab6.dto.MembreDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.DAOException;
import org.hibernate.Session;

public class MembreDAO extends DAO implements IMembreDAO {

    MembreDAO(Class<MembreDTO> membreDTOClass) throws DAOException {
        super(membreDTOClass);
    }

    @Override
    public List<MembreDTO> findByUsername(Session session,
        String username) throws DAOException {
        return (List<MembreDTO>) find(session,
            MembreDTO.USERNAME_COLUMN_NAME,
            username,
            MembreDTO.USERNAME_COLUMN_NAME);
    }
}
