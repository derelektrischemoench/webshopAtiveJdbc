package Controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MenuCookieController {
    public static HttpServletResponse toggleMenuStateCookie(HttpServletRequest r, HttpServletResponse res) {
        List<Cookie> cookieList = Arrays.asList(r.getCookies());
        Cookie c = null;
        
        if (cookieList.size() > 0) {
            for (Iterator<Cookie> cIter = cookieList.iterator(); cIter.hasNext(); ) {
                c = cIter.next();
                if (c.getName().equals("menuOpen")) {
                    String cookieVal = c.getValue();
                    
                    if (cookieVal.equals("true")) {
                        c.setValue("false");
                        //res.addCookie(c);
                    } else if (cookieVal.equals("false")) {
                        c.setValue("true");
                        //res.addCookie(c);
                    } else {
                        throw new IllegalStateException("cookie value is invalid");
                    }
                }
            }
        }
        
        return res;
    }
}
