
package ca.qc.collegeahuntsic.weblab6.dto;

import java.util.Collections;
import java.util.Set;

public class MarchandDTO extends DTO {

    private static final long serialVersionUID = 1L;

    public static final String ID_MARCHAND_COLUMN_NAME = "IDMarchand";

    public static final String MEMBRE_ID_COLUMN_NAME = "MembreID";

    public static final String NAME_COLUMN_NAME = "Name";

    public static final String LOGO_URI_COLUMN_NAME = "LogoURI";

    private String idMarchand;

    private MembreDTO membreDTO;

    private String name;

    private String logoURI;

    private Set<ProduitDTO> produits;

    public MarchandDTO() {
        super();
        setProduits(Collections.EMPTY_SET);
    }

    public String getIdMarchand() {
        return this.idMarchand;
    }

    public void setIdMarchand(String idMarchand) {
        this.idMarchand = idMarchand;
    }

    public MembreDTO getMembreDTO() {
        return this.membreDTO;
    }

    public void setMembreDTO(MembreDTO membreDTO) {
        this.membreDTO = membreDTO;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoURI() {
        return this.logoURI;
    }

    public void setLogoURI(String logoURI) {
        this.logoURI = logoURI;
    }

    public Set<ProduitDTO> getProduits() {
        return this.produits;
    }

    public void setProduits(Set<ProduitDTO> produits) {
        this.produits = produits;
    }
}
