package servlet.adminServlets;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import model.Account;
import org.javalite.activejdbc.Base;
import org.javalite.common.Base64;
import org.mindrot.jbcrypt.BCrypt;

import java.nio.charset.Charset;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        
        String username = req.getParameter("adminSignup__username");
        String password = req.getParameter("adminSignup__password1");
        String password2 = req.getParameter("adminSignup__password2");
        
        //input passwords match
        if (password.equals(password2)) {
            
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            //check if name is available:
            Account a = Account.findFirst("user_name = ?", username);
            if (a == null) {
                //the account is available, continue adding the user
                Account.createIt("first_name", "",
                                 "last_name", "",
                                 "user_name", username,
                                 "hashed_password", hashedPassword,
                                 "is_admin", 1);
            } else {
                // An account with this username is already taken, render error msg
                String errorMessage = "This username is already taken, please choose another one";
                req.setAttribute("errorMsg", errorMessage);
                RequestDispatcher rd = req.getRequestDispatcher("/createAdminAccount.jsp");
                rd.forward(req, resp);
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
