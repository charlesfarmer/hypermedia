
package ca.qc.collegeahuntsic.weblab6.dto;

public class LigneVitrineDTO extends DTO {

    private static final long serialVersionUID = 1L;

    public static final String ID_LIGNE_VITRINE_COLUMN_NAME = "IDLigneVitrine";

    public static final String PRODUIT_ID_COLUMN_NAME = "ProduitID";

    public static final String VITRINE_ID_COLUMN_NAME = "VitrineID";

    public static final String CATEGORIE_ID_COLUMN_NAME = "CategorieID";

    public static final String IS_PRIVATE_COLUMN_NAME = "IsPrivate";

    private String idLigneVitrine;

    private ProduitDTO produitDTO;

    private VitrineDTO vitrineDTO;

    private CategorieDTO categorieDTO;

    private Boolean isPrivate;

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

    public Boolean getIsPrivate() {
        return this.isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }
}
