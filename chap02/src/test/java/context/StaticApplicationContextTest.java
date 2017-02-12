package context;

import domain.Hello;
import domain.StringPrinter;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2017-02-12
 */
public class StaticApplicationContextTest {
    @Test
    public void staticApplicationContextTest() {
        StaticApplicationContext ac = new StaticApplicationContext();
        ac.registerSingleton("hello", Hello.class);

        Hello hello1 = (Hello) ac.getBean("hello");
        assertNotNull(hello1);

        Hello hello2 = (Hello) ac.getBean("hello");
        assertEquals(hello1, hello2);
    }

    @Test
    public void registerBeanWithDependency() {
        StaticApplicationContext ac = new StaticApplicationContext();

        ac.registerBeanDefinition("printer",
                new RootBeanDefinition(StringPrinter.class));

        BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
        helloDef.getPropertyValues().addPropertyValue("name", "Spring");
        // printer 빈을 DI
       helloDef.getPropertyValues().addPropertyValue("printer",
                new RuntimeBeanReference("printer"));
        ac.registerBeanDefinition("hello", helloDef);

        Hello hello = ac.getBean("hello", Hello.class);
        hello.pirnt();

        assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
    }
}
