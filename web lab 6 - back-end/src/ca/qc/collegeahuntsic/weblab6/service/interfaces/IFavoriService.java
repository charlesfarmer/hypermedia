
package ca.qc.collegeahuntsic.weblab6.service.interfaces;

import java.util.List;

import ca.qc.collegeahuntsic.weblab6.dto.FavoriDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidCriterionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidCriterionValueException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab6.exception.dto.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab6.exception.service.FavoriAlreadyAddedException;
import ca.qc.collegeahuntsic.weblab6.exception.service.ServiceException;

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

    List<FavoriDTO> getTopFavoris(Session session,
        int topCombien) throws InvalidHibernateSessionException,
        InvalidCriterionException,
        InvalidCriterionValueException,
        ServiceException;

    void ajouterFavori(Session session,
        FavoriDTO favoriDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        FavoriAlreadyAddedException,
        ServiceException;
}
