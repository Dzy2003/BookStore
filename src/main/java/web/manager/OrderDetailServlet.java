package web.manager;

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

@WebServlet("/manager/OrderDetailServlet")
public class OrderDetailServlet extends HttpServlet {
	OrderService service = new OrderServiceImpl();
	UserService userService = new UserServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer oid = Integer.valueOf(request.getParameter("orderid"));
		Integer uid = Integer.valueOf(request.getParameter("uid"));
		try {
			User user = userService.findByID(uid);
			List<CartVo> itemByOid = service.findOrderItemByOid(oid);
			request.setAttribute("user", user);
			request.setAttribute("orderItem", itemByOid);
			request.getRequestDispatcher("/manager/orderdetail.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
