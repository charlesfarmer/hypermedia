
package ca.qc.collegeahuntsic.weblab6.dao.interfaces;

import java.util.List;
import ca.qc.collegeahuntsic.weblab6.dto.FavoriDTO;
import org.hibernate.Session;

public interface IFavoriDAO extends IDAO {

    List<FavoriDTO> findTopFavoris(Session session,
        int topCombien);
}
