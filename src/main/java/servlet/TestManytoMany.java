package servlet;

import model.Account;
import model.Record;
import model.Shoppingcart;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.logging.ActiveJDBCLogger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestManytoMany extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doget in testmanytomany");
        
        
        System.out.println("creating record");
        Record record = Record.createIt("title", "testTitle", "label", "testLabel", "artist_id", "1", "price", "21.99");
        
        System.out.println("creating shoppingcart");
        Shoppingcart shoppingcart = Shoppingcart.createIt(); //gets an autoid
        
        System.out.println("adding record to shoppingcart");
        shoppingcart.add(record);
    }
    
}
