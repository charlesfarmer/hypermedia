
package ca.qc.collegeahuntsic.weblab6.dto;

import java.util.Collections;
import java.util.Set;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class MarchandDTO extends DTO {

    private static final long serialVersionUID = 1L;

    public static final String ID_MARCHAND_COLUMN_NAME = "idMarchand";

    public static final String MEMBRE_ID_COLUMN_NAME = "membreId";

    public static final String NAME_COLUMN_NAME = "name";

    public static final String LOGO_URI_COLUMN_NAME = "logoURI";

    private String idMarchand;

    private MembreDTO membreDTO;

    private String name;

    private String logoURI;

    private Set<ProduitDTO> produits;

    public MarchandDTO() {
        super();
        setProduits(Collections.<ProduitDTO> emptySet());
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

    @Override
    public boolean equals(Object obj) {
        boolean equals = this == obj;
        if(!equals) {
            equals = obj != null
                && obj instanceof MarchandDTO;
            if(equals) {
                final MarchandDTO marchandDTO = (MarchandDTO) obj;
                final EqualsBuilder equalsBuilder = new EqualsBuilder();
                equalsBuilder.appendSuper(super.equals(marchandDTO));
                equalsBuilder.append(getIdMarchand(),
                    marchandDTO.getIdMarchand());
                equals = equalsBuilder.isEquals();
            }
        }
        return equals;
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder hashCodeBuilder = new HashCodeBuilder(13,
            19);
        hashCodeBuilder.appendSuper(super.hashCode());
        hashCodeBuilder.append(getIdMarchand());
        return hashCodeBuilder.toHashCode();
    }
}
