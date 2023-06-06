package service.Impl;

import dao.CategoryDao;
import dao.impl.CategoryDaoImpl;
import entity.Category;
import service.CategoryService;
import Exception.*;
import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao dao = new CategoryDaoImpl();
    @Override
    public void addCategory(Category category) throws SQLException {
        if(dao.getByName(category.getName()) != null) throw new BusinessException("该图书种类已经存在");
        if(dao.insertCategory(category) != 1) throw new SystemException("系统异常");
    }

    @Override
    public List<Category> getAllCategories() throws SQLException {
        return dao.getAll();
    }
}
