
package ca.qc.collegeahuntsic.weblab6.service.implementations;

import java.util.List;
import ca.qc.collegeahuntsic.weblab6.dao.interfaces.IFavoriDAO;
import ca.qc.collegeahuntsic.weblab6.dto.FavoriDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidCriterionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidCriterionValueException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidDAOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab6.exception.dto.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab6.exception.service.FavoriAlreadyAddedException;
import ca.qc.collegeahuntsic.weblab6.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab6.service.interfaces.IFavoriService;
import org.hibernate.Session;

public class FavoriService extends Service implements IFavoriService {

    private IFavoriDAO favoriDAO;

    FavoriService(IFavoriDAO favoriDAO) throws InvalidDAOException {
        super();
        if(favoriDAO == null) {
            throw new InvalidDAOException("le dao ne peut être null");
        }
        setFavoriDAO(favoriDAO);
    }

    public IFavoriDAO getFavoriDAO() {
        return this.favoriDAO;
    }

    public void setFavoriDAO(IFavoriDAO favoriDAO) {
        this.favoriDAO = favoriDAO;
    }

    @Override
    public void addFavori(Session session,
        FavoriDTO favoriDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException {
        try {
            getFavoriDAO().add(session,
                favoriDTO);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public FavoriDTO getFavori(Session session,
        String idFavori) throws InvalidHibernateSessionException,
        InvalidPrimaryKeyException,
        ServiceException {
        try {
            return (FavoriDTO) getFavoriDAO().get(session,
                idFavori);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public void updateFavori(Session session,
        FavoriDTO favoriDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException {
        try {
            getFavoriDAO().update(session,
                favoriDTO);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public void deleteFavori(Session session,
        FavoriDTO favoriDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException {
        try {
            getFavoriDAO().delete(session,
                favoriDTO);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public List<FavoriDTO> getAllFavoris(Session session,
        String sortByPropertyName) throws InvalidHibernateSessionException,
        InvalidSortByPropertyException,
        ServiceException {
        try {
            return (List<FavoriDTO>) getFavoriDAO().getAll(session,
                sortByPropertyName);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public List<FavoriDTO> getTopFavoris(Session session,
        int topCombien) throws InvalidHibernateSessionException,
        InvalidCriterionException,
        InvalidCriterionValueException,
        ServiceException {
        return getFavoriDAO().findTopFavoris(session,
            topCombien);
    }

    @Override
    public void ajouterFavori(Session session,
        FavoriDTO favoriDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        FavoriAlreadyAddedException,
        ServiceException {
        List<FavoriDTO> favoris = (List<FavoriDTO>) favoriDTO.getMembreDTO().getFavoris();
        for(FavoriDTO favori : favoris) {
            if(favori.getProduitDTO().getIdProduit().equals(favoriDTO.getProduitDTO().getIdProduit())) {
                throw new FavoriAlreadyAddedException();
            }
            addFavori(session,
                favoriDTO);
        }

    }
}
