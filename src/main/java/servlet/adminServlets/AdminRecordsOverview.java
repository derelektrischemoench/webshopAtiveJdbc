package servlet.adminServlets;

import model.Artist;
import model.Record;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminRecordsOverview extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String artistIdGetParam = req.getParameter("artistId");
        
        try {
            Artist a = Artist.findFirst("id=?", artistIdGetParam);
            String artistId = a.get("id").toString();
            List<Record> artistsRecords = Record.where("artist_id = ?", artistId);
            
            System.out.println("found " + artistsRecords.size() + " records for artist: " + a.get("artist_name"));
            req.setAttribute("artist", a);
            req.setAttribute("artistsRecords", artistsRecords);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("artist not found");
        }
        RequestDispatcher rd = req.getRequestDispatcher("/adminRecordsPerArtist.jsp");
        rd.forward(req, resp);
    }
}
