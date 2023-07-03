package hello.login.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    
    public static final String LOG_ID = "logId";
    
    //ctrl + o : 오버라이드
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        String requestURI = request.getRequestURI();
        String logId = UUID.randomUUID().toString();
        //싱글톤이라 지역변수로 담아두면 위험함
        request.setAttribute(LOG_ID, logId);

        //@RequestMapping : HandlerMethod
        //정적 리소스 : ResourceHttpRequestHandler
        if(handler instanceof HandlerMethod){//타입확인
            HandlerMethod hm = (HandlerMethod) handler;//호출할 컨트롤러 메서드의 모든정보가 포함되어 있음.
        }
        
        log.info("LogInterceptor REQUEST [{}][{}][{}]", logId, request, handler);
        return true; //false면 중단됨
        
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("LogInterceptor postHandle [{}]", modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        Object logId = (String)request.getAttribute(LOG_ID);

        log.info("LogInterceptor RESPONSE [{}][{}][{}]", logId, request, handler);
        if(ex != null){
            log.error("LogInterceptor afterCompletion error!!", ex);
        }
    }
}
