package springdemo.aspect;

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
	
	// setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// setup pointcut declaration
	@Pointcut("execution(* springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* springdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	// add @Before advice
	@Before("forAppFlow()")
	private void before(JoinPoint theJoinPoint) {
		
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("=====>> in @Before: calling method: " + method);
		
		Object[] args = theJoinPoint.getArgs();
		for (Object tempArg : args)
			myLogger.info("=====>> argument: " + tempArg);
	}
	
	// add @AfterReturning advice
	@AfterReturning(pointcut="forAppFlow()", returning="theResult")
	public void after(JoinPoint theJoinPoint, Object theResult) {
		
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("=====>> in @AfterReturning: from method: " + method);
		
		myLogger.info("=====>> result: " + theResult);
	}
}
