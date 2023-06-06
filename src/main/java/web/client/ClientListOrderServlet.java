package web.client;

import entity.Order;
import entity.User;
import service.Impl.OrderServiceImpl;
import service.Impl.UserServiceImpl;
import service.OrderService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 显示用户订单列表的servlet
 */
@WebServlet("/client/ClientListOrderServlet")
public class ClientListOrderServlet extends HttpServlet {
	OrderService service = new OrderServiceImpl();
	UserService userService = new UserServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer uid = (Integer) request.getSession().getAttribute("uid");
			User user = userService.findByID(uid);
			List<Order> orders  = service.clientListOrder(uid);
			request.setAttribute("orders", orders);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/client/clientlistorder.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
