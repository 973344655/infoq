package demo.work.week7.annotation;

import org.aopalliance.aop.Advice;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * @author xxl
 */
@Component
public class ReadOnlyAdvisor extends AbstractPointcutAdvisor implements BeanFactoryAware {
    private Pointcut pointcut;

    private Advice advice;

    public ReadOnlyAdvisor() {
        advice = new ReadOnlyInterceptor();
        pointcut = new ReadOnlyPointCut();
    }



    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        if (this.advice instanceof BeanFactoryAware) {
            ((BeanFactoryAware) this.advice).setBeanFactory(beanFactory);
        }
    }



    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;

    }
}
