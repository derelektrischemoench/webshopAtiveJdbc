package servlet.adminServlets;

import Controllers.RecordCrud;
import model.Artist;
import model.Record;
import sun.misc.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteRecord extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet in DeleteRecordFinally");
        String RecordIdGetParam = req.getParameter("recordId");
        
        RecordCrud rc = new RecordCrud(RecordIdGetParam);
        String delete = rc.deleteRecord();
    
        System.out.println("return of the crudder delete: " + delete);
        
        RequestDispatcher rd = req.getRequestDispatcher("/recordDeleteSuccess.jsp");
        rd.forward(req, resp);
    }
}
