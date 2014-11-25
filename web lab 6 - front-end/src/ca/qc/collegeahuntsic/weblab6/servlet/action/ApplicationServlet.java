package ca.qc.collegeahuntsic.weblab6.servlet.action;

import javax.servlet.http.HttpServlet;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ca.qc.collegeahuntsic.weblab6.exception.dao.ApplicationException;
import ca.qc.collegeahuntsic.weblab6.service.interfaces.ICategorieService;
import ca.qc.collegeahuntsic.weblab6.service.interfaces.IFavoriService;
import ca.qc.collegeahuntsic.weblab6.service.interfaces.ILigneVitrineService;
import ca.qc.collegeahuntsic.weblab6.service.interfaces.IMarchandService;
import ca.qc.collegeahuntsic.weblab6.service.interfaces.IMembreService;
import ca.qc.collegeahuntsic.weblab6.service.interfaces.IProduitService;
import ca.qc.collegeahuntsic.weblab6.service.interfaces.IVitrineService;

public class ApplicationServlet extends HttpServlet {

	private static final String APPLICATION_CONTEXT_JDBC_FILENAME = "applicationContext-JDBC.xml";

	private static final String APPLICATION_CONTEXT_DTO_FILENAME = "applicationContext-DTO.xml";

	private static final String APPLICATION_CONTEXT_DAO_FILENAME = "applicationContext-DAO.xml";

	private static final String APPLICATION_CONTEXT_SERVICE_FILENAME = "applicationContext-Service.xml";

	private static final String APPLICATION_CONTEXT_FILENAME = "applicationContext.xml";

	private static final String SESSION_FACTORY_NAME = "sessionFactory";

	private static final String CATEGORIE_SERVICE_NAME = "categorieService";

	private static final String FAVORI_SERVICE_NAME = "favoriService";

	private static final String LIGNE_VITRINE_SERVICE_NAME = "ligneVitrineService";

	private static final String MARCHAND_SERVICE_NAME = "marchandService";

	private static final String MEMBRE_SERVICE_NAME = "membreService";

	private static final String PRODUIT_SERVICE_NAME = "produitService";

	private static final String VITRINE_SERVICE_NAME = "vitrineService";

	private static final String[] APPLICATION_CONTEXT_FILENAMES = new String[] {
			APPLICATION_CONTEXT_JDBC_FILENAME,
			APPLICATION_CONTEXT_DTO_FILENAME, APPLICATION_CONTEXT_DAO_FILENAME,
			APPLICATION_CONTEXT_SERVICE_FILENAME, APPLICATION_CONTEXT_FILENAME };

	private static final ApplicationContext APPLICATION_CONTEXT = new ClassPathXmlApplicationContext(
			APPLICATION_CONTEXT_FILENAMES);

	private SessionFactory sessionFactory;

	private Session session;

	private Transaction transaction;

	private ICategorieService categorieService;

	private IFavoriService favoriService;

	private ILigneVitrineService ligneVitrineService;

	private IMarchandService marchandService;

	private IMembreService membreService;

	private IProduitService produitService;

	private IVitrineService vitrineService;

	public ApplicationServlet() throws ApplicationException {
		super();
		try {
			setSessionFactory((SessionFactory) ApplicationServlet.APPLICATION_CONTEXT
					.getBean(ApplicationServlet.SESSION_FACTORY_NAME));
			setCategorieService((ICategorieService) ApplicationServlet.APPLICATION_CONTEXT
					.getBean(ApplicationServlet.CATEGORIE_SERVICE_NAME));
			setFavoriService((IFavoriService) ApplicationServlet.APPLICATION_CONTEXT
					.getBean(ApplicationServlet.FAVORI_SERVICE_NAME));
			setLigneVitrineService((ILigneVitrineService) ApplicationServlet.APPLICATION_CONTEXT
					.getBean(ApplicationServlet.LIGNE_VITRINE_SERVICE_NAME));
			setMarchandService((IMarchandService) ApplicationServlet.APPLICATION_CONTEXT
					.getBean(ApplicationServlet.MARCHAND_SERVICE_NAME));
			setMembreService((IMembreService) ApplicationServlet.APPLICATION_CONTEXT
					.getBean(ApplicationServlet.MEMBRE_SERVICE_NAME));
			setVitrineService((IVitrineService) ApplicationServlet.APPLICATION_CONTEXT
					.getBean(ApplicationServlet.VITRINE_SERVICE_NAME));
			setProduitService((IProduitService) ApplicationServlet.APPLICATION_CONTEXT
					.getBean(ApplicationServlet.PRODUIT_SERVICE_NAME));
		} catch (BeansException beansException) {
			throw new ApplicationException(beansException);
		}
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	private void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.session;
	}

	private void setSession(Session session) {
		this.session = session;
	}

	public Transaction getTransaction() {
		return this.transaction;
	}

	private void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public ICategorieService getCategorieService() {
		return this.categorieService;
	}

	private void setCategorieService(ICategorieService categorieService) {
		this.categorieService = categorieService;
	}

	public IFavoriService getFavoriService() {
		return this.favoriService;
	}

	private void setFavoriService(IFavoriService favoriService) {
		this.favoriService = favoriService;
	}

	public ILigneVitrineService getLigneVitrineService() {
		return this.ligneVitrineService;
	}

	private void setLigneVitrineService(ILigneVitrineService ligneVitrineService) {
		this.ligneVitrineService = ligneVitrineService;
	}

	public IMarchandService getMarchandService() {
		return this.marchandService;
	}

	private void setMarchandService(IMarchandService marchandService) {
		this.marchandService = marchandService;
	}

	public IMembreService getMembreService() {
		return this.membreService;
	}

	private void setMembreService(IMembreService membreService) {
		this.membreService = membreService;
	}

	public IVitrineService getVitrineService() {
		return this.vitrineService;
	}

	private void setVitrineService(IVitrineService vitrineService) {
		this.vitrineService = vitrineService;
	}

	public IProduitService getProduitService() {
		return this.produitService;
	}

	private void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}

	/**
	 * Ouvre une session Hibernate.
	 * 
	 * @return La session Hibernate
	 * @throws ApplicationException
	 *             S'il y a une erreur
	 */
	private Session openSession() throws ApplicationException {
		try {
			setSession(getSessionFactory().openSession());
		} catch (HibernateException hibernateException) {
			throw new ApplicationException(hibernateException);
		}
		return getSession();
	}

	/**
	 * Ferme une session Hibernate.
	 * 
	 * @throws ApplicationException
	 *             S'il y a une erreur
	 */
	private void closeSession() throws ApplicationException {
		try {
			getSession().close();
		} catch (HibernateException hibernateException) {
			throw new ApplicationException(hibernateException);
		}
	}

	/**
	 * Démarre une transaction.
	 * 
	 * @throws ApplicationException
	 *             S'il y a une erreur
	 */
	public void beginTransaction() throws ApplicationException {
		try {
			setTransaction(openSession().beginTransaction());
		} catch (HibernateException hibernateException) {
			throw new ApplicationException(hibernateException);
		}
	}

	/**
	 * Commit une transaction.
	 * 
	 * @throws ApplicationException
	 *             S'il y a une erreur
	 */
	public void commitTransaction() throws ApplicationException {
		try {
			getTransaction().commit();
			closeSession();
		} catch (HibernateException hibernateException) {
			throw new ApplicationException(hibernateException);
		}
	}

	/**
	 * Rollback une transaction.
	 * 
	 * @throws ApplicationException
	 *             S'il y a une erreur
	 */
	public void rollbackTransaction() throws ApplicationException {
		try {
			getTransaction().rollback();
			closeSession();
		} catch (HibernateException hibernateException) {
			throw new ApplicationException(hibernateException);
		}
	}

}
