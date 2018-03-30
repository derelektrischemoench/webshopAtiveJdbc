package Controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CookieController {
    Cookie[] cookies;
    
    public CookieController(Cookie[] cookies) {
        this.cookies = cookies;
    }
    
    public Cookie getCookieByName(String cookieName) {
        List<Cookie> cookiesList = Arrays.asList(this.cookies);
        
        Iterator<Cookie> cIter = cookiesList.iterator();
        while (cIter.hasNext()) {
            Cookie c = cIter.next();
            if (c.getName().equals(cookieName)) {
                return c;
            }
        }
        
        return null;
    }
    
    public HttpServletResponse generateNewCookie(HttpServletResponse r, String cookieName, String cookieValue) {
        Cookie c = new Cookie(cookieName, cookieValue);
        c.setMaxAge(60*60*24*365);
        r.addCookie(c);
        
        return r;
    }
}
