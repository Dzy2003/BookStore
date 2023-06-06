package dao.impl;

import dao.BookDao;
import entity.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class BookDaoImpl implements BookDao {

    @Override
    public Integer addBook(Book book) throws SQLException {
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "insert into book(name,author,price,image,description,cid) values(?,?,?,?,?,?)";
        Object params[] = {book.getName(), book.getAuthor(), book.getPrice(), book.getImage(), book.getDescription(), book.getCid()};
        return runner.update(sql, params);
    }

    @Override
    public Book selectById(Integer id) throws SQLException {
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from book where id=?";
        return (Book)runner.query(sql, new BeanHandler(Book.class),id);
    }

    @Override
    public Integer deleteBookById(Integer id) throws SQLException {
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "delete from book where id = ?";
        return runner.update(sql,id);
    }

    @Override
    public List<Book> getPageData(Integer begin, Integer end) throws SQLException {
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from book limit ?,?";
        Object params[] = {begin, end};
        return (List<Book>)runner.query(sql,  new BeanListHandler(Book.class),params);
    }

    @Override
    public Integer getTotalRecord() throws SQLException {
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select count(*) from book";
        long totalRecord = (Long)runner.query(sql, new ScalarHandler());
        return (int)totalRecord;
    }

    @Override
    public List<Book> getPageData(Integer begin, Integer end, Integer category_id) throws SQLException {
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from book where cid =? limit ?,?";
        Object params[] = {category_id, begin, end};
        return (List<Book>)runner.query(sql,  new BeanListHandler(Book.class),params);
    }

    @Override
    public int getTotalRecord(Integer category_id) throws SQLException {
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select count(*) from book where cid = ?";
        long totalRecord = (Long)runner.query(sql,new ScalarHandler(), category_id);
        return (int)totalRecord;
    }
}
