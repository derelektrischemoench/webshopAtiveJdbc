package servlet;

import model.ContactInquiry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContactServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doget in contact");
        RequestDispatcher rd = req.getRequestDispatcher("/contactForm.jsp");
        rd.forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("dopost in contact");
        
        String emailAddr = req.getParameter("contact__emailAddress");
        String message = req.getParameter("contact__message");
    
        System.out.println("email: " + emailAddr);
        System.out.println("Message:  " + message);
    
        ContactInquiry.create("contact_email", emailAddr,
                              "message", message).saveIt();
        
    }
}
