package hello.login.web.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@RestController
public class SessionInfoController {

    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request){
        //세션은 메모리에 저장되기에 최소한의 정보만 담아야함
        HttpSession session = request.getSession(false);
        if(session == null)
            return "세션이 없습니다.";

        //세션 데이터 출력
        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name={}, value={}", name, session.getAttribute(name)));

        log.info("sessionId={}", session.getId()); //쿠키담긴 아이디와 같음
        log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval()); // 세션유효시간 ex) 1800초 -> 60으로 나누면 30분
        log.info("creationTime={}", new Date(session.getCreationTime())); //세션 생성일시
        log.info("lastAccessedTime={}", new Date(session.getLastAccessedTime())); //가장 최근 접근시간
        log.info("isNew={}", session.isNew()); //false : 지금생성 x, 이미 생성된거 써서

        return "세션 출력";
    }

}
