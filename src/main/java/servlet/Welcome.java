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
        List<Record> mostRecentRecords = Record.findAll().limit(10).orderBy("id asc");
        
        for (Iterator i = mostRecentRecords.iterator(); i.hasNext(); ) {
            Record r = (Record) i.next();
            Artist a = r.parent(Artist.class);
        }
        req.setAttribute("mostRecentRecords", mostRecentRecords);
        rd.forward(req, resp);
    }
}
