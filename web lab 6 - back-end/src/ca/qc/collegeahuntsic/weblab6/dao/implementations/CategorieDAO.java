
package ca.qc.collegeahuntsic.weblab6.dao.implementations;

import ca.qc.collegeahuntsic.weblab6.dao.interfaces.ICategorieDAO;
import ca.qc.collegeahuntsic.weblab6.dto.CategorieDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.DAOException;

public class CategorieDAO extends DAO implements ICategorieDAO {

    CategorieDAO(Class<CategorieDTO> categorieDTOClass) throws DAOException {
        super(categorieDTOClass);
    }

}
