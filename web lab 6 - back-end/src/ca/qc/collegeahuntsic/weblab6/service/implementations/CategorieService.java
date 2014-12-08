
package ca.qc.collegeahuntsic.weblab6.service.implementations;

import java.util.List;
import ca.qc.collegeahuntsic.weblab6.dao.interfaces.ICategorieDAO;
import ca.qc.collegeahuntsic.weblab6.dto.CategorieDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidDAOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab6.exception.dto.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab6.exception.service.CategorieAlreadyAddedException;
import ca.qc.collegeahuntsic.weblab6.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab6.service.interfaces.ICategorieService;
import org.hibernate.Session;

public class CategorieService extends Service implements ICategorieService {

    private ICategorieDAO categorieDAO;

    CategorieService(ICategorieDAO categorieDAO) throws InvalidDAOException {
        super();
        if(categorieDAO == null) {
            throw new InvalidDAOException("le dao ne peut être null");
        }
        setCategorieDAO(categorieDAO);
    }

    public ICategorieDAO getCategorieDAO() {
        return this.categorieDAO;
    }

    public void setCategorieDAO(ICategorieDAO categorieDAO) {
        this.categorieDAO = categorieDAO;
    }

    @Override
    public void addCategorie(Session session,
        CategorieDTO categorieDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException {
        try {
            getCategorieDAO().add(session,
                categorieDTO);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public CategorieDTO getCategorie(Session session,
        String idCategorie) throws InvalidHibernateSessionException,
        InvalidPrimaryKeyException,
        ServiceException {
        try {
            return (CategorieDTO) getCategorieDAO().get(session,
                idCategorie);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public void updateCategorie(Session session,
        CategorieDTO categorieDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException {
        try {
            getCategorieDAO().update(session,
                categorieDTO);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public void deleteCategorie(Session session,
        CategorieDTO categorieDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException {
        try {
            getCategorieDAO().delete(session,
                categorieDTO);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public List<CategorieDTO> getAllCategories(Session session,
        String sortByPropertyName) throws InvalidHibernateSessionException,
        InvalidSortByPropertyException,
        ServiceException {
        try {
            return (List<CategorieDTO>) getCategorieDAO().getAll(session,
                sortByPropertyName);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public void ajouterCategorie(Session session,
        CategorieDTO categorieDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        CategorieAlreadyAddedException,
        ServiceException {
        /*List<CategorieDTO> categories = (List<CategorieDTO>) categorieDTO.getMembreDTO().getCategories();
        for(CategorieDTO categorie : categories) {
            if(categorie.getName().equals(categorieDTO.getName())) {
                throw new CategorieAlreadyAddedException();
            }
        }
        addCategorie(session,
            categorieDTO);*/
    }

}
