package info.rajeshg;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MethodExecutionTimeLogger {

	Logger log = Logger.getLogger(MethodExecutionTimeLogger.class);

	@Around("execution(* *(..)) && @annotation(info.rajeshg.annotation.TimeIt)")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = point.proceed();
		log.info("#" + MethodSignature.class.cast(point.getSignature()).getMethod().getName() + "("
				+ Arrays.toString(point.getArgs()) + "): executed in " + (System.currentTimeMillis() - start)
				+ "ms");

		return result;
	}

}
