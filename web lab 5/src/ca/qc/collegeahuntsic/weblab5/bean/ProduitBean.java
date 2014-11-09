
package ca.qc.collegeahuntsic.weblab5.bean;

public class ProduitBean extends Bean {

    public static final String ID_PRODUIT_COLUMN_NAME = "IDProduit";

    public static final String NOM_COLUMN_NAME = "Nom";

    public static final String DESCRIPTION_COLUMN_NAME = "Description";

    public static final String ID_STOCK_COLUMN_NAME = "StockID";

    private static final long serialVersionUID = 1L;

    private String idProduit;

    private String nom;

    private String description;

    private StockBean stockBean;

    public ProduitBean() {
        super();
    }

    public String getIdProduit() {
        return this.idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StockBean getStockBean() {
        return this.stockBean;
    }

    public void setStockBean(StockBean stockBean) {
        this.stockBean = stockBean;
    }

}
