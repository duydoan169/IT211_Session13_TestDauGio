package org.example.hn_ks24_cntt4_doanngocduy2.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* org.example.hn_ks24_cntt4_doanngocduy2.service.*.*(..))")
    public Object serviceLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Tên phương thức: {}, tham số đầu vào: {}", joinPoint.getSignature().getName(), joinPoint.getArgs());

        try {
            Object result = joinPoint.proceed();
            log.info("Chạy phương thức {} thành công, trả về: {}", joinPoint.getSignature().getName(), result);
            return result;
        } catch (Throwable ex) {
            log.warn("Phương thức {} thất bại: {}", joinPoint.getSignature().getName(), ex.getMessage());
            throw ex;
        }
    }
}
