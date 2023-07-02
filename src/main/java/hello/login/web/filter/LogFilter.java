package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

// 서블릿 필터 구현
@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
        log.info("log filter init");
    }

    //고객 요청시 먼저 호출됨
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter");
        //ServletRequest는 http요청 아닌경우도 고려하니 다운캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        //모든요청 uri 남기기
        String requestURI = httpRequest.getRequestURI();
        //사용자 구분
        String uuid = UUID.randomUUID().toString();

        try{
            log.info("REQUEST [{}][{}]", uuid, requestURI);
            //다음필터 호출, 없으면 서블릿 호출됨
            chain.doFilter(request, response);
        }catch (Exception e){
            throw e;
        }finally {
            log.info("RESPONSE [{}][{}]", uuid, requestURI);
        }
    }

    @Override
    public void destroy() {
//        Filter.super.destroy();
        log.info("log filter destroy");
    }
}
