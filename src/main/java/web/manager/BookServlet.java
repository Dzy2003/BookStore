package web.manager;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import entity.Book;
import entity.Category;
import entity.PageBean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.BookService;
import service.CategoryService;
import service.Impl.BookServiceImpl;
import service.Impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * 管理员管理图书的servlet
 * list ：显示所有书籍
 * add ： 添加图书
 * doupLoad : 处理前端传入的书籍数据
 * addUI ：管理员添加图书时选择分类你
 */
@WebServlet("/manager/BookServlet")
public class BookServlet extends HttpServlet {
    BookService service = new BookServiceImpl();
    CategoryService service1 = new CategoryServiceImpl();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //根据发送请求转发处理
        String method = request.getParameter("method");
        if (method.equalsIgnoreCase("addUI")) {
            addUI(request, response);
        }
        if (method.equalsIgnoreCase("add")) {
            add(request, response);
        }
        if(method.equalsIgnoreCase("list")){
            list(request, response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNow = Integer.valueOf(request.getParameter("pageNow"));
        PageBean<Book> page = null;
        try {
            page = service.getByPage(pageNow, 5);
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
        request.setAttribute("page", page);
        request.getRequestDispatcher("/manager/listbook.jsp").forward(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Book book = doupLoad(request);
            service.addBook(book);
            request.setAttribute("message", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", e.getMessage());
        }
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    private Book doupLoad(HttpServletRequest request) {

        Book book = new Book();
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                if(item.isFormField()){
                    String name = item.getFieldName();
                    String value = item.getString("UTF-8");
                    BeanUtils.setProperty(book, name, value);
                }else{
                    String filename = item.getName();
                    String savefilename = makeFileName(filename);
                    InputStream in = item.getInputStream();
                    FileOutputStream out = new FileOutputStream("E:\\JAVACODE\\Javaweb_bookstore-master\\store\\src\\main\\webapp\\images" + "\\" + savefilename);
                    int len = 0;
                    byte buffer[] = new byte[1024];
                    while((len = in.read(buffer)) > 0){
                        out.write(buffer, 0, len);
                    }
                    in.close();
                    out.close();
                    item.delete();
                    book.setImage(savefilename);
                    System.out.println(book.getImage());
                }
            }
            return book;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String makeFileName(String filename){
        String ext = filename.substring(filename.lastIndexOf(".") + 1);//lastIndexOf("\\.")����д����
        return UUID.randomUUID().toString() + "." + ext;
    }

    private void addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Category> category = service1.getAllCategories();
            request.setAttribute("categories", category);
            request.getRequestDispatcher("/manager/addBook.jsp").forward(request,
                    response);
        }catch (Exception e){
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

}
