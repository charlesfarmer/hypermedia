
package ca.qc.collegeahuntsic.weblab6.dto;

import java.util.Collections;
import java.util.Set;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class MembreDTO extends DTO {

    private static final long serialVersionUID = 1L;

    public static final String ID_MEMBRE_COLUMN_NAME = "idMembre";

    public static final String USERNAME_COLUMN_NAME = "username";

    public static final String PASSWORD_COLUMN_NAME = "password";

    public static final String EMAIL_COLUMN_NAME = "email";

    private String idMembre;

    private String username;

    private String password;

    private String email;

    private Set<VitrineDTO> vitrines;

    private Set<FavoriDTO> favoris;

    private Set<MarchandDTO> marchands;

    public MembreDTO() {
        super();
        setVitrines(Collections.<VitrineDTO> emptySet());
        setFavoris(Collections.<FavoriDTO> emptySet());
        setMarchands(Collections.<MarchandDTO> emptySet());
    }

    public String getIdMembre() {
        return this.idMembre;
    }

    public void setIdMembre(String idMembre) {
        this.idMembre = idMembre;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<VitrineDTO> getVitrines() {
        return this.vitrines;
    }

    public void setVitrines(Set<VitrineDTO> vitrines) {
        this.vitrines = vitrines;
    }

    public Set<FavoriDTO> getFavoris() {
        return this.favoris;
    }

    public void setFavoris(Set<FavoriDTO> favoris) {
        this.favoris = favoris;
    }

    public Set<MarchandDTO> getMarchands() {
        return this.marchands;
    }

    public void setMarchands(Set<MarchandDTO> marchands) {
        this.marchands = marchands;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals = this == obj;
        if(!equals) {
            equals = obj != null
                && obj instanceof MembreDTO;
            if(equals) {
                final MembreDTO membreDTO = (MembreDTO) obj;
                final EqualsBuilder equalsBuilder = new EqualsBuilder();
                equalsBuilder.appendSuper(super.equals(membreDTO));
                equalsBuilder.append(getIdMembre(),
                    membreDTO.getIdMembre());
                equals = equalsBuilder.isEquals();
            }
        }
        return equals;
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder hashCodeBuilder = new HashCodeBuilder(17,
            23);
        hashCodeBuilder.appendSuper(super.hashCode());
        hashCodeBuilder.append(getIdMembre());
        return hashCodeBuilder.toHashCode();
    }
}
