package servlet.adminServlets;

import Controllers.MenuCookieController;
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
import java.text.CollationKey;
import java.util.Arrays;
import java.util.List;

import static Controllers.MenuCookieController.toggleMenuStateCookie;

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
            RequestDispatcher rd = req.getRequestDispatcher("/adminLoginForm.jsp");
            rd.forward(req, resp);
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("login__username");
        String inputPassword = req.getParameter("login__password");
        
        //open db connection:
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
        Account a = Account.findFirst("user_name = ?", username);
        Base.close();
        
        //TODO: usernameerror message doesnt work yet.
        
        if (a == null) {
            //account does not exist
            String usernameErrorMsg = "No such account.";
            req.setAttribute("usernameErrorMsg", usernameErrorMsg);
            RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
            rd.forward(req, resp);
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
                
                //invalidatemenuopenCookie
                loginCookie.setMaxAge(30 * 60);
                resp.addCookie(loginCookie);
                
                //call the cookie controller to set the menustate
                MenuCookieController.toggleMenuStateCookie(req, resp);
                
                if (isAdmin) {
                    System.out.println("is adminaccount");
                    session.setAttribute("isAdmin", "true");
                    session.setAttribute("username", a.get("user_name"));
                    RequestDispatcher rd = req.getRequestDispatcher("/adminLoginSuccessful.jsp");
                    rd.forward(req, resp);
                    
                } else if (!isAdmin) {
                    System.out.println("is useracc");
                    session.setAttribute("username", a.get("user_name"));
                    req.setAttribute("signinSuccessMessage", "Your signin was successful.");
                    RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
                    rd.forward(req, resp);
                    //TODO: sessions and cookies also: shopping cart
                }
                
                return;
                
            } else {
                //passwords didnt match
                System.out.println("Wrong password");
                String errorMsg = "Wrong password";
                req.setAttribute("passwordErrorMsg", errorMsg);
                req.setAttribute("visibility", "visible");
                RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
                rd.forward(req, resp);
            }
        }
    }
}
