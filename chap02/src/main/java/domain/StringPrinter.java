package domain;

/**
 * @author Kj Nam
 * @since 2017-02-12
 */
public class StringPrinter implements IPrinter {
    private StringBuffer sb = new StringBuffer();

    @Override
    public void print(String message) {
        this.sb.append(message);
    }

    @Override
    public String toString() {
        return this.sb.toString();
    }
}
