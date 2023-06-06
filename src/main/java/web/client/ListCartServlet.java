package web.client;

import service.CartService;
import service.Impl.CartServiceImpl;
import vo.CartVo;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 显示购物车页面的servlet
 */
@WebServlet("/client/ListCartServlet")
public class ListCartServlet extends HttpServlet {
	CartService service = new CartServiceImpl();
	//将点击的商品加入购物车
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer uid = (Integer)request.getSession().getAttribute("uid");
		try{
			//bookId不为null则添加图书后跳转到购物车页面
			if(request.getParameter("bookid") != null) {
				Integer bookId = Integer.valueOf(request.getParameter("bookid"));
				service.InsertCart(uid,bookId);
			}
			//为空则直接显示页面
			List<CartVo> cartVoList = service.listCartBook(uid);
			int totalPrice = 0;
			for (CartVo cartVo : cartVoList) {
				totalPrice += cartVo.getRealPrice();
			}
			request.setAttribute("totalPrice",totalPrice);
			request.setAttribute("cartVo",cartVoList);
			request.getRequestDispatcher("/client/listcart.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "添加购物车失败");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
