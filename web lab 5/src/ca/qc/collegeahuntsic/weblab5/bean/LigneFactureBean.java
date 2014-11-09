package ca.qc.collegeahuntsic.weblab5.bean;

import java.math.BigDecimal;

public class LigneFactureBean extends Bean {

	public static final String ID_LIGNEFACTURE_COLUMN_NAME = "IDLigneFacture";

	public static final String ID_ACHAT_COLUMN_NAME = "AchatID";

	public static final String ID_PRODUIT_COLUMN_NAME = "ProduitID";

	public static final String QUANTITE_COLUMN_NAME = "Quantite";

	public static final String PRIX_COLUMN_NAME = "Prix";

	private static final long serialVersionUID = 1L;

	private String idLigneFacture;

	private int quantite;

	private BigDecimal prix;

	private AchatBean achatBean;

	private ProduitBean produitBean;

	public LigneFactureBean() {
		super();
	}

	public String getIdLigneFacture() {
		return this.idLigneFacture;
	}

	public void setIdLigneFacture(String idLigneFacture) {
		this.idLigneFacture = idLigneFacture;
	}

	public int getQuantite() {
		return this.quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public BigDecimal getPrix() {
		return this.prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public ProduitBean getProduitBean() {
		return this.produitBean;
	}

	public void setProduitBean(ProduitBean produitBean) {
		this.produitBean = produitBean;
	}

	public AchatBean getAchatBean() {
		return this.achatBean;
	}

	public void setAchatBean(AchatBean achatBean) {
		this.achatBean = achatBean;
	}
}
