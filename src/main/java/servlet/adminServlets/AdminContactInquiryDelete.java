package servlet.adminServlets;

import model.ContactInquiry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminContactInquiryDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doget in delete contact servlet");
        String cId = req.getParameter("id");
        System.out.println("contactid =  " + cId);
        
        ContactInquiry c = ContactInquiry.findById(cId);
        c.delete();
        
        String redirectString = req.getRequestURI();
        System.out.println("redirecting to: " + redirectString);
        
        resp.sendRedirect("/webapp/adminLogin/adminContactInquiries");
    }
}
