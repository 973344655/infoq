package demo.work.week7.annotation;


import demo.work.week7.config.DatabaseContextHolder;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

public class ReadOnlyInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        ReadOnly readOnly = (ReadOnly) methodInvocation.getMethod().getAnnotation(ReadOnly.class);
        Annotation[] annotations = methodInvocation.getMethod().getDeclaringClass().getAnnotations();

        //先判断类的注解，再判断方法的注解
        String dsKey = "MASTER";
        for (Annotation annotation : annotations){
            System.out.println(annotation);
            if(annotation.annotationType().equals(ReadOnly.class)){
                dsKey = ((ReadOnly)annotation).value();
            }

        }
        dsKey = readOnly.value();

        System.out.println("*********************");
        System.out.println(dsKey);
        //不能使用主库
        if("MASTER".equals(dsKey)){
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
            return methodInvocation.proceed();
        }finally {
            DatabaseContextHolder.clearDbType();
        }

    }
}
