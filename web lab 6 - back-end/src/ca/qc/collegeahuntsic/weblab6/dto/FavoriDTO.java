
package ca.qc.collegeahuntsic.weblab6.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class FavoriDTO extends DTO {

    private static final long serialVersionUID = 1L;

    public static final String ID_FAVORI_COLUMN_NAME = "idFavori";

    public static final String PRODUIT_ID_COLUMN_NAME = "produitId";

    public static final String MEMBRE_ID_COLUMN_NAME = "membreId";

    private String idFavori;

    private ProduitDTO produitDTO;

    private MembreDTO membreDTO;

    public FavoriDTO() {
        super();
    }

    public String getIdFavori() {
        return this.idFavori;
    }

    public void setIdFavori(String idFavori) {
        this.idFavori = idFavori;
    }

    public ProduitDTO getProduitDTO() {
        return this.produitDTO;
    }

    public void setProduitDTO(ProduitDTO produitDTO) {
        this.produitDTO = produitDTO;
    }

    public MembreDTO getMembreDTO() {
        return this.membreDTO;
    }

    public void setMembreDTO(MembreDTO membreDTO) {
        this.membreDTO = membreDTO;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals = this == obj;
        if(!equals) {
            equals = obj != null
                && obj instanceof FavoriDTO;
            if(equals) {
                final FavoriDTO favoriDTO = (FavoriDTO) obj;
                final EqualsBuilder equalsBuilder = new EqualsBuilder();
                equalsBuilder.appendSuper(super.equals(favoriDTO));
                equalsBuilder.append(getIdFavori(),
                    favoriDTO.getIdFavori());
                equals = equalsBuilder.isEquals();
            }
        }
        return equals;
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder hashCodeBuilder = new HashCodeBuilder(7,
            13);
        hashCodeBuilder.appendSuper(super.hashCode());
        hashCodeBuilder.append(getIdFavori());
        return hashCodeBuilder.toHashCode();
    }
}
