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

public class Welcome extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
        System.out.println("asdasd");
        RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
        
        //query most recent records, put them in the slider
        List<Record> mostRecentRecords = Record.findAll().limit(10).orderBy("id asc");
        for (Iterator i = mostRecentRecords.iterator(); i.hasNext(); ) {
            Record r = (Record) i.next();
            System.out.println(r.get("title"));
        }
        
        Base.close();
        req.setAttribute("mostRecentRecords", mostRecentRecords);
        rd.forward(req, resp);
    }
}
