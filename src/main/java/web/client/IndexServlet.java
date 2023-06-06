package web.client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Book;
import entity.Category;
import entity.PageBean;
import service.BookService;
import service.CategoryService;
import service.Impl.BookServiceImpl;
import service.Impl.CategoryServiceImpl;

/**
 * 显示商城首页的servlet
 */
@WebServlet("/client/IndexServlet")
public class IndexServlet extends HttpServlet {
    CategoryService categoryService = new CategoryServiceImpl();
    BookService bookService = new BookServiceImpl();

    /**
     * 根据前端的请求参数转发处理
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //根据前端的参数选择调用的方法
        String method = request.getParameter("method");
        if(method.equalsIgnoreCase("getAll")){
            getAll(request, response);
        }else if(method.equalsIgnoreCase("listBookWithCategory")){
            if("".equals(request.getParameter("cid")) ){
                getAll(request,response);
            }else{
                listBookWithCategory(request,response);
            }
        }
    }

    /**
     * 显示所有的book
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void getAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> categories = null;
        try {
            //将分类数据传入前端
            categories = categoryService.getAllCategories();
            request.setAttribute("categories", categories);
            //获取当前页码
            Integer pageNum = Integer.valueOf(request.getParameter("pageNow"));
            //分页查询
            PageBean<Book> page = bookService.getByPage(pageNum,5);
            //转发
            request.setAttribute("page", page);
            request.getRequestDispatcher("/client/body.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }

    /**
     * 显示所选择分类的所有图书
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void listBookWithCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            Integer cid = Integer.valueOf(request.getParameter("cid"));
            Integer pageNum = Integer.valueOf(request.getParameter("pageNow"));
            List<Category> categories = null;
            categories = categoryService.getAllCategories();
            request.setAttribute("categories", categories);
            PageBean<Book> page = bookService.getByPage(pageNum,5,cid);
            //将pageBean传递给前端
            request.setAttribute("cid", cid);
            request.setAttribute("page", page);
            request.getRequestDispatcher("/client/body.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

}
