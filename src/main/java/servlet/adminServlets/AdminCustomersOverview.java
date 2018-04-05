package servlet.adminServlets;

import model.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminCustomersOverview extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Account> allAccounts = Account.where("is_admin = ? and first_name != ?", "0", "" );
        req.setAttribute("allAccounts", allAccounts);
        RequestDispatcher rd = req.getRequestDispatcher("/adminCustomersOverview.jsp");
        rd.forward(req, resp);
    }
}
