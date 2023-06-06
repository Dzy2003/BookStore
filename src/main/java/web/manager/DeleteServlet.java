package web.manager;

import entity.Book;
import entity.Category;
import entity.Order;
import entity.PageBean;
import service.BookService;
import service.CartService;
import service.CategoryService;
import service.Impl.BookServiceImpl;
import service.Impl.CartServiceImpl;
import service.Impl.CategoryServiceImpl;
import service.Impl.OrderServiceImpl;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 处理管理端的删除操作(图书的删除，订单的删除，图书分类的删除)
 */
@WebServlet("/manager/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    BookService bookService = new BookServiceImpl();
    OrderService orderService = new OrderServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //根据前端参数查询删除操作的表
        String toDelete = request.getParameter("toDelete");
        if (toDelete.equalsIgnoreCase("book")) {
            toDeleteBook(request, response);
        }
        if (toDelete.equalsIgnoreCase("category")) {
            toDeleteCategory(request, response);
        }
        if (toDelete.equalsIgnoreCase("order")) {
            toDeleteOrder(request, response);
        }
    }
    //图书的删除
    private void toDeleteBook(HttpServletRequest request, HttpServletResponse response)  {
        String bid = request.getParameter("bid");
        try {
            bookService.delete(Integer.valueOf(bid));
            //跳转到显示图书的页面
            PageBean<Book> page = bookService.getByPage(1, 5);
            request.setAttribute("page", page);
            request.getRequestDispatcher("/manager/listbook.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //分类的删除
    private void toDeleteCategory(HttpServletRequest request, HttpServletResponse response) {
        Integer cid = Integer.valueOf(request.getParameter(("cid")));
        try {
            List<Category> allCategories = null;
            categoryService.delete(cid);
            //删除后跳转显示页面
            allCategories = categoryService.getAllCategories();
            request.setAttribute("categories", allCategories);
            request.getRequestDispatcher("/manager/listcategory.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //订单的删除
    private void toDeleteOrder(HttpServletRequest request, HttpServletResponse response) {
        Integer oid = Integer.valueOf(request.getParameter(("oid")));
        try {
            orderService.delete(oid);
            List<Order> orders = orderService.listOrder(false);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/manager/listorder.jsp").forward(request, response);
            request.getRequestDispatcher("/manager/ClientListOrderServlet").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
