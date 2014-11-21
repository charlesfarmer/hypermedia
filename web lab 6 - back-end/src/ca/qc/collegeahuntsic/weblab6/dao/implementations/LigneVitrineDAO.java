
package ca.qc.collegeahuntsic.weblab6.dao.implementations;

import ca.qc.collegeahuntsic.weblab6.dao.interfaces.ILigneVitrineDAO;
import ca.qc.collegeahuntsic.weblab6.dto.LigneVitrineDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.DAOException;

public class LigneVitrineDAO extends DAO implements ILigneVitrineDAO {

    LigneVitrineDAO(Class<LigneVitrineDTO> ligneVitrineDTOClass) throws DAOException {
        super(ligneVitrineDTOClass);
    }

}
