
package ca.qc.collegeahuntsic.weblab6.dao.implementations;

import ca.qc.collegeahuntsic.weblab6.dao.interfaces.IMarchandDAO;
import ca.qc.collegeahuntsic.weblab6.dto.MarchandDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.DAOException;

public class MarchandDAO extends DAO implements IMarchandDAO {

    MarchandDAO(Class<MarchandDTO> marchandDTOClass) throws DAOException {
        super(marchandDTOClass);
    }

}
