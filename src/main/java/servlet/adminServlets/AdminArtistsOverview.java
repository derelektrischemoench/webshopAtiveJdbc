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
        System.out.println("doget in adminartistsoverview");
        
        //db connection duh
        try {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
            System.out.println("successfully made connection");
            List<Artist> artists = Artist.findAll();
            req.setAttribute("artists", artists);
            RequestDispatcher rd = req.getRequestDispatcher("/adminAvailableArtists.jsp");
            rd.forward(req, resp);
            
        } catch (Exception e) {
            System.out.println("unable to open connection");
        }
        
        Base.close();
    }
}
