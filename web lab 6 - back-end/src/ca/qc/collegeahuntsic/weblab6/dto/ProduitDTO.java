
package ca.qc.collegeahuntsic.weblab6.dto;

import java.math.BigDecimal;
import java.util.Set;

public class ProduitDTO extends DTO {

    private static final long serialVersionUID = 1L;

    public static final String ID_PRODUIT_COLUMN_NAME = "IDProduit";

    public static final String MARCHAND_ID_COLUMN_NAME = "MarchandID";

    public static final String TITLE_COLUMN_NAME = "Title";

    public static final String URL_COLUMN_NAME = "URL";

    public static final String PRICE_COLUMN_NAME = "Price";

    private String idProduit;

    private MarchandDTO marchandDTO;

    private String title;

    private String url;

    private BigDecimal price;

    private Set<LigneVitrineDTO> ligneVitrines;

    private Set<FavoriDTO> favoris;

    public ProduitDTO() {
        super();
    }

    public MarchandDTO getMarchandDTO() {
        return this.marchandDTO;
    }

    public void setMarchandDTO(MarchandDTO marchandDTO) {
        this.marchandDTO = marchandDTO;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<LigneVitrineDTO> getLigneVitrines() {
        return this.ligneVitrines;
    }

    public void setLigneVitrines(Set<LigneVitrineDTO> ligneVitrines) {
        this.ligneVitrines = ligneVitrines;
    }

    public Set<FavoriDTO> getFavoris() {
        return this.favoris;
    }

    public void setFavoris(Set<FavoriDTO> favoris) {
        this.favoris = favoris;
    }

    public String getIdProduit() {
        return this.idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }
}
