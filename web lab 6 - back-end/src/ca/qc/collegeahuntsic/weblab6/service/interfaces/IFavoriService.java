
package ca.qc.collegeahuntsic.weblab6.service.interfaces;

import java.util.List;
import ca.qc.collegeahuntsic.weblab6.dto.FavoriDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.ServiceException;
import org.hibernate.Session;

public interface IFavoriService extends IService {

    List<FavoriDTO> getAllFavoris(Session session,
        String sortByPropertyName) throws InvalidHibernateSessionException,
        InvalidSortByPropertyException,
        ServiceException;

    void deleteFavori(Session session,
        FavoriDTO favoriDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException;

    void updateFavori(Session session,
        FavoriDTO favoriDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException;

    FavoriDTO getFavori(Session session,
        String idFavori) throws InvalidHibernateSessionException,
        InvalidPrimaryKeyException,
        ServiceException;

    void addFavori(Session session,
        FavoriDTO favoriDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException;
}
