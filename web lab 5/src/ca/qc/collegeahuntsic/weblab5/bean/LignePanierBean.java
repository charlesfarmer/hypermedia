package ca.qc.collegeahuntsic.weblab5.bean;

public class LignePanierBean extends Bean {

	public static final String ID_LIGNE_PANIER_COLUMN_NAME = "IDLignePanier";

	public static final String ID_CLIENT_COLUMN_NAME = "ClientID";

	public static final String ID_PRODUIT_COLUMN_NAME = "ProduitID";

	public static final String QUANTITE_COLUMN_NAME = "Quantite";

	private static final long serialVersionUID = 1L;

	private String idLignePanier;

	private ClientBean clientBean;

	private ProduitBean produitBean;

	private int quantite;

	public LignePanierBean() {
		super();
	}

	public String getIdLignePanier() {
		return idLignePanier;
	}

	public void setIdLignePanier(String idLignePanier) {
		this.idLignePanier = idLignePanier;
	}
	
	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public ClientBean getClientBean() {
		return clientBean;
	}

	public void setClientBean(ClientBean clientBean) {
		this.clientBean = clientBean;
	}
	
	public ProduitBean getProduitBean() {
		return produitBean;
	}

	public void setProduitBean(ProduitBean produitBean) {
		this.produitBean = produitBean;
	}

}
