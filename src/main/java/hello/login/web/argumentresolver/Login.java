package hello.login.web.argumentresolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 파라미터에만 적용됨
@Retention(RetentionPolicy.RUNTIME) //동작완료까지 어노테이션 남아있게
public @interface Login {


}
