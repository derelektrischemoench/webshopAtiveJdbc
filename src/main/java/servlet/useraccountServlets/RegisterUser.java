package servlet.useraccountServlets;

import model.Account;
import org.javalite.activejdbc.Base;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet registeruser");
        RequestDispatcher rd = req.getRequestDispatcher("userSignupForm.jsp");
        rd.forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost in registerUser");
        String email = req.getParameter("customerSignup__email");
        String password1 = req.getParameter("customerSignup__password1");
        String password2 = req.getParameter("customerSignup__password2");
        
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
        
        if (!password1.equals(password2)) {
            System.out.println("password mismatch");
            String errorMsg = "Pasword mismatch. Please try again";
            req.setAttribute("errorMsg", errorMsg);
            RequestDispatcher rd = req.getRequestDispatcher("userSignupForm.jsp");
            rd.forward(req, resp);
        } else {
            //passwords matched, proceed with the account generation
            String hashedPassword = BCrypt.hashpw(password1, BCrypt.gensalt());
            //check if name is available:
            Account a = Account.findFirst("user_name = ?", email);
            if (a == null) {
                //the account is available, continue adding the user
                Account.createIt("first_name", "",
                                 "last_name", "",
                                 "user_name", email,
                                 "hashed_password", hashedPassword,
                                 "is_admin", 0);
                System.out.println("successfully created account");
                
                req.setAttribute("username", email);
                
                //TODO: optional: send email
                RequestDispatcher rd = req.getRequestDispatcher("userAccountSuccessfullyCreated.jsp");
                rd.forward(req,resp);
                
            } else {
                // An account with this username is already taken, render error msg
                String errorMessage = "This username is already taken, please choose another one";
                req.setAttribute("errorMsg", errorMessage);
                RequestDispatcher rd = req.getRequestDispatcher("userSignupForm.jsp");
                rd.forward(req, resp);
            }
        }
        
        Base.close();
    }
}
