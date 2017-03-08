package chap09.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kj Nam
 * @since 2017-03-09
 */
public class DummyDao {

    private List<String> database;

    public DummyDao(List<String> database) {
        this.database = database;
    }

    public void add(String element) {
        this.database.add(element);
    }

    public void delete(String element) {
        database.remove(element);
    }

    public List<String> find(String queryWord) {
        List<String> result = new ArrayList<>();
        for (String each : database) {
            if (each.contains(queryWord)) {
                result.add(each);
            }
        }
        return result;
    }
}
