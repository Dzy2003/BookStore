package web.manager;

import entity.Order;
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
 * 管理端根据订单状态显示。(发货和未发货)
 */
@WebServlet("/manager/ListOrderServlet")
public class ListOrderServlet extends HttpServlet {
	OrderService service = new OrderServiceImpl();
	UserService userService = new UserServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Boolean state = Boolean.valueOf(request.getParameter("state"));
		try {
			List<Order> orders = service.listOrder(state);
			request.setAttribute("orders", orders);
			request.getRequestDispatcher("/manager/listorder.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("message", "查询失败");
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
