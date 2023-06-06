package web.client;

import entity.User;
import service.Impl.OrderServiceImpl;
import service.Impl.UserServiceImpl;
import service.OrderService;

import service.UserService;
import vo.CartVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 显示用户点击的订单详情的servlet
 */
@WebServlet("/client/ClientOrderDetailServlet")
public class ClientOrderDetailServlet extends HttpServlet {
	OrderService service = new OrderServiceImpl();
	UserService userService = new UserServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("orderid");

		try {
			User user = userService.findByID((Integer) request.getSession().getAttribute("uid"));
			List<CartVo> itemByOid = service.findOrderItemByOid(Integer.valueOf(oid));
			request.setAttribute("orderItem", itemByOid);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/client/clientorderdetail.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
