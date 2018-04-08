package servlet.adminServlets;

import model.ContactInquiry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminContactInquiries extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doget in contact inquries");
        List<ContactInquiry> contactInquiries = ContactInquiry.findAll();
    
        RequestDispatcher rd = req.getRequestDispatcher("/adminContactInquiryOverview.jsp");
        req.setAttribute("contactInquries", contactInquiries);
        
        rd.forward(req, resp);
    }
}
