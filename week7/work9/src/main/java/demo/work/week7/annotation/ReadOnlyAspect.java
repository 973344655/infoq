package demo.work.week7.annotation;

import demo.work.week7.config.DatabaseContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * ReadOnly 只读slave
 * @author xxl
 */
@Component
@Aspect
@Order(2)
public class ReadOnlyAspect {

    private final static String MATSR = "db1";
    @Pointcut("@annotation(demo.work.week7.annotation.ReadOnly)")
    public void dataSourcePointCut(){

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String dsKey = getDSAnnotation(joinPoint).value();

        //不能使用主库
        if(MATSR.equals(dsKey)){
            dsKey = "";
        }
        if("".equals(dsKey)){
            //负载均衡，随机负载
            List<String> slaves = DatabaseContextHolder.getDataSources();
            int index = new Random().nextInt(slaves.size());
            dsKey = slaves.get(index);
        }
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
    private ReadOnly getDSAnnotation(ProceedingJoinPoint joinPoint){
        Class<?> targetClass = joinPoint.getTarget().getClass();
        ReadOnly dsAnnotation = targetClass.getAnnotation(ReadOnly.class);
        // 先判断类的注解，再判断方法注解
        if(Objects.nonNull(dsAnnotation)){
            return dsAnnotation;
        }else{
            MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
            return methodSignature.getMethod().getAnnotation(ReadOnly.class);
        }
    }

}
