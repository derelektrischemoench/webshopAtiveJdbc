package servlet;

import model.Page;
import model.Record;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Paginator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MostRecentRecords extends HttpServlet {
    /*This shows the most recent records*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doget in mostRecent records");
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        int offset = pageNo * 10;
        
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
        
        
        List<Record> foundRecords = Record.findAll().limit(10).offset(offset)   ;
        for(Record r:foundRecords) {
            System.out.println(r.get("title"));
        }
        req.setAttribute("foundRecords", foundRecords);
        
        RequestDispatcher rd = req.getRequestDispatcher("allRecordsCustomer.jsp");
        rd.forward(req, resp);
        
        Base.close();
    }
}
