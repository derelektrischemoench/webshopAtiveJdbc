package servlet.adminServlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//TODO: NOTE: files are save in domains/domain1/generated/jsp/webshop

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class AddRecord extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doget addrecord");
        RequestDispatcher rd = req.getRequestDispatcher("/addRecord.jsp");
        rd.forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String appPath = request.getServletContext().getRealPath("");
        String savePath = "/";
        
        System.out.println("apppath " + appPath); ///home/chris/IdeaProjects/webshop_javalite/target/webshop
        System.out.println("savepath " + savePath); ///home/chris/IdeaProjects/webshop_javalite/target/webshop/uploadFiles
        
        //create fileDir if !exists:
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        
        //upload ze file:
        for (Part part : request.getParts()) {
          
            String timestamp =  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String filename = timestamp + getFileName(part);
         
            part.write(filename);
        }
    }
    
    private String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim()
                         .replace("\"", "").replace(" ", "");
            }
        }
        return null;
    }
    
    
}
