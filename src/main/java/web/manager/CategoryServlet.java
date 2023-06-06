package web.manager;

import entity.Category;
import service.CategoryService;
import service.Impl.CategoryServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

//��������CRUD����
@WebServlet("/manager/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	CategoryService service = new CategoryServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method.equals("add")) {
			add(request, response);
		} else if (method.equals("listall")) {
			listAll(request, response);
		} else {
			request.setAttribute("message", "未知操作，请重试");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}

	private void listAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Category> CategoryList = null;
		try {
			CategoryList = service.getAllCategories();
			request.setAttribute("categories", CategoryList);
			request.getRequestDispatcher("/manager/listcategory.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}

	}


	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//获取参数
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			//封装对象
			Category category = new Category();
			category.setName(name);
			category.setDescription(description);

			service.addCategory(category);
			request.setAttribute("message", "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
