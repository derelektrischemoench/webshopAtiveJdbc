package servlet.adminServlets;

import model.Record;
import org.javalite.activejdbc.Base;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditRecordServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int recordId = Integer.parseInt(req.getParameter("recordId"));
        System.out.println("received record param: " + recordId);
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
        Record r = Record.findById(recordId);
        
        Base.close();
    
        System.out.println("found corresponding record: " + r.get("title"));
    
        RequestDispatcher rd = req.getRequestDispatcher("/addRecord.jsp");
        req.setAttribute("isEdit", true);
        req.setAttribute("editRecordId", r.get("id"));
        rd.forward(req, resp);
        
    }
}
