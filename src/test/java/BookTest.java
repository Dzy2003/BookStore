import dao.BookDao;
import dao.impl.BookDaoImpl;
import entity.Book;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class BookTest {
    BookDao dao = new BookDaoImpl();
    @Test
    public void testAdd() throws SQLException {
        Book book = new Book();
        book.setName("百年孤独9");
        book.setAuthor("马尔克斯");
        book.setDescription("拉丁美洲的血泪史");
        book.setCid(2);
        book.setPrice(55l);
        book.setImage("http://");
        dao.addBook(book);
    }
    @Test
    public void testSelect() throws SQLException {
        Book book = dao.selectById(2);
        System.out.println(book);
    }
    @Test
    public void testSelectByPage() throws SQLException {
        List<Book> pageData = dao.getPageData(0, 1,2);
        System.out.println(pageData);
    }
    @Test
    public void testCount() throws SQLException{
        Integer integer = dao.getTotalRecord();
        System.out.println(integer);
    }


    @Test
    public void test(){
        Integer[] oids = {1,2,3,5};
        String s = "";
        for (int i = 0; i < oids.length; i++) {
            s += oids[i];
            s = i == oids.length - 1 ? s : s +",";
        }
        System.out.println(s);
    }

}
