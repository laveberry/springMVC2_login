package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

//Member저장소
@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); //static 사용
    private static long sequence = 0L; //static

    public Member save(Member member){
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId){
        /*
        List<Member> all = findAll();
        for(Member m : all){
            if(m.getLoginId().equals(loginId)){
                //return m;
                return Optional.of(m);
            }
        }
        return Optional.empty();
         */

        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst(); //먼저 나오는값 반환
    }

    public List<Member> findAll(){
        //key에 대한 value들이 리스트로 변환
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
