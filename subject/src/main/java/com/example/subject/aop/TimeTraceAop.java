//package com.example.subject.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class TimeTraceAop {
//    @Around("execution(* com.example.subject..*(..))") // 이 경로의 하위에 있는 모든 객체의 모든 메소드를 추적
//    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
//        long start = System.currentTimeMillis();
//        System.out.println("START: " + joinPoint.toString());
//        try {
//            return joinPoint.proceed();
//        } finally {
//            long finish = System.currentTimeMillis();
//            long result = finish - start;
//            System.out.println("END: " + joinPoint.toString() + " " + result + "ms");
//        }
//    }
//}
