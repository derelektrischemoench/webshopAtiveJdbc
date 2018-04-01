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
        String RecordIdGetParam = req.getParameter("recordId");
        
        //TODO: use the crudder, luke
        RecordCrud rd = new RecordCrud()
        
        Record r = Record.findById(RecordIdGetParam);
        r.deleteCascadeShallow();
        
        RequestDispatcher rd = req.getRequestDispatcher("");
    }
}
