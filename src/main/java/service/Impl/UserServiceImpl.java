package service.Impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import service.UserService;
import Exception.*;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public void reg(User user) throws SQLException {
        User res = userDao.selectUserByName(user.getUsername());
        if(res != null) throw new BusinessException("您输入的用户名已存在");
        if( userDao.InsertUser(user) != 1) throw new SystemException("注册异常");

    }

    @Override
    public User login(String username, String password) throws SQLException {
        User user = userDao.selectUserByName(username);
        if(user == null) throw new BusinessException("您输入的用户不存在");
        if(!user.getPassword().equals(password)) throw new BusinessException("密码输入错误");
        return user;
    }

    @Override
    public User findByID(Integer id) throws SQLException {
        return  userDao.selectByID(id);
    }
}
