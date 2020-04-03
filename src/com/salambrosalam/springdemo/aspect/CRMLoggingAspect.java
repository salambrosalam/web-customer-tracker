package com.salambrosalam.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	//setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	//setup pointcut declarations
	@Pointcut("execution(* com.salambrosalam.springdemo.controller.*.*(..))")
	private void forControllPackage() {
		
	}
	//do the same for service and dao
	@Pointcut("execution(* com.salambrosalam.springdemo.service.*.*(..))")
	private void forServicePackage() {
		
	}

	@Pointcut("execution(* com.salambrosalam.springdemo.dao.*.*(..))")
	private void forDaoPackage() {
		
	}
	@Pointcut("forControllPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {
		
	}
	//add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		
		//display method we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("====>>> in @Before advice : calling method " + theMethod);
		
		//display the arguments to the method 
		
		//get the arguments
		Object[] args = theJoinPoint.getArgs();
		
		// loop thru and display args
		for(Object tempArg: args) {
			myLogger.info("======>>>> argument " + tempArg);    
		}
		
		
		
		
		
	}
	
	
	//add @AfterReturning advice
	
	 @AfterReturning(
			 pointcut = "forAppFlow()",
			 returning = "theResult")
	 public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		 
		 //displat method we are returning from
			String theMethod = theJoinPoint.getSignature().toShortString();
			myLogger.info("====>>> in @AfterReturning : calling method " + theMethod);
		 
			myLogger.info("====>>> result: " + theResult );
			
	 }
	 
	
}
