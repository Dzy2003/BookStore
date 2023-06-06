package web.client;

import service.Impl.OrderServiceImpl;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 创建订单的servlet
 */
@WebServlet("/client/OrderServlet")
public class OrderServlet extends HttpServlet {
	OrderService service = new OrderServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer uid = (Integer) request.getSession().getAttribute("uid");
		String s =request.getParameter("cids");
		s = s.substring(0,s.length() - 1);
		System.out.println(s);
		String[] split = s.split(", ");
		Integer[] cids = new Integer[split.length];
		for (int i = 0; i < cids.length ; i++) {
			cids[i] = Integer.parseInt(split[i]);
		}
		try {
			service.CreateOrder(uid,cids);
			request.setAttribute("message", "创建订单成功");
		} catch (SQLException e) {
			request.setAttribute("message", "创建订单失败");
			e.printStackTrace();
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
