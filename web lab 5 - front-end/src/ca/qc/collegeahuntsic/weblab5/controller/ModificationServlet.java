
package ca.qc.collegeahuntsic.weblab5.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.bean.ProfilBean;
import ca.qc.collegeahuntsic.weblab5.exception.MagasinException;
import ca.qc.collegeahuntsic.weblab5.exception.facade.FacadeException;
import ca.qc.collegeahuntsic.weblab5.util.MagasinCreateur;

/**
 * Servlet implementation class ModificationServlet
 */
@WebServlet(
    name = "modificationProfil",
    urlPatterns = {"/profil/modification"})
public class ModificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        processRequest(request,
            response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        processRequest(request,
            response);
    }

    private void processRequest(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        ClientBean clientBean = (ClientBean) request.getSession(false).getAttribute("client");
        if(clientBean == null) {
            request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request,
                response);
        } else if(request.getParameter("miseajour") != null) {
            ProfilBean profil = clientBean.getProfilBean();
            System.out.println(profil);
            String nouveauPrenom = request.getParameter("nouveauPrenom");
            String nouveauNom = request.getParameter("nouveauNom");
            String password = request.getParameter("password");

            if(password == null
                || !password.equals(clientBean.getPassword())) {
                request.setAttribute("passwordInvalide",
                    "true");
            } else {
                if(nouveauPrenom != null) {
                    profil.setPrenom(nouveauPrenom);
                }
                if(nouveauNom != null) {
                    profil.setNom(nouveauNom);
                }
                MagasinCreateur magasin = (MagasinCreateur) getServletContext().getAttribute("magasin");
                try {
                    profil = magasin.getProfilFacade().modifierProfil(magasin.getConnexion(),
                        profil);
                    magasin.commit();
                    clientBean.setProfilBean(profil);
                    request.getSession().setAttribute("client",
                        clientBean);
                    request.setAttribute("modificationsReussies",
                        "true");
                } catch(
                    FacadeException
                    | MagasinException e) {
                    e.printStackTrace();
                    try {
                        magasin.rollback();
                    } catch(MagasinException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
        request.getRequestDispatcher("/WEB-INF/modification.jsp").forward(request,
            response);
    }

}
