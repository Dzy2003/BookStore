
import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class UserTest {
    UserDao bookDao = new UserDaoImpl();
    @Test
    public void testAdd(){
        User user = new User();
        user.setAddress("dawdwd");
        user.setCellphone("12333");
        user.setUsername("伟大伟大");
        user.setEmail("dwadwddad@qq.com");
        user.setPhone("2414434");
        user.setPassword("dwdadwada");
        try {
            Integer i = bookDao.InsertUser(user);
            System.out.println(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testSelect() throws SQLException {
        User user = bookDao.selectUserByName("伟大伟大");
        System.out.println(user);
    }
}
