package utils;

import org.apache.commons.io.FileUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class InitContextListener implements ServletContextListener {
 public static final String backupPath = Paths
			.get(new JFileChooser().getFileSystemView().getDefaultDirectory().toString(), "webshop", "backupmedia")
			.toString();
    @Override
    public void contextInitialized(ServletContextEvent ce) {
        File f = new File(backupPath);
        if (!f.exists()) {
            f.mkdirs();
        }
        String uploadFilesPath = ce.getServletContext().getRealPath("uploadFiles");
        try {
            FileUtils.copyDirectory(f, new File(uploadFilesPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    @Override
    public void contextDestroyed(ServletContextEvent ce) {
        File f = new File(backupPath);
        if (!f.exists()) {
            f.mkdirs();
        }
        String uploadFilesPath = ce.getServletContext().getRealPath("uploadFiles");
        try {
            FileUtils.copyDirectory(new File(uploadFilesPath), f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
