package service;

import entity.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {
    /**
     * 添加图书分类
     * @param category
     */
    void addCategory(Category category) throws SQLException;

    /**
     * 获取所有图书分类
     * @return
     */
    List<Category> getAllCategories() throws SQLException;

    /**
     * 删除
     * @param id
     */
    void delete(Integer id) throws SQLException;
}
