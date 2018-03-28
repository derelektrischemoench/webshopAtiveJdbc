package servlet;

import model.Artist;
import model.Record;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class SearchRecords extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchQuery = req.getParameter("index__searchRecord");
        
        System.out.println("#####search record dopost got record query: " + searchQuery);
        
        List<Record> resultSetRecords = Record.where("title = ?", searchQuery);
        List<Artist> resultSetArtists = Artist.where("artist_name = ?", searchQuery);
    
        System.out.println("Found num records: " + resultSetRecords.size());
        System.out.println("Found num artists: " + resultSetArtists.size() );
        
        List<Artist> asdasd = Artist.findBySQL("select artists.* from artists where soundex('artist_name') = soundex(?)", searchQuery);
        System.out.println(asdasd.size());
        Iterator<Artist> fuzzyArt = asdasd.iterator();
        while (fuzzyArt.hasNext()) {
            Artist a  = fuzzyArt.next();
        }
        
        Iterator<Record> recordIter = resultSetRecords.iterator();
        Iterator<Artist> artistIter = resultSetArtists.iterator();
        
        while (recordIter.hasNext()) {
            Record r = recordIter.next();
        }
        
        while (artistIter.hasNext()) {
            Artist a = artistIter.next();
        }
        
        req.setAttribute("searchQuery" , searchQuery);
        req.setAttribute("artists", resultSetArtists);
        req.setAttribute("records", resultSetRecords);
        
        RequestDispatcher rd = req.getRequestDispatcher("/customerSearchResults.jsp");
        rd.forward(req, resp);
    }
}
