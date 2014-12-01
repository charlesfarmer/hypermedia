
package ca.qc.collegeahuntsic.weblab6.dto;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class VitrineDTO extends DTO {

    private static final long serialVersionUID = 1L;

    public static final String ID_VITRINE_COLUMN_NAME = "idVitrine";

    public static final String MEMBRE_ID_COLUMN_NAME = "membreId";

    public static final String TITLE_COLUMN_NAME = "title";

    public static final String DATE_ADDED_COLUMN_NAME = "dateAdded";

    private String idVitrine;

    private MembreDTO membreDTO;

    private String title;

    private Timestamp dateAdded;

    private Set<LigneVitrineDTO> ligneVitrines;

    public VitrineDTO() {
        super();
        setLigneVitrines(Collections.<LigneVitrineDTO> emptySet());
    }

    public String getIdVitrine() {
        return this.idVitrine;
    }

    public void setIdVitrine(String idVitrine) {
        this.idVitrine = idVitrine;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getDateAdded() {
        return this.dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded) {
        this.dateAdded = dateAdded;
    }

    public MembreDTO getMembreDTO() {
        return this.membreDTO;
    }

    public void setMembreDTO(MembreDTO membreDTO) {
        this.membreDTO = membreDTO;
    }

    public Set<LigneVitrineDTO> getLigneVitrines() {
        return this.ligneVitrines;
    }

    public void setLigneVitrines(Set<LigneVitrineDTO> ligneVitrines) {
        this.ligneVitrines = ligneVitrines;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals = this == obj;
        if(!equals) {
            equals = obj != null
                && obj instanceof VitrineDTO;
            if(equals) {
                final VitrineDTO vitrineDTO = (VitrineDTO) obj;
                final EqualsBuilder equalsBuilder = new EqualsBuilder();
                equalsBuilder.appendSuper(super.equals(vitrineDTO));
                equalsBuilder.append(getIdVitrine(),
                    vitrineDTO.getIdVitrine());
                equals = equalsBuilder.isEquals();
            }
        }
        return equals;
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder hashCodeBuilder = new HashCodeBuilder(31,
            37);
        hashCodeBuilder.appendSuper(super.hashCode());
        hashCodeBuilder.append(getIdVitrine());
        return hashCodeBuilder.toHashCode();
    }
}
