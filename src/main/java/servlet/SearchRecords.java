package servlet;

import model.Artist;
import model.Record;
import org.javalite.activejdbc.Base;

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
        
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
    
        List<Record> resultSetRecords = Record.where("title = ?", searchQuery);
        List<Artist> resultSetArtists = Artist.where("artist_name = ?", searchQuery);
    
        System.out.println("Found num records: " + resultSetRecords.size());
        System.out.println("Found num artists: " + resultSetArtists.size() );
        
        
        Iterator<Record> recordIter = resultSetRecords.iterator();
        
        Iterator<Artist> artistIter = resultSetArtists.iterator();
        
        
        System.out.println("Results in Records:");
        while (recordIter.hasNext()) {
            Record r = recordIter.next();
            System.out.println("found record for searchquery:: " + searchQuery + r.get("title"));
        }
        
        
        while (artistIter.hasNext()) {
            Artist a = artistIter.next();
            System.out.println("found Artist for searchquery:" + searchQuery + a.get("artist_name"));
        }
        
        req.setAttribute("searchQuery" , searchQuery);
        req.setAttribute("artists", resultSetArtists);
        req.setAttribute("records", resultSetRecords);
        
        
    
        RequestDispatcher rd = req.getRequestDispatcher("/customerSearchResults.jsp");
        rd.forward(req, resp);
        
        
        Base.close();
        
    }
}
