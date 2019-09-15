package ms.me.demothymesession.handler;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public class FirstConnectionTimer implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("firstTime") == null){
            request.getSession().setAttribute("firstTime", LocalDateTime.now());
        }

        return true;
    }
}
