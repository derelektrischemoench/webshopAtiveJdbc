package servlet;

import model.Record;
import model.Track;
import org.javalite.activejdbc.Base;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

public class RecordDetailServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doget in record detail");
        int recordID = Integer.parseInt(req.getParameter("recordId"));
        try {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
            Record r  = Record.findFirst("id=?", recordID);
            List<Track> tracksOfRecord = r.getAll(Track.class);
            
            req.setAttribute("record", r);
            req.setAttribute("tracksOfRecord", tracksOfRecord);
            RequestDispatcher rd = req.getRequestDispatcher("recordDetailCustomer.jsp");
            rd.forward(req, resp);
            Base.close();
        } catch (Exception e ) {
            e.printStackTrace();
            System.err.println("Record not found");
        }
        
        
    }
}
