package dao.impl;

import dao.CategoryDao;
import entity.Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public Integer insertCategory(Category category) throws SQLException {
        String sql = "insert into category(name,description) values(?,?)";
        Object params[] = {category.getName(), category.getDescription()};
        return runner.update(sql, params);
    }

    @Override
    public Category find(Integer id) throws SQLException {
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from category where id=?";
        return (Category)runner.query(sql, new BeanHandler(Category.class),id);
    }

    @Override
    public List<Category> getAll() throws SQLException {
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from category";
        return (List<Category>)runner.query(sql, new BeanListHandler(Category.class));
    }

    @Override
    public Category getByName(String name) throws SQLException {
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from category where name= ?";
        return (Category)runner.query(sql,  new BeanHandler(Category.class),name);
    }

    @Override
    public Integer deleteCategoryById(Integer id) throws SQLException {
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "delete from category where id = ?";
        return runner.update(sql,id);
    }
}
