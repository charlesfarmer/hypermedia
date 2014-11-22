package ca.qc.collegeahuntsic.weblab6.dto;

import java.util.Collections;
import java.util.Set;

public class CategorieDTO extends DTO {

	private static final long serialVersionUID = 1L;

	public static final String ID_CATEGORIE_COLUMN_NAME = "IDCategorie";

	public static final String MEMBRE_ID_COLUMN_NAME = "MembreID";

	public static final String NAME_COLUMN_NAME = "Name";

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
}
