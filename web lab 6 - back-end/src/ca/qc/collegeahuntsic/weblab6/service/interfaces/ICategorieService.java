package ca.qc.collegeahuntsic.weblab6.service.interfaces;

import java.util.List;

import org.hibernate.Session;

import ca.qc.collegeahuntsic.weblab6.dto.CategorieDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab6.exception.dto.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab6.exception.service.CategorieAlreadyAddedException;
import ca.qc.collegeahuntsic.weblab6.exception.service.ServiceException;

public interface ICategorieService extends IService {

	List<CategorieDTO> getAllCategories(Session session,
			String sortByPropertyName) throws InvalidHibernateSessionException,
			InvalidSortByPropertyException, ServiceException;

	void deleteCategorie(Session session, CategorieDTO categorieDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			ServiceException;

	void updateCategorie(Session session, CategorieDTO categorieDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			ServiceException;

	CategorieDTO getCategorie(Session session, String idCategorie)
			throws InvalidHibernateSessionException,
			InvalidPrimaryKeyException, ServiceException;

	void addCategorie(Session session, CategorieDTO categorieDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			ServiceException;

	void ajouterCategorie(Session session, CategorieDTO categorieDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			CategorieAlreadyAddedException, ServiceException;
}
