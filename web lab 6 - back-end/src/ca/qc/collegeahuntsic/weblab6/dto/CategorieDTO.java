
package ca.qc.collegeahuntsic.weblab6.dto;

import java.util.Collections;
import java.util.Set;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CategorieDTO extends DTO {

    private static final long serialVersionUID = 1L;

    public static final String ID_CATEGORIE_COLUMN_NAME = "idCategorie";

    public static final String MEMBRE_ID_COLUMN_NAME = "membreId";

    public static final String NAME_COLUMN_NAME = "name";

    private String idCategorie;

    private MembreDTO membreDTO;

    private String name;

    private Set<LigneVitrineDTO> ligneVitrines;

    public CategorieDTO() {
        super();
        setLigneVitrines(Collections.<LigneVitrineDTO> emptySet());
    }

    public String getIdCategorie() {
        return this.idCategorie;
    }

    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<LigneVitrineDTO> getLigneVitrines() {
        return this.ligneVitrines;
    }

    public void setLigneVitrines(Set<LigneVitrineDTO> ligneVitrines) {
        this.ligneVitrines = ligneVitrines;
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
                && obj instanceof CategorieDTO;
            if(equals) {
                final CategorieDTO categorieDTO = (CategorieDTO) obj;
                final EqualsBuilder equalsBuilder = new EqualsBuilder();
                equalsBuilder.appendSuper(super.equals(categorieDTO));
                equalsBuilder.append(getIdCategorie(),
                    categorieDTO.getIdCategorie());
                equals = equalsBuilder.isEquals();
            }
        }
        return equals;
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder hashCodeBuilder = new HashCodeBuilder(37,
            43);
        hashCodeBuilder.appendSuper(super.hashCode());
        hashCodeBuilder.append(getIdCategorie());
        return hashCodeBuilder.toHashCode();
    }
}
