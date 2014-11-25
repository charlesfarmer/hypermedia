
package ca.qc.collegeahuntsic.weblab6.service.implementations;

import java.util.List;
import ca.qc.collegeahuntsic.weblab6.dao.interfaces.IMarchandDAO;
import ca.qc.collegeahuntsic.weblab6.dto.MarchandDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidDAOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab6.exception.dto.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab6.exception.service.MarchandAlreadyAddedException;
import ca.qc.collegeahuntsic.weblab6.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab6.service.interfaces.IMarchandService;
import org.hibernate.Session;

public class MarchandService extends Service implements IMarchandService {

    private IMarchandDAO marchandDAO;

    MarchandService(IMarchandDAO marchandDAO) throws InvalidDAOException {
        super();
        if(marchandDAO == null) {
            throw new InvalidDAOException("le dao ne peut être null");
        }
        setMarchandDAO(marchandDAO);
    }

    public IMarchandDAO getMarchandDAO() {
        return this.marchandDAO;
    }

    public void setMarchandDAO(IMarchandDAO marchandDAO) {
        this.marchandDAO = marchandDAO;
    }

    @Override
    public void addMarchand(Session session,
        MarchandDTO marchandDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException {
        try {
            getMarchandDAO().add(session,
                marchandDTO);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public MarchandDTO getMarchand(Session session,
        String idMarchand) throws InvalidHibernateSessionException,
        InvalidPrimaryKeyException,
        ServiceException {
        try {
            return (MarchandDTO) getMarchandDAO().get(session,
                idMarchand);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public void updateMarchand(Session session,
        MarchandDTO marchandDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException {
        try {
            getMarchandDAO().update(session,
                marchandDTO);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public void deleteMarchand(Session session,
        MarchandDTO marchandDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException {
        try {
            getMarchandDAO().delete(session,
                marchandDTO);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public List<MarchandDTO> getAllMarchands(Session session,
        String sortByPropertyName) throws InvalidHibernateSessionException,
        InvalidSortByPropertyException,
        ServiceException {
        try {
            return (List<MarchandDTO>) getMarchandDAO().getAll(session,
                sortByPropertyName);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public void ajouterMarchand(Session session,
        MarchandDTO marchandDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        MarchandAlreadyAddedException,
        ServiceException {
        List<MarchandDTO> marchands = (List<MarchandDTO>) marchandDTO.getMembreDTO().getMarchands();
        for(MarchandDTO marchand : marchands) {
            if(marchandDTO.getName().equals(marchand.getName())) {
                throw new MarchandAlreadyAddedException();
            }
        }
        addMarchand(session,
            marchandDTO);
    }

}
