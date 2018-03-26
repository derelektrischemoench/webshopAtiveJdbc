package servlet.adminServlets;

import model.Artist;
import model.Record;
import org.javalite.activejdbc.Base;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditRecord extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do get in Edit record");
        String artistId = req.getParameter("artistId");
        String recordId = req.getParameter("recordId");
    
        //render the form
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
        
        
        Record r = Record.findById(recordId);
        Artist a = r.parent(Artist.class);
       
        req.setAttribute("artist", a);
        req.setAttribute("record", r);
        req.setAttribute("isEdit", true);
        RequestDispatcher rd = req.getRequestDispatcher("/addRecord.jsp");
        //todo: render the values into the form
        //todo: the artists id is missing here
        Base.close();
        rd.forward(req, resp);
        
    }
}
