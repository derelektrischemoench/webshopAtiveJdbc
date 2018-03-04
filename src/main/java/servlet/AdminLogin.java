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
        //if the user is not signed in render signinform else redirect to adminloginSuccessful
        HttpSession s = req.getSession();
        
        //if there is a session with an admin name and the username is not null
        if (s.getAttribute("isAdmin") != null && s.getAttribute("username") != null) {
            RequestDispatcher rd = req.getRequestDispatcher("/adminLoginSuccessful.jsp");
            rd.forward(req, resp);
            return;
        } else {
            //render signup form
            System.out.println("doget in adminlogin");
            RequestDispatcher rd = req.getRequestDispatcher("/adminLoginForm.jsp");
            rd.forward(req, resp);
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("adminligon dopost");
        String username = req.getParameter("adminLogin__username");
        String inputPassword = req.getParameter("adminLogin__password");
        
        //open db connection:
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
        Account a = Account.findFirst("user_name = ?", username);
        if (a == null) {
            //account does not exist
            String errorMsg = "No such account.";
            req.setAttribute("errorMsg", errorMsg);
            RequestDispatcher rd = req.getRequestDispatcher("/adminLoginForm.jsp");
            Base.close();
            rd.forward(req, resp);
            
            System.out.println("closed db connection");
            return;
            
        } else {
            if (BCrypt.checkpw(inputPassword, a.get("hashed_password").toString())) {
                System.out.println("Hashed passwords matched");
                
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                session.setAttribute("isAdmin", "true");
                session.setMaxInactiveInterval(30 * 60);
                
                Cookie loginCookie = new Cookie("username", username);
                loginCookie.setMaxAge(30 * 60);
                resp.addCookie(loginCookie);
                RequestDispatcher rd = req.getRequestDispatcher("/adminLoginSuccessful.jsp");
                Base.close();
                
                rd.forward(req, resp);
                //close database connection
                
                return;
                
            } else {
                System.out.println("Wrong passwords");
                String errorMsg = "Wrong password";
                req.setAttribute("errorMsg", errorMsg);
                RequestDispatcher rd = req.getRequestDispatcher("/adminLoginForm.jsp");
                //close database connection
                Base.close();
                rd.forward(req, resp);
                return;
            }
        }
    }
}
