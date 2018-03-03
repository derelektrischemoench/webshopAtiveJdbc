package servlet;

import model.Account;
import model.Admin;
import org.javalite.activejdbc.Base;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateAdminAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/createAdminAccount.jsp");
        rd.forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //connect to database:
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        Connection conn = null;
        
        //conn = DriverManager.getConnection("jdbc:sqlite:db.sqlite");
        Base.open("org.sqlite.JDBC", "jdbc:sqlite:db.db", "", "");
        
        
        String username = req.getParameter("adminSignup__username");
        String password = req.getParameter("adminSignup__password1");
        String password2 = req.getParameter("adminSignup__password2");
        
        System.out.println("usernameform " + username);
        System.out.println("passwordform " + password);
        System.out.println("passwordform2 " + password2);
        
        Account newAdminAcc = new Account();
        newAdminAcc.set("first_name", "");
        newAdminAcc.set("last_name", "");
        newAdminAcc.set("username", username);
        newAdminAcc.set("password", password);
        newAdminAcc.saveIt();
        
        //close connection
        Base.close();
        
    }
}
