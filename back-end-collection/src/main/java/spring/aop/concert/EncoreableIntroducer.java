package spring.aop.concert;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * @author qiaoyihan
 * @date 2020/5/10
 */
@Aspect
@Component
public class EncoreableIntroducer {
    @DeclareParents(value = "spring.aop.concert.Performance+", defaultImpl = DefaultEncoreable.class)
    public static Encoreable encoreable;
}
