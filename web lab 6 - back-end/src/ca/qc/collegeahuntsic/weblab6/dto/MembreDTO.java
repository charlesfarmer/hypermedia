
package ca.qc.collegeahuntsic.weblab6.dto;

import java.util.Collections;
import java.util.Set;

public class MembreDTO extends DTO {

    private static final long serialVersionUID = 1L;

    public static final String ID_MEMBRE_COLUMN_NAME = "IDMembre";

    public static final String USERNAME_COLUMN_NAME = "Username";

    public static final String PASSWORD_COLUMN_NAME = "Password";

    public static final String EMAIL_COLUMN_NAME = "Email";

    private String idMembre;

    private String username;

    private String password;

    private String email;

    private Set<VitrineDTO> vitrines;

    private Set<CategorieDTO> categories;

    private Set<FavoriDTO> favoris;

    private Set<MarchandDTO> marchands;

    public MembreDTO() {
        super();
        setVitrines(Collections.EMPTY_SET);
        setCategories(Collections.EMPTY_SET);
        setFavoris(Collections.EMPTY_SET);
        setMarchands(Collections.EMPTY_SET);
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

    public Set<CategorieDTO> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<CategorieDTO> categories) {
        this.categories = categories;
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
}
