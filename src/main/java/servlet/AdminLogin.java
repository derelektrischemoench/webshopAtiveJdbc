package servlet;

import model.Account;
import org.javalite.activejdbc.Base;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //render signup form
        System.out.println("doget in adminlogin");
        RequestDispatcher rd = req.getRequestDispatcher("/adminLoginForm.jsp");
        rd.forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //open db connection:
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
        
        System.out.println("adminligon dopost");
        String username = req.getParameter("adminLogin__username");
        String password = req.getParameter("adminLogin__password");
    
        // find admin acc:
        Account a = Account.findFirst("user_name = ?", username);
        System.out.println("Found account " + a.get("user_name"));
        
        Base.close();
    }
}
