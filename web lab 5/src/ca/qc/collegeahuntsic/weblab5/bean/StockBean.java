
package ca.qc.collegeahuntsic.weblab5.bean;

import java.math.BigDecimal;

public class StockBean extends Bean {

    public static final String ID_STOCK_COLUMN_NAME = "IDStock";

    public static final String QUANTITE_COLUMN_NAME = "Quantite";

    public static final String PRIX_COLUMN_NAME = "Prix";

    public static final String RABAIS_COLUMN_NAME = "Rabais";

    private static final long serialVersionUID = 1L;

    private String idStock;

    private int quantite;

    private BigDecimal prix;

    private BigDecimal rabais;

    public StockBean() {
        super();
    }

    public String getIdStock() {
        return this.idStock;
    }

    public void setIdStock(String idStock) {
        this.idStock = idStock;
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

    public BigDecimal getRabais() {
        return this.rabais;
    }

    public void setRabais(BigDecimal rabais) {
        this.rabais = rabais;
    }
}
