package servlet.adminServlets;

import model.Artist;
import org.javalite.activejdbc.Base;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class AdminArtistsOverview extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //db connection duh
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");

        System.out.println("doget in artistsoverview");
    
        List<Artist> artists = Artist.findAll();
    
        Iterator arIter = artists.iterator();
        while (arIter.hasNext()){
            Artist a = (Artist)arIter.next();
            System.out.println(a.get("artist_name") + " " + a.get("first_name") + " " + a.get("last_name") + " ");

        }
        
        RequestDispatcher rd = req.getRequestDispatcher("/adminAvailableArtists.jsp");
        Base.close();
        
        req.setAttribute("artists", artists);

        rd.forward(req, resp);
    }
}
