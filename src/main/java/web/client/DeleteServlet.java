package web.client;

import service.CartService;
import service.CategoryService;
import service.Impl.CartServiceImpl;
import service.Impl.OrderServiceImpl;
import service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 处理用户端的删除操作(购物车的删除和订单的删除)
 */
@WebServlet("/client/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    CartService cartService = new CartServiceImpl();
    OrderService orderService = new OrderServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String toDelete = request.getParameter("toDelete");
        if (toDelete.equalsIgnoreCase("order")) {
            toDeleteOrder(request, response);
        }
        if (toDelete.equalsIgnoreCase("cart")) {
            toDeleteCart(request, response);
        }
    }
    //订单的删除
    private void toDeleteCart(HttpServletRequest request, HttpServletResponse response)  {
        String cid = request.getParameter("cid");
        try {
            cartService.delete(Integer.valueOf(cid));
            //跳转到显示订单的页面
            request.getRequestDispatcher("/client/ListCartServlet").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //购物车的删除
    private void toDeleteOrder(HttpServletRequest request, HttpServletResponse response) {
        Integer oid = Integer.valueOf(request.getParameter(("oid")));
        try {
            orderService.delete(oid);
            request.getRequestDispatcher("/client/ClientListOrderServlet").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
