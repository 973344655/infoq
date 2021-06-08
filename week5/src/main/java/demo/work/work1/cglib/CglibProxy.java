package demo.work.work1.cglib;


import demo.work.work1.MyAop;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author xxl
 */
public class CglibProxy<T> implements MethodInterceptor {

    public T getProxy(T clz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clz.getClass());
        enhancer.setCallback(new CglibProxy());

        return (T)enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = null;


        Annotation annotation = o.getClass().getAnnotation(MyAop.class);
        if(Objects.nonNull(annotation)){
            System.out.println("代理增强 before()");
        }

        result = methodProxy.invokeSuper(o, objects);

        if(Objects.nonNull(annotation)){
            System.out.println("代理增强 after()");
        }

        return result;
    }
}
