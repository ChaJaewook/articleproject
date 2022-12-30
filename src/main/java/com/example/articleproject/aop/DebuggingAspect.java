package com.example.articleproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect // AOP 클래스 선언: 부가 기능을 주입하는 클래스
@Component // IoC 컨테이너가 해당 객체를 생성 및 관리
@Slf4j
public class DebuggingAspect {
    //어떤 메소드를 잘라서, 어느 지점에 들어갈지 지점을 정한다.

    //@Pointcut("execution(* com.example.articleproject.service.CommentService.*(..))")
    //대상 : api 패키지의 모든 메소드
    @Pointcut("execution(* com.example.articleproject.api.*.*(..))")
    //어느지점을 타겟으로해서 부가기능을 주입할지 정한다.
    private void cut(){

    }

    //실행 시점 설정 : cut()의 대상이 수행되기 이전에 수행
    //컷을 지점으로 실행되기전에 before실행?
    @Before("cut()")
    public void logginArgs(JoinPoint joinPoint)
    {
        // cut()의 대상 메소드
        // 입력값 가져오기
        Object[] args=joinPoint.getArgs();

        // 클래스명
        String className=joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        // 메소드명
        String methodName=joinPoint.getSignature()
                .getName();

        // 입력값 로깅하기
        // CommentService#create()의 입력값 => 5
        // CommentService#create()의 입력값 => CommentDto(id=null,...)
        for(Object obj:args)
        {
            log.info("{}#{}의 입력값 => {}",className,methodName,obj);
        }
    }

    //실행 시점 설정: cut()에 지정된 대상 호출 성공 후!
    @AfterReturning(value = "cut()",returning = "returnObj")
    public void loggingReturnValue(JoinPoint joinPoint, // 대략 cut()의 대상 메소드
                                   Object returnObj) //리턴값
    {
        // 클래스명
        String className=joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        // 메소드명
        String methodName=joinPoint.getSignature()
                .getName();

        // 반환값 로깅
        // CommentService#create()의 반환값 => CommentDto(id=10, ....)
        log.info("{}#{}의 반환값 => {}",className,methodName,returnObj);

    }
}
