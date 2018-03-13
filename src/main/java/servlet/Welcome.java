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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Welcome extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
        
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
        //query most recent records, put them in the slider
        List<Record> mostRecentRecords = Record.findAll().limit(10).orderBy("id asc");
        
        
        for (Iterator i = mostRecentRecords.iterator(); i.hasNext(); ) {
            //TODO: use custom tag, pass record to function called in custom tag
            Record r = (Record) i.next();
            Artist a = r.parent(Artist.class);
        }
        
        Base.close();
        req.setAttribute("mostRecentRecords", mostRecentRecords);
        rd.forward(req, resp);
    }
}
