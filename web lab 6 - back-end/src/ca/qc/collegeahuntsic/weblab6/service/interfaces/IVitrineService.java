package ca.qc.collegeahuntsic.weblab6.service.interfaces;

import java.util.List;

import org.hibernate.Session;

import ca.qc.collegeahuntsic.weblab6.dto.VitrineDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab6.exception.dto.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab6.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab6.exception.service.VitrineAlreadyAddedException;

public interface IVitrineService extends IService {

	List<VitrineDTO> getAllVitrines(Session session, String sortByPropertyName)
			throws InvalidHibernateSessionException,
			InvalidSortByPropertyException, ServiceException;

	void addVitrine(Session session, VitrineDTO vitrineDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			ServiceException;

	void deleteVitrine(Session session, VitrineDTO vitrineDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			ServiceException;

	void updateVitrine(Session session, VitrineDTO vitrineDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			ServiceException;

	VitrineDTO getVitrine(Session session, String idVitrine)
			throws InvalidHibernateSessionException,
			InvalidPrimaryKeyException, ServiceException;

	void ajouterVitrine(Session session, VitrineDTO vitrineDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			VitrineAlreadyAddedException, ServiceException;

}
