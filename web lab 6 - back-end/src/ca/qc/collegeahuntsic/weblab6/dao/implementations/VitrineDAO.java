
package ca.qc.collegeahuntsic.weblab6.dao.implementations;

import ca.qc.collegeahuntsic.weblab6.dao.interfaces.IVitrineDAO;
import ca.qc.collegeahuntsic.weblab6.dto.VitrineDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.DAOException;

public class VitrineDAO extends DAO implements IVitrineDAO {

    VitrineDAO(Class<VitrineDTO> vitrineDTOClass) throws DAOException {
        super(vitrineDTOClass);
    }

}
