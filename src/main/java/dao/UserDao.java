package dao;

import entity.User;

import java.sql.SQLException;

public interface UserDao {
    /**
     * 向用户表插入数据(注册)
     * @param user 用户的bean
     * @return
     * @throws SQLException
     */
    Integer InsertUser(User user) throws SQLException;

    /**
     * 根据用户名查询数据(登录)
     * @param name 用户名
     * @return
     * @throws SQLException
     */
    User selectUserByName(String name) throws SQLException;

    /**
     * 根据id查询用户
     * @param id yid
     * @return
     * @throws SQLException
     */
    User selectByID(Integer id) throws SQLException;
}
