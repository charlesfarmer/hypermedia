
package ca.qc.collegeahuntsic.weblab6.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class LigneVitrineDTO extends DTO {

    private static final long serialVersionUID = 1L;

    public static final String ID_LIGNE_VITRINE_COLUMN_NAME = "idLigneVitrine";

    public static final String PRODUIT_ID_COLUMN_NAME = "produitId";

    public static final String VITRINE_ID_COLUMN_NAME = "vitrineId";

    public static final String CATEGORIE_ID_COLUMN_NAME = "categorieId";

    private String idLigneVitrine;

    private ProduitDTO produitDTO;

    private VitrineDTO vitrineDTO;

    private CategorieDTO categorieDTO;

    public LigneVitrineDTO() {
        super();
    }

    public String getIdLigneVitrine() {
        return this.idLigneVitrine;
    }

    public void setIdLigneVitrine(String idLigneVitrine) {
        this.idLigneVitrine = idLigneVitrine;
    }

    public ProduitDTO getProduitDTO() {
        return this.produitDTO;
    }

    public void setProduitDTO(ProduitDTO produitDTO) {
        this.produitDTO = produitDTO;
    }

    public VitrineDTO getVitrineDTO() {
        return this.vitrineDTO;
    }

    public void setVitrineDTO(VitrineDTO vitrineDTO) {
        this.vitrineDTO = vitrineDTO;
    }

    public CategorieDTO getCategorieDTO() {
        return this.categorieDTO;
    }

    public void setCategorieDTO(CategorieDTO categorieDTO) {
        this.categorieDTO = categorieDTO;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals = this == obj;
        if(!equals) {
            equals = obj != null
                && obj instanceof LigneVitrineDTO;
            if(equals) {
                final LigneVitrineDTO ligneVitrineDTO = (LigneVitrineDTO) obj;
                final EqualsBuilder equalsBuilder = new EqualsBuilder();
                equalsBuilder.appendSuper(super.equals(ligneVitrineDTO));
                equalsBuilder.append(getIdLigneVitrine(),
                    ligneVitrineDTO.getIdLigneVitrine());
                equals = equalsBuilder.isEquals();
            }
        }
        return equals;
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder hashCodeBuilder = new HashCodeBuilder(11,
            17);
        hashCodeBuilder.appendSuper(super.hashCode());
        hashCodeBuilder.append(getIdLigneVitrine());
        return hashCodeBuilder.toHashCode();
    }
}
