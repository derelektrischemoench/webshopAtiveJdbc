package servlet.adminServlets;

import model.Artist;
import model.Record;
import model.Track;
import org.javalite.activejdbc.Base;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class EditRecord extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do get in Edit record");
        String artistId = req.getParameter("artistId");
        String recordId = req.getParameter("recordId");
        
        Record r = Record.findById(recordId);
        Artist a = r.parent(Artist.class);
        List<Track> trackList = r.getAll(Track.class);
        
        StringBuilder ListAsString = new StringBuilder();
    
        Iterator<Track> tracksIterator = trackList.iterator();
        while (tracksIterator.hasNext()) {
            Track t = tracksIterator.next();
            ListAsString.append(t.getString("name"));
            ListAsString.append(";");
        }
        
        String trackString = ListAsString.toString();
       
        req.setAttribute("artist", a);
        req.setAttribute("record", r);
        req.setAttribute("trackString", trackString);
        req.setAttribute("isEdit", true);
        RequestDispatcher rd = req.getRequestDispatcher("/addRecord.jsp");
        
        rd.forward(req, resp);
    }
}
