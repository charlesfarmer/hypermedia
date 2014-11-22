
package ca.qc.collegeahuntsic.weblab6.service.interfaces;

import java.util.List;
import ca.qc.collegeahuntsic.weblab6.dto.MembreDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.ServiceException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.UsernameAlreadyTakenException;
import org.hibernate.Session;

public interface IMembreService extends IService {

    void addMembre(Session session,
        MembreDTO membreDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException;

    MembreDTO getMembre(Session session,
        String idMembre) throws InvalidHibernateSessionException,
        InvalidPrimaryKeyException,
        ServiceException;

    void updateMembre(Session session,
        MembreDTO membreDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException;

    void deleteMembre(Session session,
        MembreDTO membreDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException;

    List<MembreDTO> getAllMembres(Session session,
        String sortByPropertyName) throws InvalidHibernateSessionException,
        InvalidSortByPropertyException,
        ServiceException;

    void inscrireMembre(Session session,
        MembreDTO membreDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        UsernameAlreadyTakenException,
        ServiceException;

}
