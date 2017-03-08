package chap09.dao;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2017-03-09
 */
public class DummyDaoTest {

    private List<String> database;
    private DummyDao dummyDao;

    @Before
    public void setUp() {
        database = new ArrayList<>();
        for (int i=0; i<20; i++) {
            database.add("test" + i);
        }
        dummyDao = new DummyDao(database);
    }

    @Test
    public void testDeleteQuery() {
        String target = "test0";
        assertEquals(1, dummyDao.find(target).size());
        dummyDao.delete(target);
        assertEquals(19, database.size());
        assertEquals(0, dummyDao.find(target).size());
    }

}