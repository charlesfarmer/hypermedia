
package ca.qc.collegeahuntsic.weblab6;

import ca.qc.collegeahuntsic.weblab6.dto.MembreDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.ApplicationException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.ServiceException;
import ca.qc.collegeahuntsic.weblab6.utils.ApplicationCreateur;

public class Application {
    public static void main(String[] args) {
        try {
            ApplicationCreateur gestionApp = new ApplicationCreateur();
            gestionApp.beginTransaction();
            MembreDTO membreDTO = new MembreDTO();
            membreDTO.setUsername("Charles");
            membreDTO.setPassword("abcd1234");
            membreDTO.setEmail("200675851@collegeahuntsic.qc.ca");
            System.out.println("ajout réussi !");
            gestionApp.getMembreService().addMembre(gestionApp.getSession(),
                membreDTO);
            gestionApp.commitTransaction();
        } catch(
            ApplicationException
            | InvalidHibernateSessionException
            | InvalidDTOException
            | ServiceException e) {
            e.printStackTrace();
        }
    }
}
