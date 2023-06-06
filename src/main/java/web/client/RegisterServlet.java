package web.client;

import entity.User;
import service.Impl.UserServiceImpl;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 用户注册的servlet
 */
@WebServlet("/client/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    UserService service = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String cellphone = request.getParameter("cellphone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        //封装数据
        User user = new User();
        user.setAddress(address);
        user.setCellphone(cellphone);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setUsername(username);
        try {
            service.reg(user);
            //设置参数跳转
            request.setAttribute("message", "注册成功");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("message", "注册失败,原因：" + e.getMessage());
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
