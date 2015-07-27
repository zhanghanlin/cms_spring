package com.demo.java.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.demo.java.utils.string.StringUtils;

@Component
@Aspect
public class CmsAspect {

    static final Logger logger = LoggerFactory.getLogger(CmsAspect.class);

    /**
     * 
     * 配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
     * 
     * @author zhanghanlin
     * @since JDK 1.7
     */
    @Pointcut("execution(* com.demo.java..*(..))")
    public void aspect() {
    }

    /**
     * 配置前置通知,使用在方法aspect()上注册的切入点. <br/>
     * 同时接受JoinPoint切入点对象,可以没有该参数
     * 
     * @author zhanghanlin
     * @since JDK 1.7
     */
    @Before("aspect()")
    public void before(JoinPoint joinPoint) {
        ProceedingJoinPoint pjp = (ProceedingJoinPoint) joinPoint;
        Object[] args = pjp.getArgs();
        int argsCount = args != null ? args.length : 0;
        logger.info("@Before\t{}", joinPoint);
        if (argsCount > 0) {
            logger.info("@Before\targs:{}", StringUtils.array2str(args));
        } else {
            logger.info("@Before\targs is null");
        }
    }

    /**
     * 配置后置通知,使用在方法aspect()上注册的切入点
     * 
     * @author zhanghanlin
     * @param joinPoint
     * @since JDK 1.7
     */
    @After("aspect()")
    public void after(JoinPoint joinPoint) {
        logger.info("@After\t{}", joinPoint);
    }

    /**
     * 配置环绕通知,使用在方法aspect()上注册的切入点
     * 
     * @author zhanghanlin
     * @param joinPoint
     * @since JDK 1.7
     */
    @Around("aspect()")
    public Object around(JoinPoint joinPoint) {
        Object proceed = null;
        long start = System.currentTimeMillis();
        try {
            ProceedingJoinPoint pjp = (ProceedingJoinPoint) joinPoint;
            proceed = pjp.proceed();
            logger.info("@Around\tproceed:{}", proceed);
            long end = System.currentTimeMillis();
            logger.info("@Around\t{}\tUse time:{} ms!", joinPoint, (end - start));
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            logger.info("@Around\t{}\tUse time:{} ms!with exception:{}", joinPoint, (end - start), e.getMessage());
        }
        return proceed;
    }

    /**
     * 配置后置返回通知,使用在方法aspect()上注册的切入点
     * 
     * @author zhanghanlin
     * @param joinPoint
     * @since JDK 1.7
     */
    @AfterReturning("aspect()")
    public void afterReturn(JoinPoint joinPoint) {
        logger.info("@AfterReturning\t{}", joinPoint);
    }

    /**
     * 配置抛出异常后通知,使用在方法aspect()上注册的切入点
     * 
     * @author zhanghanlin
     * @param joinPoint
     * @param ex
     * @since JDK 1.7
     */
    @AfterThrowing(pointcut = "aspect()", throwing = "ex")
    public void afterThrow(JoinPoint joinPoint, Exception ex) {
        logger.info("@AfterThrowing\t{}\terror:{} ", joinPoint, ex.getMessage());
    }
}
