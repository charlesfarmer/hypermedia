
package ca.qc.collegeahuntsic.weblab6.service.interfaces;

import java.util.List;
import ca.qc.collegeahuntsic.weblab6.dto.LigneVitrineDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.ServiceException;
import org.hibernate.Session;

public interface ILigneVitrineService extends IService {

    void addLigneVitrine(Session session,
        LigneVitrineDTO ligneVitrineDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException;

    LigneVitrineDTO getLigneVitrine(Session session,
        String idLigneVitrine) throws InvalidHibernateSessionException,
        InvalidPrimaryKeyException,
        ServiceException;

    void updateLigneVitrine(Session session,
        LigneVitrineDTO ligneVitrineDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException;

    void deleteLigneVitrine(Session session,
        LigneVitrineDTO ligneVitrineDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException;

    List<LigneVitrineDTO> getAllLigneVitrines(Session session,
        String sortByPropertyName) throws InvalidHibernateSessionException,
        InvalidSortByPropertyException,
        ServiceException;
}
