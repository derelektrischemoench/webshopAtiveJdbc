package servlet;

import model.Account;
import org.javalite.activejdbc.Base;
import org.javalite.common.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
        
        String salt = getInitParameter("adminAccSalt");
        System.out.println("adminaccountSalt from webxml: " + salt);
        
        String username = req.getParameter("adminSignup__username");
        String password = req.getParameter("adminSignup__password1");
        String password2 = req.getParameter("adminSignup__password2");
        
        //input passwords match
        if (password.equals(password2)) {
            String saltedPassword = password + salt;
            MessageDigest digest;
            try {
                digest = MessageDigest.getInstance("SHA-256");
                
                String passwordBase64 = Base64.getEncoder().encodeToString(digest.digest(saltedPassword.getBytes()));
                //check if name is available:
                Account a = Account.findFirst("user_name = ?", username);
                if (a==null) {
                    //the account is available, continue adding the user
                    Account.createIt("first_name", "",
                                     "last_name", "",
                                     "user_name", username,
                                     "hashed_password", passwordBase64,
                                     "is_admin", 1);
                } else {
                    // An account with this username is already taken, render error msg
                    String errorMessage = "This username is already taken, please choose another one";
                    req.setAttribute("errorMsg", errorMessage);
                    RequestDispatcher rd = req.getRequestDispatcher("/createAdminAccount.jsp");
                    rd.forward(req, resp);
                }
                
                
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else {
            //passwords no match
            String errorMessage = "Your passwords do not match. Please try again";
            req.setAttribute("errorMsg", errorMessage);
            RequestDispatcher rd = req.getRequestDispatcher("/createAdminAccount.jsp");
            rd.forward(req, resp);
        }
        
        
        //close connection
        Base.close();
        
    }
}
