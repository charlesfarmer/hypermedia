
package ca.qc.collegeahuntsic.weblab5.bean;

import java.sql.Timestamp;

public class AchatBean extends Bean {

    public static final String ID_ACHAT_COLUMN_NAME = "IDAchat";

    public static final String ID_CLIENT_COLUMN_NAME = "ClientID";

    public static final String DATE_ACHAT_COLUMN_NAME = "DateAchat";

    private static final long serialVersionUID = 1L;

    private String idAchat;

    private ClientBean clientBean;

    private Timestamp dateAchat;

    public AchatBean() {
        super();
    }

    public String getIdAchat() {
        return this.idAchat;
    }

    public void setIdAchat(String idAchat) {
        this.idAchat = idAchat;
    }

    public ClientBean getClientBean() {
        return this.clientBean;
    }

    public void setClientBean(ClientBean clientBean) {
        this.clientBean = clientBean;
    }

    public Timestamp getDateAchat() {
        return this.dateAchat;
    }

    public void setDateAchat(Timestamp dateAchat) {
        this.dateAchat = dateAchat;
    }
}
