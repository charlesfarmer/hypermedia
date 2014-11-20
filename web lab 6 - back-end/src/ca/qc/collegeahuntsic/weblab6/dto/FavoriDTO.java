
package ca.qc.collegeahuntsic.weblab6.dto;

public class FavoriDTO extends DTO {

    private static final long serialVersionUID = 1L;

    public static final String ID_FAVORI_COLUMN_NAME = "IDFavori";

    public static final String PRODUIT_ID_COLUMN_NAME = "ProduitID";

    public static final String MEMBRE_ID_COLUMN_NAME = "MembreID";

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
}
