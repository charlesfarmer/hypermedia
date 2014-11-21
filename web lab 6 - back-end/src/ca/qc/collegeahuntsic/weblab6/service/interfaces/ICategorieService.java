
package ca.qc.collegeahuntsic.weblab6.service.interfaces;

import java.util.List;
import ca.qc.collegeahuntsic.weblab6.dto.CategorieDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.ServiceException;
import org.hibernate.Session;

public interface ICategorieService {

    List<CategorieDTO> getAllCategories(Session session,
        String sortByPropertyName) throws InvalidHibernateSessionException,
        InvalidSortByPropertyException,
        ServiceException;

    void deleteCategorie(Session session,
        CategorieDTO categorieDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException;

    void updateCategorie(Session session,
        CategorieDTO categorieDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException;

    CategorieDTO getCategorie(Session session,
        String idCategorie) throws InvalidHibernateSessionException,
        InvalidPrimaryKeyException,
        ServiceException;

    void addCategorie(Session session,
        CategorieDTO categorieDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException;
}
