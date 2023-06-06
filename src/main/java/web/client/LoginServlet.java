package web.client;

import entity.User;
import service.Impl.UserServiceImpl;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.sql.SQLException;

/**
 * 用户登录的servlet
 */
@WebServlet("/client/LoginServlet")
public class LoginServlet extends HttpServlet {
    UserService service = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        try {
            User user = service.login(username, password);
            //登录成功携带参数跳转页面
            HttpSession session = req.getSession();
            //将登录成功的用户放入session中
            session.setAttribute("uid",user.getId());
            session.setAttribute("username",username);
            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/client/head.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/message.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
