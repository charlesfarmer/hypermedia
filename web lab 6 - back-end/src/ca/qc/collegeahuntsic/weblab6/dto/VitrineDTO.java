package ca.qc.collegeahuntsic.weblab6.dto;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

public class VitrineDTO extends DTO {

	private static final long serialVersionUID = 1L;

	public static final String ID_VITRINE_COLUMN_NAME = "IDVitrine";

	public static final String MEMBRE_ID_COLUMN_NAME = "MembreID";

	public static final String TITLE_COLUMN_NAME = "Title";

	public static final String DATE_ADDED_COLUMN_NAME = "DateAdded";

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
}
