package servlet;

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
        String username = req.getParameter("adminLogin__username");
        String password = req.getParameter("adminLogin__password");
    
        System.out.println(username);
        System.out.println(password);
    }
}
