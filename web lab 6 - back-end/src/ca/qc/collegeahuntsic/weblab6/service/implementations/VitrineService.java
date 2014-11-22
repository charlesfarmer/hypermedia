package ca.qc.collegeahuntsic.weblab6.service.implementations;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import ca.qc.collegeahuntsic.weblab6.dao.interfaces.IVitrineDAO;
import ca.qc.collegeahuntsic.weblab6.dto.VitrineDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidDAOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.ServiceException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.VitrineAlreadyAddedException;
import ca.qc.collegeahuntsic.weblab6.service.interfaces.IVitrineService;

public class VitrineService extends Service implements IVitrineService {

	private IVitrineDAO vitrineDAO;

	VitrineService(IVitrineDAO vitrineDAO) throws InvalidDAOException {
		super();
		if (vitrineDAO == null) {
			throw new InvalidDAOException("le dao ne peut être null");
		}
		setVitrineDAO(vitrineDAO);
	}

	public IVitrineDAO getVitrineDAO() {
		return this.vitrineDAO;
	}

	public void setVitrineDAO(IVitrineDAO vitrineDAO) {
		this.vitrineDAO = vitrineDAO;
	}

	@Override
	public void addVitrine(Session session, VitrineDTO vitrineDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			ServiceException {
		try {
			getVitrineDAO().add(session, vitrineDTO);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	@Override
	public VitrineDTO getVitrine(Session session, String idVitrine)
			throws InvalidHibernateSessionException,
			InvalidPrimaryKeyException, ServiceException {
		try {
			return (VitrineDTO) getVitrineDAO().get(session, idVitrine);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	@Override
	public void updateVitrine(Session session, VitrineDTO vitrineDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			ServiceException {
		try {
			getVitrineDAO().update(session, vitrineDTO);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	@Override
	public void deleteVitrine(Session session, VitrineDTO vitrineDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			ServiceException {
		try {
			getVitrineDAO().delete(session, vitrineDTO);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VitrineDTO> getAllVitrines(Session session,
			String sortByPropertyName) throws InvalidHibernateSessionException,
			InvalidSortByPropertyException, ServiceException {
		try {
			return (List<VitrineDTO>) getVitrineDAO().getAll(session,
					sortByPropertyName);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	@Override
	public void ajouterVitrine(Session session, VitrineDTO vitrineDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			VitrineAlreadyAddedException, ServiceException {
		Set<VitrineDTO> vitrines = vitrineDTO.getMembreDTO().getVitrines();
		for (VitrineDTO vitrine : vitrines) {
			if (vitrine.getTitle().equals(vitrineDTO.getTitle())) {
				throw new VitrineAlreadyAddedException();
			}
		}
		addVitrine(session, vitrineDTO);
	}
}
