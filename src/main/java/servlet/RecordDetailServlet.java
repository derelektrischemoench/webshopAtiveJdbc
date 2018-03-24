package servlet;

import model.Record;
import org.javalite.activejdbc.Base;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

public class RecordDetailServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doget in record detail");
        int recordID = Integer.parseInt(req.getParameter("recordId"));
        try {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
            Record r  = Record.findFirst("id=?", recordID);
            Base.close();
            req.setAttribute("record", r);
            RequestDispatcher rd = req.getRequestDispatcher("recordDetailCustomer.jsp");
            
            rd.forward(req, resp);
            
            
        } catch (Exception e ) {
            e.printStackTrace();
            System.err.println("Record not found");
        }
        
        
    }
}
