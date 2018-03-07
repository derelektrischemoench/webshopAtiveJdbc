package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class AddRecord extends HttpServlet {
    private static final String SAVE_DIR = "uploadFiles";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
          
            String disposition = part.getHeader("Content-Disposition");
            String filename = getFileName(part);
            String filenameTrimmed = filename.replace(" ", "_");
         
            //create file:
            System.out.println("disposition part: " + disposition);
            System.out.println("Filename: " + filename);
            
            filename = new File(filename).getName();
            System.out.println("filename from getFilename  " + getFileName(part) );
            part.write(filename);
            System.out.println("wrote part " + savePath + filename);
            
        }
    }
    
    private String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim()
                         .replace("\"", "");
            }
        }
        return null;
    }
    
    
}
