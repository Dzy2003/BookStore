package service.Impl;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import entity.Book;
import entity.PageBean;
import service.BookService;
import Exception.*;
import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {
    BookDao dao = new BookDaoImpl();
    @Override
    public void addBook(Book book) throws SQLException {
        dao.addBook(book);
    }

    @Override
    public PageBean<Book> getByPage(Integer pageNow, Integer pageSize) throws SQLException {
        if(pageNow < 1) throw new BusinessException("已经是第一页了");
        int begin = (pageNow - 1) * pageSize;
        int end = pageSize;
        List<Book> pageData = dao.getPageData(begin, end);
        Integer record = dao.getTotalRecord();
        PageBean<Book> page = new PageBean<>();
        page.setTotalPages(record / 5 + 1);
        page.setRows(pageData);
        page.setTotalCount(record);
        return page;
    }

    @Override
    public PageBean<Book> getByPage(Integer pageNow, Integer pageSize, Integer cid) throws SQLException {
        if(pageNow < 1) throw new BusinessException("已经是第一页了");
        int begin = (pageNow - 1) * pageSize;
        int end = pageSize;
        List<Book> pageData = dao.getPageData(begin, end,cid);
        Integer record = dao.getTotalRecord(cid);
        PageBean<Book> page = new PageBean<>();
        page.setTotalPages(record / 5 + 1);
        page.setRows(pageData);
        page.setTotalCount(record);
        return page;
    }
}
