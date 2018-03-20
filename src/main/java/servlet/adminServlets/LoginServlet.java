package servlet.adminServlets;

import model.Account;
import org.javalite.activejdbc.Base;
import org.javalite.common.Base64;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginServlet extends HttpServlet {
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
            System.out.println("doget in admindlogin");
            RequestDispatcher rd = req.getRequestDispatcher("/adminLoginForm.jsp");
            rd.forward(req, resp);
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet dopost");
        String username = req.getParameter("login__username");
        String inputPassword = req.getParameter("login__password");
        
        System.out.println("LoginServlet dopost with: " + username + " " + inputPassword);
        
        
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
            boolean isAdmin = a.getBoolean("is_admin");
            //check pws
            
            if (BCrypt.checkpw(inputPassword, a.get("hashed_password").toString())) {
                System.out.println("Hashed passwords matched");
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                session.setMaxInactiveInterval(30 * 60);
                Cookie loginCookie = new Cookie("username", username);
                loginCookie.setMaxAge(30 * 60);
                resp.addCookie(loginCookie);
                System.out.println("state value : " + isAdmin);
                
                if (isAdmin) {
                    System.out.println("is adminaccount");
                    session.setAttribute("isAdmin", "true");
                    RequestDispatcher rd = req.getRequestDispatcher("/adminLoginSuccessful.jsp");
                    rd.forward(req, resp);
                    
                } else if (!isAdmin) {
                    System.out.println("is useracc");
                    req.setAttribute("signinSuccessMessage", "Your signin was successful.");
                    RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
                    rd.forward(req, resp);
                }
                
                Base.close();
                System.out.println("db connection closed");
                return;
                
            } else {
                //passwords didnt match
                System.out.println("Wrong passwords");
                String errorMsg = "Wrong password";
                req.setAttribute("errorMsg", errorMsg);
                
                if (isAdmin) {
                    System.out.println("is adminaccount");
                    
                    RequestDispatcher rd = req.getRequestDispatcher("/adminLoginForm.jsp");
                    rd.forward(req, resp);
                    Base.close();
                    return;
                    
                } else {
                    System.out.println("is useracc");
                    req.setAttribute("signinSuccessMessage", "Your signin was successful.");
                    RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
                    rd.forward(req, resp);
                    Base.close();
                    return;
                }
                
            }
        }
    }
}
