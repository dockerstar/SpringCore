package org.dmitri.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
public class NotNullArgsAspect {
    @Around("@annotation(NotNullArgs)")
    public Object checkArg(ProceedingJoinPoint pjp) {
        try {
            System.out.println("Init method: " + pjp.getSignature().getName());
            for (Object arg: pjp.getArgs()) {
                if (arg==null) {
                    throw new IllegalAccessException("Arg null in method: " + pjp.getSignature().getName());
                }
            }
            return pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
