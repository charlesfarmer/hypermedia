
package ca.qc.collegeahuntsic.weblab5.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MagasinSessionListener implements HttpSessionListener {

    private static int nbActiveSession;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        nbActiveSession++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        nbActiveSession--;
    }

    public static int getNbActiveSession() {
        return nbActiveSession;
    }

}
