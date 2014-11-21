
package ca.qc.collegeahuntsic.weblab6.service.implementations;

import java.util.List;
import ca.qc.collegeahuntsic.weblab6.dao.interfaces.IMembreDAO;
import ca.qc.collegeahuntsic.weblab6.dto.MembreDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidDAOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.ServiceException;
import ca.qc.collegeahuntsic.weblab6.service.interfaces.IMembreService;
import org.hibernate.Session;

public class MembreService extends Service implements IMembreService {

    private IMembreDAO membreDAO;

    MembreService(IMembreDAO membreDAO) throws InvalidDAOException {
        super();
        if(membreDAO == null) {
            throw new InvalidDAOException("le dao ne peut être null");
        }
        setMembreDAO(membreDAO);
    }

    public IMembreDAO getMembreDAO() {
        return this.membreDAO;
    }

    public void setMembreDAO(IMembreDAO membreDAO) {
        this.membreDAO = membreDAO;
    }

    @Override
    public void addMembre(Session session,
        MembreDTO membreDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException {
        try {
            getMembreDAO().add(session,
                membreDTO);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public MembreDTO getMembre(Session session,
        String idMembre) throws InvalidHibernateSessionException,
        InvalidPrimaryKeyException,
        ServiceException {
        try {
            return (MembreDTO) getMembreDAO().get(session,
                idMembre);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public void updateMembre(Session session,
        MembreDTO membreDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException {
        try {
            getMembreDAO().update(session,
                membreDTO);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public void deleteMembre(Session session,
        MembreDTO membreDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException {
        try {
            getMembreDAO().delete(session,
                membreDTO);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public List<MembreDTO> getAllMembres(Session session,
        String sortByPropertyName) throws InvalidHibernateSessionException,
        InvalidSortByPropertyException,
        ServiceException {
        try {
            return (List<MembreDTO>) getMembreDAO().getAll(session,
                sortByPropertyName);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

}
