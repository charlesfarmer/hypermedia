
package ca.qc.collegeahuntsic.weblab6.dto;

import java.math.BigDecimal;
import java.util.Set;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ProduitDTO extends DTO {

    private static final long serialVersionUID = 1L;

    public static final String ID_PRODUIT_COLUMN_NAME = "idProduit";

    public static final String MARCHAND_ID_COLUMN_NAME = "marchandId";

    public static final String TITLE_COLUMN_NAME = "title";

    public static final String URL_COLUMN_NAME = "url";

    public static final String PRICE_COLUMN_NAME = "price";

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

    @Override
    public boolean equals(Object obj) {
        boolean equals = this == obj;
        if(!equals) {
            equals = obj != null
                && obj instanceof ProduitDTO;
            if(equals) {
                final ProduitDTO produitDTO = (ProduitDTO) obj;
                final EqualsBuilder equalsBuilder = new EqualsBuilder();
                equalsBuilder.appendSuper(super.equals(produitDTO));
                equalsBuilder.append(getIdProduit(),
                    produitDTO.getIdProduit());
                equals = equalsBuilder.isEquals();
            }
        }
        return equals;
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder hashCodeBuilder = new HashCodeBuilder(23,
            29);
        hashCodeBuilder.appendSuper(super.hashCode());
        hashCodeBuilder.append(getIdProduit());
        return hashCodeBuilder.toHashCode();
    }
}
