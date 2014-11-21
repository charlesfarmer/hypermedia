
package ca.qc.collegeahuntsic.weblab6.dao.implementations;

import ca.qc.collegeahuntsic.weblab6.dao.interfaces.IProduitDAO;
import ca.qc.collegeahuntsic.weblab6.dto.ProduitDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.DAOException;

public class ProduitDAO extends DAO implements IProduitDAO {

    ProduitDAO(Class<ProduitDTO> membreDTOClass) throws DAOException {
        super(membreDTOClass);
    }

}
