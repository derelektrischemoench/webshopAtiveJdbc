package servlet;

import model.Account;
import org.javalite.activejdbc.Base;
import org.javalite.common.Base64;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
        String inputPassword = req.getParameter("adminLogin__password");
        
        Account a = Account.findFirst("user_name = ?", username);
        if (a == null) {
            //account does not exist
            String errorMsg = "No such account.";
            req.setAttribute("errorMsg", errorMsg);
            RequestDispatcher rd = req.getRequestDispatcher("/adminLoginForm.jsp");
            rd.forward(req, resp);
            return;
        } else {
            System.out.println("Found account " + a.get("user_name"));
            System.out.println("Account password from db: " + a.get("hashed_password"));
            
            if (BCrypt.checkpw(inputPassword, a.get("hashed_password").toString())) {
                System.out.println("Hashed passwords matched");
                
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                session.setMaxInactiveInterval(30*60);
                
                Cookie loginCookie = new Cookie("username", username);
                loginCookie.setMaxAge(30*60);
                resp.addCookie(loginCookie);
                RequestDispatcher rd = req.getRequestDispatcher("/adminLoginSuccessful.jsp");
                rd.forward(req, resp);
                return;
                
            } else {
                System.out.println("Wrong passwords");
                String errorMsg = "Wrong password";
                req.setAttribute("errorMsg", errorMsg);
                RequestDispatcher rd = req.getRequestDispatcher("/adminLoginForm.jsp");
                rd.forward(req, resp);
            }
        }
        
        
        Base.close();
    }
}
