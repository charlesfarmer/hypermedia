
package ca.qc.collegeahuntsic.weblab6;

import java.util.Set;
import ca.qc.collegeahuntsic.weblab6.dto.MarchandDTO;
import ca.qc.collegeahuntsic.weblab6.dto.MembreDTO;
import ca.qc.collegeahuntsic.weblab6.exception.dao.ApplicationException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidDTOException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidHibernateSessionException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.InvalidPrimaryKeyException;
import ca.qc.collegeahuntsic.weblab6.exception.dao.ServiceException;
import ca.qc.collegeahuntsic.weblab6.util.ApplicationCreateur;

public class Application {
    public static void main(String[] args) {
        try {

            ApplicationCreateur gestionApp = new ApplicationCreateur();

            gestionApp.beginTransaction();

            MembreDTO membreDTO = new MembreDTO();
            membreDTO.setUsername("Charles");
            membreDTO.setPassword("abcd1234");
            membreDTO.setEmail("200675851@collegeahuntsic.qc.ca");
            gestionApp.getMembreService().addMembre(gestionApp.getSession(),
                membreDTO);
            System.out.println("ajout membre réussi !");
            String id = membreDTO.getIdMembre();

            gestionApp.commitTransaction();

            //

            gestionApp.beginTransaction();

            MarchandDTO marchand = new MarchandDTO();
            marchand.setName("Oracle");
            marchand.setMembreDTO(membreDTO);
            marchand.setLogoURI("img/oracle");
            gestionApp.getMarchandService().addMarchand(gestionApp.getSession(),
                marchand);
            System.out.println("ajout marchand réussi !");

            gestionApp.commitTransaction();

            gestionApp.beginTransaction();

            membreDTO = gestionApp.getMembreService().getMembre(gestionApp.getSession(),
                id);
            Set<MarchandDTO> marchands = membreDTO.getMarchands();
            for(MarchandDTO m : marchands) {
                StringBuilder sb = new StringBuilder();
                sb.append(m.getIdMarchand()
                    + Constants.NEW_LINE);
                sb.append(m.getName()
                    + Constants.NEW_LINE);
                sb.append(m.getLogoURI()
                    + Constants.NEW_LINE);
                System.out.println(sb.toString()
                    + Constants.NEW_LINE);
            }

            gestionApp.commitTransaction();

        } catch(
            ApplicationException
            | InvalidHibernateSessionException
            | InvalidDTOException
            | ServiceException
            | InvalidPrimaryKeyException e) {
            e.printStackTrace();
        }
    }
}
