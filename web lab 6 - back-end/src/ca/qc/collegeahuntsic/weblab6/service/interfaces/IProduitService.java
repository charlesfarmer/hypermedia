
package ca.qc.collegeahuntsic.weblab6.service.interfaces;

import java.util.List;
import ca.qc.collegeahuntsic.weblab6.dto.ProduitDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.ServiceException;
import org.hibernate.Session;

public interface IProduitService extends IService {

    List<ProduitDTO> getAllProduits(Session session,
        String sortByPropertyName) throws InvalidHibernateSessionException,
        InvalidSortByPropertyException,
        ServiceException;

    void deleteProduit(Session session,
        ProduitDTO produitDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException;

    void updateProduit(Session session,
        ProduitDTO produitDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException;

    ProduitDTO getProduit(Session session,
        String idProduit) throws InvalidHibernateSessionException,
        InvalidPrimaryKeyException,
        ServiceException;

    void addProduit(Session session,
        ProduitDTO produitDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        ServiceException;
}
