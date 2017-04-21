package servlets;

import dao.DAOFactory;
import dao.InterfaceDao.ProfesseurDao;
import beans.ProfesseurEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Sylvain on 21/04/2017.
 */
@WebServlet(name = "Professeurs")
public class Professeurs extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";

    private ProfesseurDao profDao;
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.profDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY )).getProfesseurDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mail = request.getParameter("mail");
        if(mail==null){
            List<ProfesseurEntity> listProf = profDao.findAll();
            request.setAttribute("listeProf", listProf);
            request.getRequestDispatcher("professeur.jsp").forward(request, response);
        }
        else {
            ProfesseurEntity prof = profDao.find(mail);
            request.setAttribute("prof", prof);
            request.getRequestDispatcher("professeur.jsp").forward(request, response);
        }
    }
}
