package context;

import domain.Hello;
import domain.IPrinter;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2017-02-12
 */
public class GenericApplicationContextTest {
    @Test
    public void genericApplicationContextTest() {
        GenericApplicationContext ac = new GenericApplicationContext();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(ac);
        reader.loadBeanDefinitions("config/genericApplicationContext.xml");
        ac.refresh();

        Hello hello = ac.getBean("hello", Hello.class);
        hello.pirnt();

        assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
    }

    @Test
    public void genericApplicationContextWithXmlContext() {
        GenericApplicationContext ac =
                new GenericXmlApplicationContext( "config/genericApplicationContext.xml");

        Hello hello = ac.getBean("hello", Hello.class);
        hello.pirnt();

        assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
    }

    @Test
    public void contextHierarchyTest() {
        ApplicationContext parent = new GenericXmlApplicationContext("config/parentContext.xml");

        GenericApplicationContext child = new GenericApplicationContext(parent);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(child);
        reader.loadBeanDefinitions("config/childContext.xml");
        child.refresh();

        //childContext.xml 에는 printer 빈이 존재하지 않는 상태
        //부모컨텍스트를 찾는다
        IPrinter printer = (IPrinter) child.getBean("printer");
        assertNotNull(printer);

        Hello hello = (Hello) child.getBean("hello");
        assertNotNull(hello);

        hello.pirnt();
        assertThat(printer.toString(), is("Hello Child"));
    }
}
