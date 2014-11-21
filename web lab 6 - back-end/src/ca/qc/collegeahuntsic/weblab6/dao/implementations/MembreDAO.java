
package ca.qc.collegeahuntsic.weblab6.dao.implementations;

import ca.qc.collegeahuntsic.weblab6.dao.interfaces.IMembreDAO;
import ca.qc.collegeahuntsic.weblab6.dto.MembreDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.DAOException;

public class MembreDAO extends DAO implements IMembreDAO {

    MembreDAO(Class<MembreDTO> membreDTOClass) throws DAOException {
        super(membreDTOClass);
    }

}
