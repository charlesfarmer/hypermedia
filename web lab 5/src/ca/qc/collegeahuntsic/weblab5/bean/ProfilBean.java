package ca.qc.collegeahuntsic.weblab5.bean;

public class ProfilBean extends Bean {

	public static final String ID_PROFIL_COLUMN_NAME = "IDProfil";

	public static final String NOM_COLUMN_NAME = "Nom";

	public static final String PRENOM_COLUMN_NAME = "Prenom";

	private static final long serialVersionUID = 1L;

	private String idProfil;

	private String nom;

	private String prenom;

	public ProfilBean() {
		super();
	}

	public String getIdProfil() {
		return idProfil;
	}

	public void setIdProfil(String idProfil) {
		this.idProfil = idProfil;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}
