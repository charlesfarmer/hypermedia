package ca.qc.collegeahuntsic.weblab6.service.interfaces;

import java.util.List;

import org.hibernate.Session;

import ca.qc.collegeahuntsic.weblab6.dto.MarchandDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.MarchandAlreadyAddedException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.ServiceException;

public interface IMarchandService extends IService {

	void addMarchand(Session session, MarchandDTO marchandDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			ServiceException;

	MarchandDTO getMarchand(Session session, String idMarchand)
			throws InvalidHibernateSessionException,
			InvalidPrimaryKeyException, ServiceException;

	void updateMarchand(Session session, MarchandDTO marchandDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			ServiceException;

	void deleteMarchand(Session session, MarchandDTO marchandDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			ServiceException;

    List<MarchandDTO> getAllMarchands(Session session,
        String sortByPropertyName) throws InvalidHibernateSessionException,
        InvalidSortByPropertyException,
        ServiceException;

    void ajouterMarchand(Session session,
        MarchandDTO marchandDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        MarchandAlreadyAddedException,
        ServiceException;
}
