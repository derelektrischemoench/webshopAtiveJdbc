package servlet;

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
        System.out.println("doget in modddstRecent records");
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        int offset = pageNo * 10;
        //for pagination
        int newOffset = pageNo+1;
        
        
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
        System.out.println("opened connection");
        
        List<Record> allRecordsCustomer = Record.findAll().limit(10000).offset(offset).orderBy("id asc");
        for(Record r : allRecordsCustomer) {
            System.out.println(r.get("title"));
        }
        
        
        Base.close();
        req.setAttribute("allRecordsCustomer", allRecordsCustomer);
        req.setAttribute("newPageno", newOffset);
        
        RequestDispatcher rd = req.getRequestDispatcher("allRecordsCustomer.jsp");
        rd.forward(req, resp);
        
    }
}
