package domain;

/**
 * @author Kj Nam
 * @since 2017-02-12
 */
public class Hello {
    String name;
    IPrinter printer;

    public String sayHello() {
        return "Hello " + name;
    }

    public void pirnt() {
        this.printer.print(sayHello());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrinter(IPrinter printer) {
        this.printer = printer;
    }
}
