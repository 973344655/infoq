package demo.work.week7.annotation;

import demo.work.week7.config.DatabaseContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Aspect
@Order(1)
public class DynamicDataSourceAspect {

    @Pointcut("@annotation(demo.work.week7.annotation.DataSource)")
    public void dataSourcePointCut(){

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String dsKey = getDSAnnotation(joinPoint).value();
        DatabaseContextHolder.setDbType(dsKey);
        try{
            return joinPoint.proceed();
        }finally {
            DatabaseContextHolder.clearDbType();
        }
    }

    /**
     * 根据类或方法获取数据源注解
     */
    private DataSource getDSAnnotation(ProceedingJoinPoint joinPoint){
        Class<?> targetClass = joinPoint.getTarget().getClass();
        DataSource dsAnnotation = targetClass.getAnnotation(DataSource.class);
        // 先判断类的注解，再判断方法注解
        if(Objects.nonNull(dsAnnotation)){
            return dsAnnotation;
        }else{
            MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
            return methodSignature.getMethod().getAnnotation(DataSource.class);
        }
    }

}
