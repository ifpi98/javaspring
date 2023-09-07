package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    public void prototypeBeanFind() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println(ac);
        PrototypeTest.PrototypeBean prototypeBean1 = ac.getBean(PrototypeTest.PrototypeBean.class);
        PrototypeTest.PrototypeBean prototypeBean2 = ac.getBean(PrototypeTest.PrototypeBean.class);
        System.out.println(prototypeBean1);
        System.out.println(prototypeBean2);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        prototypeBean1.destory();
        prototypeBean2.destory();
        ac.close();

    }


    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destory() {
            System.out.println("PrototypeBean.destroy");
        }

    }
}
