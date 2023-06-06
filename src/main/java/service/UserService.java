package service;

import entity.User;

import java.sql.SQLException;

public interface UserService {
    /**
     * 注册
     * @param user
     * @throws SQLException
     */
    public void reg(User user) throws SQLException;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public User login(String username, String password) throws SQLException;

    /**
     * 根据id找到用户
     * @param id
     * @return
     * @throws SQLException
     */
    User findByID(Integer id) throws SQLException;

    /**
     * 删除
     * @param id
     */
    void delete(Integer id) throws SQLException;
}