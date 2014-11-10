
package labo2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
    name = "Inscription",
    urlPatterns = {"/inscription"})
public class Inscription extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void processRequest(HttpServletRequest request,
        HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE);
        if(Integer.parseInt(request.getParameter("flag")) == 0) {
            chargerUtilisateurExistant(request,
                response);
        } else if(Integer.parseInt(request.getParameter("flag")) == 1) {
            inscrireNouvelUsager(request,
                response);
        } else {
            HttpSession session = request.getSession(false);
            if(session != null) {
                session.invalidate();//request.getRequestDispatcher("servletlab2").forward(request, response);
            }
            response.sendRedirect("servletlab2");
        }
    }

    @Override
    public void doGet(HttpServletRequest request,
        HttpServletResponse response) throws IOException {
        processRequest(request,
            response);
    }

    @Override
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws IOException {
        processRequest(request,
            response);
    }

    private void inscrireNouvelUsager(HttpServletRequest request,
        HttpServletResponse response) {
        String pw = request.getParameter("motdepasse");
        String pw2 = request.getParameter("motdepasse2");
        String id = request.getParameter("nom");
        try {
            BufferedReader br = new BufferedReader(new FileReader(getServletContext().getRealPath("/Users.txt")));
            HashMap<String, String> hm = new HashMap<>();
            String[] arr;
            String s = br.readLine();
            while(!(s == null)) {
                arr = s.split(",");
                hm.put(arr[0],
                    arr[1]);
                s = br.readLine();
            }
            br.close();
            if(!pw.equals(pw2)) {
                response.sendRedirect("login?failPasswords");
            } else if(hm.containsKey(id)) {
                response.sendRedirect("login?failRegister");
            } else {
                registerUser(pw,
                    id);
                createSessionAndRedirectToMainPage(request,
                    response,
                    id);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void registerUser(String pw,
        String id) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(getServletContext().getRealPath("/Users.txt"),
            true));
        bw.write(id
            + ","
            + pw);
        bw.flush();
        bw.close();
    }

    private void chargerUtilisateurExistant(HttpServletRequest request,
        HttpServletResponse response) {
        String pw = request.getParameter("password");
        String id = request.getParameter("username");
        try {
            BufferedReader br = new BufferedReader(new FileReader(getServletContext().getRealPath("/Users.txt")));
            HashMap<String, String> hm = new HashMap<>();
            String[] arr;
            String s = br.readLine();
            while(!(s == null)) {
                arr = s.split(",");
                hm.put(arr[0],
                    arr[1]);
                s = br.readLine();
            }
            br.close();
            if(!hm.containsKey(id)
                || !hm.get(id).equals(pw)) {
                response.sendRedirect("login?failConnect");
            } else {
                createSessionAndRedirectToMainPage(request,
                    response,
                    id);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void createSessionAndRedirectToMainPage(HttpServletRequest request,
        HttpServletResponse response,
        String user) throws IOException {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(60);
        response.sendRedirect("servletlab2?user="
            + user);
    }
}
