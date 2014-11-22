
package ca.qc.collegeahuntsic.weblab6.dao.implementations;

import java.util.Collections;
import java.util.List;
import ca.qc.collegeahuntsic.weblab6.dao.interfaces.IFavoriDAO;
import ca.qc.collegeahuntsic.weblab6.dto.FavoriDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.DAOException;
import org.hibernate.Session;

public class FavoriDAO extends DAO implements IFavoriDAO {

    FavoriDAO(Class<FavoriDTO> favoriDTOClass) throws DAOException {
        super(favoriDTOClass);
    }

    @Override
    public List<FavoriDTO> findTopFavoris(Session session,
        int topCombien) {
        //TODO Implémenter la query en SQL d'abord, ensuite avec les criterion
        return Collections.EMPTY_LIST;
    }

}
