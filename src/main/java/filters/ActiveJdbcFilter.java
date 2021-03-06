package filters;

import org.javalite.activejdbc.Base;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import org.slf4j.Logger;

public class ActiveJdbcFilter implements Filter {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ActiveJdbcFilter.class);
    
    private String jndiName;
    
    @Override
    public void init(FilterConfig config) throws ServletException {
    }
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        long before = System.currentTimeMillis();
        try {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/wpr_webshop", "root", "root");
            Base.openTransaction();
            chain.doFilter(req, resp);
            Base.commitTransaction();
        } catch (IOException e) {
            Base.rollbackTransaction();
            throw e;
        } catch (ServletException e) {
            Base.rollbackTransaction();
            throw e;
        } finally {
            
            Base.close();
        }
        LOGGER.info("Processing took: {} milliseconds", System.currentTimeMillis() - before);
    }
    
    @Override
    public void destroy() {
    }
}
