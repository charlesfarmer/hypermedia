package ca.qc.collegeahuntsic.weblab6.service.implementations;

import java.util.List;

import org.hibernate.Session;

import ca.qc.collegeahuntsic.weblab6.dao.interfaces.IProduitDAO;
import ca.qc.collegeahuntsic.weblab6.dto.ProduitDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.DAOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidDAOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidSortByPropertyException;
import ca.qc.collegeahuntsic.weblab6.exception.dto.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab6.exception.service.ProduitAlreadyAddedException;
import ca.qc.collegeahuntsic.weblab6.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab6.service.interfaces.IProduitService;

public class ProduitService extends Service implements IProduitService {

	private IProduitDAO produitDAO;

	ProduitService(IProduitDAO produitDAO) throws InvalidDAOException {
		super();
		if (produitDAO == null) {
			throw new InvalidDAOException("le dao ne peut être null");
		}
		setProduitDAO(produitDAO);
	}

	public IProduitDAO getProduitDAO() {
		return this.produitDAO;
	}

	public void setProduitDAO(IProduitDAO produitDAO) {
		this.produitDAO = produitDAO;
	}

	@Override
	public void addProduit(Session session, ProduitDTO produitDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			ServiceException {
		try {
			getProduitDAO().add(session, produitDTO);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	@Override
	public ProduitDTO getProduit(Session session, String idProduit)
			throws InvalidHibernateSessionException,
			InvalidPrimaryKeyException, ServiceException {
		try {
			return (ProduitDTO) getProduitDAO().get(session, idProduit);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	@Override
	public void updateProduit(Session session, ProduitDTO produitDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			ServiceException {
		try {
			getProduitDAO().update(session, produitDTO);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	@Override
	public void deleteProduit(Session session, ProduitDTO produitDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			ServiceException {
		try {
			getProduitDAO().delete(session, produitDTO);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProduitDTO> getAllProduits(Session session,
			String sortByPropertyName) throws InvalidHibernateSessionException,
			InvalidSortByPropertyException, ServiceException {
		try {
			return (List<ProduitDTO>) getProduitDAO().getAll(session,
					sortByPropertyName);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void ajouterProduit(Session session, ProduitDTO produitDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			ProduitAlreadyAddedException, ServiceException {
		List<ProduitDTO> produits = (List<ProduitDTO>) produitDTO
				.getMarchandDTO().getProduits();
		for (ProduitDTO produit : produits) {
			if (produit.getIdProduit().equals(produitDTO.getIdProduit())) {
				throw new ProduitAlreadyAddedException();
			}
		}
		addProduit(session, produitDTO);
	}

}
