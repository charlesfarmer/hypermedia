
package ca.qc.collegeahuntsic.weblab6.service.implementations;

import java.util.List;
import ca.qc.collegeahuntsic.weblab6.dao.interfaces.ILigneVitrineDAO;
import ca.qc.collegeahuntsic.weblab6.dto.LigneVitrineDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidDAOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab6.exception.dto.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab6.exception.service.LigneVitrineAlreadyAddedException;
import ca.qc.collegeahuntsic.weblab6.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab6.service.interfaces.ILigneVitrineService;
import org.hibernate.Session;

public class LigneVitrineService extends Service implements ILigneVitrineService {

    private ILigneVitrineDAO ligneVitrineDAO;

    LigneVitrineService(ILigneVitrineDAO ligneVitrineDAO) throws InvalidDAOException {
        super();
        if(ligneVitrineDAO == null) {
            throw new InvalidDAOException("le dao ne peut être null");
        }
        setLigneVitrineDAO(ligneVitrineDAO);
    }

    public ILigneVitrineDAO getLigneVitrineDAO() {
        return this.ligneVitrineDAO;
    }

    public void setLigneVitrineDAO(ILigneVitrineDAO ligneVitrineDAO) {
        this.ligneVitrineDAO = ligneVitrineDAO;
    }

    @Override
    public void addLigneVitrine(Session session,
        LigneVitrineDTO ligneVitrineDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException {
        try {
            getLigneVitrineDAO().add(session,
                ligneVitrineDTO);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public LigneVitrineDTO getLigneVitrine(Session session,
        String idLigneVitrine) throws InvalidHibernateSessionException,
        InvalidPrimaryKeyException,
        ServiceException {
        try {
            return (LigneVitrineDTO) getLigneVitrineDAO().get(session,
                idLigneVitrine);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public void updateLigneVitrine(Session session,
        LigneVitrineDTO ligneVitrineDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException {
        try {
            getLigneVitrineDAO().update(session,
                ligneVitrineDTO);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public void deleteLigneVitrine(Session session,
        LigneVitrineDTO ligneVitrineDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException {
        try {
            getLigneVitrineDAO().delete(session,
                ligneVitrineDTO);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public List<LigneVitrineDTO> getAllLigneVitrines(Session session,
        String sortByPropertyName) throws InvalidHibernateSessionException,
        InvalidSortByPropertyException,
        ServiceException {
        try {
            return (List<LigneVitrineDTO>) getLigneVitrineDAO().getAll(session,
                sortByPropertyName);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public void ajouterLigneVitrine(Session session,
        LigneVitrineDTO ligneVitrineDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        LigneVitrineAlreadyAddedException,
        ServiceException {
        List<LigneVitrineDTO> ligneVitrines = (List<LigneVitrineDTO>) ligneVitrineDTO.getVitrineDTO().getLigneVitrines();
        for(LigneVitrineDTO ligneVitrine : ligneVitrines) {
            if(ligneVitrine.getProduitDTO().getIdProduit().equals(ligneVitrineDTO.getProduitDTO().getIdProduit())) {
                throw new LigneVitrineAlreadyAddedException();
            }
        }
        addLigneVitrine(session,
            ligneVitrineDTO);
    }
}
