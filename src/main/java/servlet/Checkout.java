package servlet;

import Controllers.MailSender;
import model.Order;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Map;


public class Checkout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/orderConfirmCustomerCredentials.jsp");
        rd.forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        
        HttpSession s = req.getSession();
        int shoppingCartId = (int) s.getAttribute("shoppingCartId");
        String customerFirstname = req.getParameter("customerCheckout__firstName");
        String customerLastName = req.getParameter("customerCheckout__lastName");
        String street = req.getParameter("customerCheckout__street");
        String houseNo = req.getParameter("customerCheckout__houseNumber");
        String zip = req.getParameter("customerCheckout__zip");
        String city = req.getParameter("customerCheckout__city");
        String emailAddress = req.getParameter("customerCheckout__emailAddress");
        String emailSubject = "Your order";
        String message = "Your order has been successful. Thank you for shopping @ tightshitrecords.";
        java.sql.Date orderDate = new java.sql.Date(System.currentTimeMillis());
        
        Order o = new Order(); //https://www.youtube.com/watch?v=MPhHl2DpD4E
        o.set(
                "date", orderDate,
                "shoppingcart_id", shoppingCartId,
                "first_name", customerFirstname,
                "last_name", customerLastName,
                "address", street,
                "house_number", houseNo,
                "zip", zip,
                "city", city,
                "email_address", emailAddress
        );
        
        o.saveIt();
        
        //SEND MAIL
        try {
            MailSender mailsender = new MailSender(
                    "tightshitrecords@gmx.de",
                    emailAddress,
                    emailSubject,
                    message,
                    "tightshitrecords@gmx.de",
                    "T1ghtSh1t"
            );
            
            mailsender.sendMail();
            System.out.println("successfully sent mail");
            
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("error sending mail");
        }
        
        RequestDispatcher rd = req.getRequestDispatcher("/orderSuccessfullyPlaced.jsp");
        rd.forward(req, resp);
    }
}
