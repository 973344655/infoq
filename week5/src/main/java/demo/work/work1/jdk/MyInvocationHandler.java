package demo.work.work1.jdk;

import demo.work.work1.MyAop;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * jdk动态代理
 * @author xxl
 */
public class MyInvocationHandler implements InvocationHandler {

    Object target;
    private Annotation annotation;

    public MyInvocationHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //通过代理生成的对象上不会有注解
        //Annotation annotation = method.getAnnotation(MyAop.class);

        //需要从原来类上获取注解信息
        Annotation annotation = target.getClass()
                .getDeclaredMethod(method.getName()).getAnnotation(MyAop.class);

        if(Objects.nonNull(annotation)){
            System.out.println("代理增强 before()");
        }

        Object result = method.invoke(target,args);

        //修改返回值
        if(Objects.nonNull(annotation)){
            System.out.println("代理增强 after()");
            MyAop myAop = target.getClass()
                    .getDeclaredMethod(method.getName()).getAnnotation(MyAop.class);
            return result + myAop.value();
        }
        return result;
    }
}
