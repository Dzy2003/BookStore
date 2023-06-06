package service;

import entity.Book;
import entity.PageBean;

import java.sql.SQLException;
import java.util.List;

public interface BookService {
    /**
     * 添加图书
     * @param book
     * @throws SQLException
     */
    void addBook(Book book) throws SQLException;

    /**
     * 对图书进行分页查询
     * @param pageNow 当前页数
     * @param pageSize 每页大小
     * @return
     * @throws SQLException
     */
    PageBean<Book> getByPage(Integer pageNow, Integer pageSize) throws SQLException;

    /**
     * 对图书进行分页查询(重载方法)
     * @param pageNow 当前页数
     * @param pageSize 每页大小
     * @param cid 该书所属分类
     * @return
     * @throws SQLException
     */
    PageBean<Book> getByPage(Integer pageNow, Integer pageSize,Integer cid) throws SQLException;
}
