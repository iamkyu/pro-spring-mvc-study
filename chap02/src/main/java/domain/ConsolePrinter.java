package domain;

/**
 * @author Kj Nam
 * @since 2017-02-12
 */
public class ConsolePrinter implements IPrinter {

    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
