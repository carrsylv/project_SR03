package servlets;

import dao.DAOFactory;
import dao.InterfaceDao.UvDao;
import beans.UvEntity;

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
@WebServlet(name = "Uvs")
public class Uvs extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";

    private UvDao uvDao;
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.uvDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY )).getUvDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String identifiant = request.getParameter("identifiant");
        if(identifiant==null){
            List<UvEntity> listUv = uvDao.findAll();
            request.setAttribute("listeUv", listUv);
            request.getRequestDispatcher("uv.jsp").forward(request, response);
        }
        else {
            UvEntity uv = uvDao.find(identifiant);
            request.setAttribute("uv", uv);
            request.getRequestDispatcher("uv.jsp").forward(request, response);
        }
    }
}
