package dao.impl;

import dao.UserDao;
import entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.util.Queue;

public class UserDaoImpl implements UserDao {
    QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
    public Integer InsertUser(User user) throws SQLException {
        String sql = "Insert into user(username,password,phone,cellphone,email,address) values(?,?,?,?,?,?)";
        Object[] params = {user.getUsername(),user.getPassword(),user.getPhone(),
                user.getCellphone(),user.getEmail(),user.getAddress()};
        int i = runner.update(sql, params);
        return i;
    }

    public User selectUserByName(String name) throws SQLException {
        String sql = "select * from user where username = ?";
        User user = runner.query(sql, new BeanHandler<User>(User.class), name);
        return user;
    }

    @Override
    public User selectByID(Integer id) throws SQLException {
        String sql = "select * from user where id = ?";
        User user = runner.query(sql, new BeanHandler<User>(User.class), id);
        return user;
    }
}
