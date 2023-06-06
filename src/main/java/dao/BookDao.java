package dao;

import entity.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    Integer addBook(Book book) throws SQLException;

    Book selectById(Integer id) throws SQLException;

    /**
     * 分页查询图书
     * @param begin
     * @param end
     * @return
     */
    List<Book> getPageData(Integer begin, Integer end) throws SQLException;

    /**
     * 查询图书总数
     * @return
     */
    Integer getTotalRecord() throws SQLException;

    /**
     * 分页分类查询图书(重载方法)
     * @param begin
     * @param end
     * @param category_id
     * @return
     */
    public List<Book> getPageData(Integer begin, Integer end, Integer category_id) throws SQLException;

    /**
     * 分类查询图书总数(重载方法)
     * @param category_id
     * @return
     */
    public int getTotalRecord(Integer category_id) throws SQLException;
}
