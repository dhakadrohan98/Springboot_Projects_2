package demo;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthenticationAspect {

    @Pointcut("within(demo..*)")
    public void authenticatingPointCut() {

    }

    @Pointcut("within(demo..*)")
    public void authorizationPointCut() {

    }

    @Before("authenticatingPointCut() && authorizationPointCut()")
    public void beforeAuthenticate() {
        System.out.println("Before Authenticating the request");
    }

    @After("authenticatingPointCut() && authorizationPointCut()")
    public void afterAuthenticate() {
        System.out.println("After Authenticating the request");
    }
}
