
package ca.qc.collegeahuntsic.weblab6.dao.implementations;

import ca.qc.collegeahuntsic.weblab6.dao.interfaces.IFavoriDAO;
import ca.qc.collegeahuntsic.weblab6.dto.FavoriDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.DAOException;

public class FavoriDAO extends DAO implements IFavoriDAO {

    FavoriDAO(Class<FavoriDTO> favoriDTOClass) throws DAOException {
        super(favoriDTOClass);
    }

}
