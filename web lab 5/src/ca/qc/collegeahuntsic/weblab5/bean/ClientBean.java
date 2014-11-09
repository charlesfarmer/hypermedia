package ca.qc.collegeahuntsic.weblab5.bean;

public class ClientBean extends Bean {

	public static final String ID_CLIENT_COLUMN_NAME = "IDClient";

	public static final String EMAIL_COLUMN_NAME = "Email";

	public static final String PASSWORD_COLUMN_NAME = "Passwd";

	public static final String ID_PROFIL_COLUMN_NAME = "ProfilID";

	private static final long serialVersionUID = 1L;

	private String idClient;

	private String email;

	private String password;

	private ProfilBean profilBean;

	public ClientBean() {
		super();
	}

	public String getIdClient() {
		return idClient;
	}

	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ProfilBean getProfilBean() {
		return profilBean;
	}

	public void setProfilBean(ProfilBean profilBean) {
		this.profilBean = profilBean;
	}
}
